package data_access;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import entity.User;
import use_case.choose_recipe.ChooseRecipeDataAccessInterface;
import use_case.create.CreateDataAccessInterface;
import use_case.delete.DeleteDataAccessInterface;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeDataAccessInterface;
import use_case.recipe_search.RecipeSearchDataAccessInterface;
import use_case.shopping_list.ShoppingListDataAccessInterface;

/**
 * DAO for the RecipeSearch Use Case.
 */
public class RecipeDataAccessObject implements RecipeSearchDataAccessInterface,
        ChooseRecipeDataAccessInterface,
        LikeAndDislikeRecipeDataAccessInterface,
        CreateDataAccessInterface,
        ShoppingListDataAccessInterface,
        DeleteDataAccessInterface {

    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?f=";
    private static final String FILE_IO_API_URL = "https://file.io";
    private static final String FILE_PATH = "all_recipes.json";
    private static String recipeFileKey = "";
    private static final String API_KEY = "35F52QF.ZQV4A4E-ASHMAQD-QSPTZ93-NHYCJT6";
    private static final int STATUS_CODE_OK = 200;
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String NODES = "nodes";
    // Holds the list of recipes loaded from the downloaded JSON
    private List<Recipe> cachedRecipes = new ArrayList<>();

    @Override
    public String findFileOnFileIo(String fileName) {
        try {
            // Properly format the search URL with the provided file name
            final String searchUrl = FILE_IO_API_URL + "/?search=" + URLEncoder.encode(fileName,
                    StandardCharsets.UTF_8);
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(searchUrl))
                    .header("accept", "application/json")
                    .header(AUTHORIZATION, BEARER + API_KEY)
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                // Parse the response to find if the file exists
                System.out.println("File.io search response: " + response.body());
                final JsonElement jsonResponse = JsonParser.parseString(response.body());
                final JsonObject responseObject = jsonResponse.getAsJsonObject();

                // Correctly handle the response with "nodes" instead of "files"
                if (responseObject.has(NODES) && responseObject.get(NODES).isJsonArray()) {
                    final JsonArray nodesArray = responseObject.getAsJsonArray(NODES);

                    for (JsonElement nodeElement : nodesArray) {
                        if (nodeElement.isJsonObject()) {
                            final JsonObject nodeObject = nodeElement.getAsJsonObject();

                            if (nodeObject.has("name") && nodeObject.get("name").getAsString().equals(
                                    fileName)) {
                                if (nodeObject.has("key")) {
                                    // Correctly index to get the key
                                    recipeFileKey = nodeObject.get("key").getAsString();
                                    System.out.println(
                                            "File '" + fileName + "' found on File.io with key: " + recipeFileKey);
                                }
                                else {
                                    System.out.println("File object found, but no key present for file: " + fileName);
                                }
                            }
                        }
                    }
                }
                else {
                    System.out.println("No 'nodes' array found in the response. Response: " + response.body());
                }
            }
            else {
                System.out.println("Failed to get file list from File.io. Status code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error while searching for file on File.io: " + ex.getMessage());
            Thread.currentThread().interrupt();
        }
        return recipeFileKey;
    }

    @Override
    public void deleteFileFromFileIo() {
        if (recipeFileKey.isEmpty()) {
            System.err.println("File key is empty. Cannot delete file.");
        }
        System.out.println("Deleting file from File.io with key: " + recipeFileKey);
        try {
            final String deleteUrl = FILE_IO_API_URL + "/" + URLEncoder.encode(recipeFileKey, StandardCharsets.UTF_8);
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(deleteUrl))
                    .DELETE()
                    .header(AUTHORIZATION, BEARER + API_KEY)
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("File deleted successfully: " + response.body());
            }
            else {
                System.err.println("Failed to delete file. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error during file deletion: " + ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Fetches all recipes from the API by iterating over keywords (a-z) using the 'f' parameter.
     *
     * @return a list of all CommonRecipe objects
     */
    @Override
    public List<Recipe> fetchAllRecipes() {
        System.out.println("Starting to fetch all recipes from API...");
        final List<Recipe> allRecipes = new ArrayList<>();

        for (char keyword = 'a'; keyword <= 'z'; keyword++) {
            System.out.println("Fetching recipes for keyword: " + keyword);
            final List<Recipe> recipes = fetchRecipesByKeyword(String.valueOf(keyword));
            allRecipes.addAll(recipes);
            System.out.println("Added " + recipes.size() + " recipes for keyword: " + keyword);
        }

        System.out.println("Finished fetching all recipes. Total recipes found: " + allRecipes.size());
        writeRecipesToFile(allRecipes);
        uploadFileToFileIo();
        return allRecipes;
    }

    @Override
    public List<Recipe> fetchRecipesByKeyword(String keyword) {
        List<Recipe> recipes = new ArrayList<>();

        try {
            final String url = API_URL + URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("Successful response for keyword: " + keyword);
                recipes = parseRecipes(response.body());
            }
            else {
                System.err.println("Error: Received HTTP " + response.statusCode() + " for keyword: " + keyword);
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error fetching recipes for keyword " + keyword + ": " + ex.getMessage());
            Thread.currentThread().interrupt();
        }

        return recipes;
    }

    /**
     * Parses the JSON response and converts it to a list of CommonRecipe objects.
     *
     * @param responseBody the JSON response body from the API
     * @return a list of CommonRecipe objects parsed from the response
     */
    private List<Recipe> parseRecipes(String responseBody) {
        System.out.println("Parsing recipes.");
        final List<Recipe> recipes = new ArrayList<>();
        final JsonElement jsonElement = JsonParser.parseString(responseBody);

        // Check if the root element is an object
        if (jsonElement.isJsonObject()) {
            final JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Check if "meals" exists and handle it according to its type
            if (jsonObject.has("meals")) {
                final JsonElement mealsElement = jsonObject.get("meals");

                if (mealsElement.isJsonArray()) {
                    // Process as a JsonArray if it's an array
                    final JsonArray mealsArray = mealsElement.getAsJsonArray();
                    System.out.println("Number of recipes found in response: " + mealsArray.size());
                    recipes.addAll(processMealsArray(mealsArray));
                }
                else if (mealsElement.isJsonObject()) {
                    // Wrap a single JsonObject in an array if it's an object
                    System.out.println("Single meal object found in response, wrapping in array.");
                    final JsonArray mealsArray = new JsonArray();
                    mealsArray.add(mealsElement.getAsJsonObject());
                    recipes.addAll(processMealsArray(mealsArray));
                }
                else if (mealsElement.isJsonNull()) {
                    // Handle null case gracefully
                    System.out.println("No recipes found for keyword: null response for 'meals'.");
                }
                else if (mealsElement.isJsonPrimitive()) {
                    // Handle unexpected primitive case
                    System.out.println(
                            "Unexpected format for 'meals': JsonPrimitive (likely no recipes found for keyword).");
                }
                else {
                    // Log unexpected format for meals
                    System.err.println("Unexpected format for 'meals': " + mealsElement.getClass().getName());
                }
            }
            else {
                System.out.println("No 'meals' field found in the API response.");
            }
        }
        else {
            System.err.println("Unexpected JSON format: Root element is not a JsonObject.");
        }
        return recipes;
    }

    /**
     * Processes the JsonArray of meals and converts each meal into a CommonRecipe object.
     *
     * @param mealsArray the JsonArray containing meal data
     * @return a list of CommonRecipe objects
     */
    private List<Recipe> processMealsArray(JsonArray mealsArray) {
        final List<Recipe> recipes = new ArrayList<>();

        for (int i = 0; i < mealsArray.size(); i++) {
            final JsonObject mealObject = mealsArray.get(i).getAsJsonObject();

            final String id = mealObject.has("idMeal") && !mealObject.get("idMeal").isJsonNull()
                    ? mealObject.get("idMeal").getAsString() : "";
            final String name = mealObject.has("strMeal") && !mealObject.get("strMeal").isJsonNull()
                    ? mealObject.get("strMeal").getAsString() : "";
            final String category = mealObject.has("strCategory") && !mealObject.get("strCategory").isJsonNull()
                    ? mealObject.get("strCategory").getAsString() : "";
            final String instructions = mealObject.has(
                    "strInstructions") && !mealObject.get("strInstructions").isJsonNull()
                    ? mealObject.get("strInstructions").getAsString() : "";

            final Map<String, String> ingredientMeasureMap = new HashMap<>();
            for (int j = 1; j <= 20; j++) {
                final String ingredientKey = "strIngredient" + j;
                final String measureKey = "strMeasure" + j;

                final String ingredient = mealObject.has(ingredientKey) && !mealObject.get(ingredientKey).isJsonNull()
                        ? mealObject.get(ingredientKey).getAsString() : "";
                final String measure = mealObject.has(measureKey) && !mealObject.get(measureKey).isJsonNull()
                        ? mealObject.get(measureKey).getAsString() : "";

                if (!ingredient.isEmpty()) {
                    ingredientMeasureMap.put(ingredient, measure);
                }
            }
            final RecipeFactory recipeFactory = new CommonRecipeFactory();
            final Recipe recipe = recipeFactory.createRecipe(id, name, category, instructions, ingredientMeasureMap,
                    0, 0);
            recipes.add(recipe);
        }
        return recipes;
    }

    @Override
    public void writeRecipesToFile(List<Recipe> recipes) {
        System.out.println("Writing all recipes to JSON file.");

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String jsonContent = gson.toJson(recipes);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(jsonContent);
            System.out.println("All recipes data written to file successfully.");
        }
        catch (IOException ex) {
            System.err.println("Error while writing to file: " + ex.getMessage());
        }
    }

    @Override
    public void uploadFileToFileIo() {
        System.out.println("Uploading file to File.io with Bearer Auth.");
        try {
            final HttpClient client = HttpClient.newHttpClient();
            final String bearerToken = API_KEY;

            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(FILE_IO_API_URL))
                    .header(AUTHORIZATION, BEARER + bearerToken)
                    .header("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary")
                    .POST(ofFileUpload(Path.of(FILE_PATH)))
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("File uploaded successfully: " + response.body());
                // Parse the response to extract the "key" value and set recipeFileKey
                final JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                recipeFileKey = jsonResponse.get("key").getAsString();
                System.out.println("File key set to: " + recipeFileKey);
            }
            else {
                System.err.println("Failed to upload file. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error during file upload: " + ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Constructs a multipart request body for file upload.
     *
     * @param path the path to the file to upload
     * @return a BodyPublisher for the multipart file upload
     * @throws IOException if there is an error reading the file
     */
    public static HttpRequest.BodyPublisher ofFileUpload(Path path) throws IOException {
        final var boundary = "----WebKitFormBoundary";
        final var fileBytes = Files.readAllBytes(path);
        final var byteArrays = new ArrayList<byte[]>();

        byteArrays.add(("--" + boundary + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\""
                + path.getFileName() + "\"\r\nContent-Type: application/json\r\n\r\n").getBytes(
                        StandardCharsets.UTF_8));
        byteArrays.add(fileBytes);
        byteArrays.add(("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

    /**
     * Downloads the recipes file from the cloud, processes it, and updates the local JSON file.
     */
    @Override
    public void loadRecipesFromCloud() {
        // Check if the file key is empty; if it is, the method cannot proceed
        if (recipeFileKey.isEmpty()) {
            System.err.println("File key is empty. Cannot download file.");
        }

        System.out.println("Downloading file from File.io with key: " + recipeFileKey);

        try {
            // Construct the download URL using the file key
            final String downloadUrl = FILE_IO_API_URL + "/" + URLEncoder.encode(recipeFileKey, StandardCharsets.UTF_8);
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(downloadUrl))
                    .GET()
                    .build();

            // Send the HTTP request and capture the response
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status is 200 (OK)
            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("File downloaded successfully.");

                // Log the entire JSON content received
                final String jsonContent = response.body();

                // Parse the downloaded JSON content
                final List<Recipe> processedRecipes = parseDownloadedRecipes(jsonContent);

                // Write parsed recipes back to the JSON file
                writeRecipesToFile(processedRecipes);

                // Upload the updated JSON file immediately
                uploadFileToFileIo();

            }
            else {
                System.err.println("Failed to download file. Status code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error during file download: " + ex.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private List<Recipe> parseDownloadedRecipes(String jsonContent) {
        System.out.println("Parsing downloaded recipes JSON.");
        List<Recipe> processedRecipes = new ArrayList<>();
        final JsonElement jsonElement = JsonParser.parseString(jsonContent);

        if (jsonElement.isJsonArray()) {
            // If JSON is an array, proceed as usual
            final JsonArray recipesArray = jsonElement.getAsJsonArray();
            System.out.println("JSON is an array. Processing array elements.");
            processedRecipes = processRecipesArray(recipesArray);
            System.out.println("Processed " + processedRecipes.size() + " recipes.");
        }
        else if (jsonElement.isJsonObject()) {
            // If JSON is a single object, wrap it in an array
            System.out.println("JSON is a single object. Wrapping in an array.");
            final JsonArray recipesArray = new JsonArray();
            recipesArray.add(jsonElement.getAsJsonObject());
            processedRecipes = processRecipesArray(recipesArray);
            System.out.println("Processed " + processedRecipes.size() + " recipes.");
        }
        else {
            System.err.println("Unexpected JSON format: Not an array or object.");
            System.err.println("JSON content: " + jsonElement.toString());
        }

        return processedRecipes;
    }

    private List<Recipe> processRecipesArray(JsonArray recipesArray) {
        final List<Recipe> processedRecipes = new ArrayList<>();
        cachedRecipes.clear();

        for (int i = 0; i < recipesArray.size(); i++) {
            final JsonElement element = recipesArray.get(i);

            if (!element.isJsonObject()) {
                System.err.println("Unexpected element type at index " + i + ": " + element.getClass().getName());
                System.err.println("Element content: " + element.toString());
                continue;
            }

            final JsonObject mealObject = element.getAsJsonObject();

            // Use correct field names as per your JSON file (likely from MealDB API)
            final String mealName = mealObject.has("name") && !mealObject.get("name").isJsonNull()
                    ? mealObject.get("name").getAsString() : null;

            if (mealName == null || mealName.isEmpty()) {
                System.out.println("Skipping recipe with no valid name at index " + i + ", content: " + mealObject.toString());
                continue;
            }

            final String idNum = mealObject.has("id") && !mealObject.get("id").isJsonNull()
                    ? mealObject.get("id").getAsString() : "";

            System.out.println("Processing recipe: ID = " + idNum + ", Name = " + mealName);

            // Continue processing other fields as usual
            final String category = mealObject.has("category") && !mealObject.get("category").isJsonNull()
                    ? mealObject.get("category").getAsString() : "";
            final String instructions = mealObject.has(
                    "instructions") && !mealObject.get("instructions").isJsonNull()
                    ? mealObject.get("instructions").getAsString() : "";

            // Parse the ingredientMeasureMap
            final Map<String, String> ingredientMeasureMap = new HashMap<>();
            if (mealObject.has(
                    "ingredientMeasureMap") && !mealObject.get("ingredientMeasureMap").isJsonNull()) {
                final JsonObject ingredientMapJson = mealObject.getAsJsonObject("ingredientMeasureMap");
                // Iterate over all the keys in the ingredientMeasureMap JSON object
                for (String key : ingredientMapJson.keySet()) {
                    final String measure = ingredientMapJson.has(key) && !ingredientMapJson.get(key).isJsonNull()
                            ? ingredientMapJson.get(key).getAsString()
                            : "";
                    ingredientMeasureMap.put(key, measure);
                }
            }

            // Parse likeNumber
            final int likeNumber = mealObject.has("likeNumber") && !mealObject.get("likeNumber").isJsonNull()
                    ? mealObject.get("likeNumber").getAsInt() : 0;

            // Parse dislikeNumber
            final int dislikeNumber = mealObject.has("dislikeNumber") && !mealObject.get("dislikeNumber").isJsonNull()
                    ? mealObject.get("dislikeNumber").getAsInt() : 0;

            final RecipeFactory recipeFactory = new CommonRecipeFactory();
            final Recipe recipe = recipeFactory.createRecipe(
                    idNum, mealName, category, instructions, ingredientMeasureMap, likeNumber, dislikeNumber);
            processedRecipes.add(recipe);
        }

        cachedRecipes.addAll(processedRecipes);
        System.out.println("Total recipes loaded: " + processedRecipes.size());
        return processedRecipes;
    }

    @Override
    // Method to search recipes based on a keyword from cached recipes
    public List<Recipe> searchRecipes(String keyword) {
        final List<Recipe> result = new ArrayList<>();
        final String keywordLower = keyword.toLowerCase();
        System.out.println("Total cached recipes: " + cachedRecipes.size());

        for (Recipe recipe : cachedRecipes) {
            final String recipeName = recipe.getName().toLowerCase();
            System.out.println("Checking recipe: " + recipe.getName() + " (Lowercase: " + recipeName + ")");

            if (recipeName.contains(keywordLower)) {
                result.add(recipe);
                System.out.println("Match found: " + recipe.getName());
            }
        }

        System.out.println("Found " + result.size() + " recipes matching keywordLower: " + keywordLower);
        return result;
    }

    @Override
    // Method to search for a single recipe based on a keyword
    // Will refactor this!
    public Recipe getOneRecipe(String dishName) {
        final String dishNameLower = dishName.toLowerCase();
        System.out.println("Total cached recipes: " + cachedRecipes.size());
        Recipe matchingRecipe = null;
        for (Recipe recipe : cachedRecipes) {
            final String recipeName = recipe.getName().toLowerCase();
            System.out.println("Checking recipe: " + recipe.getName() + " (Lowercase: " + recipeName + ")");

            if (recipeName.equals(dishNameLower)) {
                System.out.println("Match found: " + recipe.getName());
                matchingRecipe = recipe;
                break;
            }
        }
        if (matchingRecipe == null) {
            System.out.println("No recipe found matching keyword: " + dishNameLower);
        }
        return matchingRecipe;
    }

    @Override
    public String getMaxId() {
        String maxId = cachedRecipes.get(0).getId();

        for (Recipe recipe : cachedRecipes) {
            final String currentId = recipe.getId();

            if (currentId.compareTo(maxId) > 0) {
                maxId = currentId;
            }
        }
        return maxId;
    }

    @Override
    public boolean isNameInRecipes(String nameToCheck) {
        boolean found = false;
        for (Recipe recipe : cachedRecipes) {
            if (recipe.getName().equalsIgnoreCase(nameToCheck)) {
                found = true;
                break;
            }
        }

        return found;
    }

    @Override
    public List<Recipe> getCachedRecipes() {
        return cachedRecipes;
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        this.cachedRecipes.add(recipe);
    }

    @Override
    public boolean removeRecipeFromLocalFile(String filePath, String recipeName) {
        boolean result = false;

        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file into a JsonObject
            final JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Retrieve the "recipes" array from the JsonObject
            final JsonArray recipesArray = jsonObject.getAsJsonArray("recipes");

            // Iterate through the array to find the recipe with the matching name
            for (int i = 0; i < recipesArray.size(); i++) {
                final JsonObject recipe = recipesArray.get(i).getAsJsonObject();

                // Check if the recipe name matches (case-insensitive comparison)
                if (recipe.get("name").getAsString().equalsIgnoreCase(recipeName)) {
                    // Remove the recipe from the array
                    recipesArray.remove(i);

                    // Update the file with the modified JSON
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(jsonObject.toString());
                        result = true;
                    }
                    break;
                }
            }
        }
        catch (IOException ex) {
            // Print the stack trace for debugging purposes in case of an error
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public void removeRecipeByName(String recipeName) {
        // Use the removeIf method to remove any recipe whose name matches the given recipe name
        // The comparison is case-insensitive
        cachedRecipes.removeIf(recipe -> recipe.getName().equalsIgnoreCase(recipeName));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public String[] getFavoriteRecipes() {
        return new String[0];
    }

    @Override
    public User get(String username) {
        return null;
    }
}
