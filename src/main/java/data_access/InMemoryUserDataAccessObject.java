package data_access;

import java.io.File;
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
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import entity.CommonUserFactory;
import entity.Recipe;
import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.favorite_receipe.FavoriteRecipeDataAccessInterface;
import use_case.like_and_dislike_a_recipe.UserLikeAndDislikeDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.shopping_list.ShoppingListDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        UserLikeAndDislikeDataAccessInterface,
        FavoriteRecipeDataAccessInterface,
        ShoppingListDataAccessInterface,
        LogoutUserDataAccessInterface {

    private static final String FILE_IO_API_URL = "https://file.io";
    private static final String API_KEY = "35F52QF.ZQV4A4E-ASHMAQD-QSPTZ93-NHYCJT6";
    private static final int STATUS_CODE_OK = 200;
    private static final String FILE_PATH = "all_users.json";
    private static String userFILE_KEY = "";
    private Map<String, User> users = new HashMap<>();
    private String currentUsername;
    private String username;
    private String[] favoriteRecipes;

    @Override
    public String findFileOnFileIo(String fileName) {
        try {
            // Properly format the search URL with the provided file name
            String searchUrl = FILE_IO_API_URL + "/?search=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(searchUrl))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                // Parse the response to find if the file exists
                System.out.println("File.io search response: " + response.body()); // Debug log to see the response
                JsonElement jsonResponse = JsonParser.parseString(response.body());
                JsonObject responseObject = jsonResponse.getAsJsonObject();

                // Correctly handle the response with "nodes" instead of "files"
                if (responseObject.has("nodes") && responseObject.get("nodes").isJsonArray()) {
                    JsonArray nodesArray = responseObject.getAsJsonArray("nodes");

                    for (JsonElement nodeElement : nodesArray) {
                        if (nodeElement.isJsonObject()) {
                            JsonObject nodeObject = nodeElement.getAsJsonObject();

                            if (nodeObject.has("name") && nodeObject.get("name").getAsString().equals(fileName)) {
                                if (nodeObject.has("key")) {
                                    // Correctly index to get the key
                                    userFILE_KEY = nodeObject.get("key").getAsString();
                                    System.out.println("User File '" + fileName + "' found on File.io with key: " + userFILE_KEY);
                                } else {
                                    System.out.println("User File object found, but no key present for file: " + fileName);
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("No 'nodes' array found in the response. Response: " + response.body());
                }
            } else {
                System.out.println("Failed to get User file list from File.io. Status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error while searching for User file on File.io: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return userFILE_KEY; // File not found
    }

    /**
     * Deletes the file from File.io using the file key.
     */
    public void deleteFileFromFileIo() {
        if (userFILE_KEY.isEmpty()) {
            System.err.println("User File key is empty. Cannot delete file.");
            return;
        }

        System.out.println("Deleting User file from File.io with User File key: " + userFILE_KEY);
        try {
            String deleteUrl = FILE_IO_API_URL + "/" + URLEncoder.encode(userFILE_KEY, StandardCharsets.UTF_8);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(deleteUrl))
                    .DELETE()
                    .header("Authorization", "Bearer " + API_KEY)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("User File deleted successfully: " + response.body());
            } else {
                System.err.println("Failed to delete User file. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during User file deletion: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Writes the list of users in JSON format to a file.
     */
    public void writeUsersToFile(Map<String, User> allUsers) {
        System.out.println("Writing all users to JSON file.");
        final File file = new File(FILE_PATH);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonContent = gson.toJson(allUsers);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(jsonContent);
            System.out.println("All user data written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error while writing to User file: " + e.getMessage());
        }
    }

    public void uploadFileToFileIo() {
        System.out.println("Uploading file to File.io with Bearer Auth.");
        try {
            final HttpClient client = HttpClient.newHttpClient();
            String bearerToken = API_KEY; // Replace this with your actual token

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(FILE_IO_API_URL))
                    .header("Authorization", "Bearer " + bearerToken)
                    .header("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary")
                    .POST(ofFileUpload(Path.of(FILE_PATH)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("User File uploaded successfully: " + response.body());
                // Parse the response to extract the "key" value and set FILE_KEY
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                userFILE_KEY = jsonResponse.get("key").getAsString();
                System.out.println("User File key set to: " + userFILE_KEY);
            } else {
                System.err.println("Failed to upload User file. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during User file upload: " + e.getMessage());
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
        var boundary = "----WebKitFormBoundary";
        var fileBytes = Files.readAllBytes(path);
        var byteArrays = new ArrayList<byte[]>();

        byteArrays.add(("--" + boundary + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\""
                + path.getFileName() + "\"\r\nContent-Type: application/json\r\n\r\n").getBytes(StandardCharsets.UTF_8));
        byteArrays.add(fileBytes);
        byteArrays.add(("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

    @Override
    public void loadUsersFromCloud() {
        if (userFILE_KEY.isEmpty()) {
            System.err.println("User File key is empty. Cannot download user file.");
        }

        System.out.println("Downloading User file from File.io with key: " + userFILE_KEY);
        try {
            String downloadUrl = FILE_IO_API_URL + "/" + URLEncoder.encode(userFILE_KEY, StandardCharsets.UTF_8);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(downloadUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("User File downloaded successfully.");

                // Log the entire JSON content received
                String jsonContent = response.body();

                // Parse the downloaded JSON content
                users = parseDownloadedUsers(jsonContent);

                // Write parsed recipes back to the JSON file
                writeUsersToFile(users);

                // Upload the updated JSON file immediately
                uploadFileToFileIo();

            } else {
                System.err.println("Failed to download User file. Status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during user file download: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Parses the downloaded JSON content into a Map of users.
     *
     * @param jsonContent the JSON string containing the user data
     * @return a Map of users parsed from the JSON content
     */
    private Map<String, User> parseDownloadedUsers(String jsonContent) {
        System.out.println("Parsing downloaded users from JSON content...");

        Gson gson = new Gson();
        Map<String, User> parsedUsers = new HashMap<>();

        try {
            // Parse the JSON into a map of raw JSON objects
            Map<String, JsonObject> rawUsers = gson.fromJson(jsonContent, new TypeToken<Map<String, JsonObject>>() {}.getType());

            if (rawUsers != null && !rawUsers.isEmpty()) {
                for (Map.Entry<String, JsonObject> entry : rawUsers.entrySet()) {
                    String username = entry.getKey();
                    JsonObject userObject = entry.getValue();

                    // Extract necessary fields from the JSON object
                    String password = userObject.get("password").getAsString();
                    JsonArray likedRecipesArray = userObject.getAsJsonArray("likedRecipes");
                    JsonArray dislikedRecipesArray = userObject.getAsJsonArray("dislikedRecipes");
                    JsonArray favoriteRecipesArray = userObject.getAsJsonArray("favoriteRecipes");

                    // Use UserFactory to create the User object
                    UserFactory userFactory = new CommonUserFactory();
                    User user = userFactory.create(username, password);

                    // Populate additional fields (e.g., liked/disliked recipes, favorite recipes)
                    for (JsonElement recipe : likedRecipesArray) {
                        user.addLikedRecipe(recipe.getAsString());
                    }
                    for (JsonElement recipe : dislikedRecipesArray) {
                        user.addDislikedRecipe(recipe.getAsString());
                    }
                    String[] favoriteRecipes = new String[favoriteRecipesArray.size()];
                    for (int i = 0; i < favoriteRecipesArray.size(); i++) {
                        favoriteRecipes[i] = favoriteRecipesArray.get(i).isJsonNull() ? null : favoriteRecipesArray.get(i).getAsString();
                    }
                    user.setFavoriteRecipes(favoriteRecipes);

                    // Add the constructed user to the map
                    parsedUsers.put(username, user);
                }
                System.out.println("Successfully parsed users:");
                parsedUsers.forEach((key, value) -> System.out.println("User: " + key + ", Details: " + value));
            } else {
                System.out.println("No users found in the JSON content.");
            }
        } catch (JsonSyntaxException | IllegalStateException e) {
            System.err.println("Error parsing JSON content: " + e.getMessage());
        }

        return parsedUsers;
    }



    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
        if (!userFILE_KEY.isEmpty()) {
            deleteFileFromFileIo();
        }
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public Recipe getOneRecipe(String dishName) {
        return null;
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
        deleteFileFromFileIo();
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setFavoriteRecipes(String[] favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    @Override
    public void updateUserFavoriteRecipes(User user) {
        users.put(user.getName(), user);
        deleteFileFromFileIo();
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public boolean hasUserLikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        return currentUser.hasUserLikedRecipe(recipeName);
    }

    @Override
    public void addLikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        currentUser.addLikedRecipe(recipeName);
    }

    @Override
    public boolean hasUserDislikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        return currentUser.hasUserDislikedRecipe(recipeName);
    }

    @Override
    public void addDislikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        currentUser.addDislikedRecipe(recipeName);
    }

    @Override
    public void updateUserLikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        // Replace the old entry with the new password
        users.put(currentUser.getName(), currentUser);
        deleteFileFromFileIo();
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public void updateUserDislikedRecipe(String recipeName) {
        final User currentUser = get(getCurrentUsername());
        // Replace the old entry with the new password
        users.put(currentUser.getName(), currentUser);
        deleteFileFromFileIo();
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

}
