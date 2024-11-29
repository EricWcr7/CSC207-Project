package data_access;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import entity.CommonUserFactory;
import entity.Recipe;
import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.create.CreateUserDataAccessInterface;
import use_case.favorite_receipe.FavoriteRecipeDataAccessInterface;
import use_case.like_and_dislike_a_recipe.UserLikeAndDislikeDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.shopping_list.ShoppingListUserDataAccessInterface;
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
        ShoppingListUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        CreateUserDataAccessInterface {

    private static final String FILE_IO_API_URL = "https://file.io";
    private static final String API_KEY = "35F52QF.ZQV4A4E-ASHMAQD-QSPTZ93-NHYCJT6";
    private static final int STATUS_CODE_OK = 200;
    private static final String FILE_PATH = "all_users.json";
    private static String userFileKey = "";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String NODES = "nodes";
    private static final String NAME = "name";
    private static final String KEY = "key";
    private Map<String, User> users = new HashMap<>();
    private String currentUsername;
    private String username;
    private String[] favoriteRecipes;

    @Override
    public String findFileOnFileIo(String fileName) {
        try {
            // Initialize recipeFileKey to null for a single return statement at the end
            userFileKey = "";

            // Properly format the search URL with the provided file name
            final String searchUrl = FILE_IO_API_URL + "/?search=" + URLEncoder.encode(
                    fileName, StandardCharsets.UTF_8);
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(searchUrl))
                    .header("accept", "application/json")
                    .header(AUTHORIZATION, BEARER + API_KEY)
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                // Parse the response
                final JsonObject responseObject = JsonParser.parseString(response.body()).getAsJsonObject();
                if (responseObject.has(NODES) && responseObject.get(NODES).isJsonArray()) {
                    final JsonArray nodesArray = responseObject.getAsJsonArray(NODES);
                    processNodes(nodesArray, fileName);
                }
                else {
                    System.out.println("No 'nodes' array found in the response. Response: " + response.body());
                }
            }
            else {
                System.out.println("Failed to get user file list from File.io. Status code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error while searching for file on File.io: " + ex.getMessage());
            Thread.currentThread().interrupt();
        }

        // Single return statement
        return userFileKey;
    }

    /**
     * Processes a JSON array of nodes to locate a specific file object by its name and retrieve its associated key.
     * This method iterates through the provided JSON array, checks each element to see if it is a JSON object,
     * and looks for a node matching the specified file name. If a matching node is found and contains a key,
     * the key's value is assigned to the `userFileKey` field. Messages are logged to indicate the result
     * of the search operation, whether the file and key were found or if the file exists without a key.
     *
     * @param nodesArray the JSON array containing node objects to be processed
     * @param fileName   the name of the file to search for within the node objects
     */
    private void processNodes(JsonArray nodesArray, String fileName) {
        boolean fileFound = false;

        for (JsonElement nodeElement : nodesArray) {
            if (nodeElement.isJsonObject()) {
                final JsonObject nodeObject = nodeElement.getAsJsonObject();

                if (nodeObject.has(NAME) && nodeObject.get(NAME).getAsString().equals(fileName)) {
                    if (nodeObject.has(KEY)) {
                        userFileKey = nodeObject.get(KEY).getAsString();
                        System.out.println("File '" + fileName + "' found on File.io with key: " + userFileKey);
                    }
                    else {
                        System.out.println("File object found, but no key present for file: " + fileName);
                    }
                    fileFound = true;
                    break;
                }
            }
        }

        if (!fileFound) {
            System.out.println("File '" + fileName + "' not found in the nodes.");
        }
    }

    /**
     * Deletes a file from File.io using its unique file key.
     * This method constructs and sends a DELETE request to the File.io API to remove a file identified
     * by its key. If the file key is empty, it logs an error and does not attempt to send the request.
     * In the case of a successful deletion, a success message is logged. If the deletion fails, the
     * method logs the HTTP status code and response body. Any exceptions during the process are caught
     * and logged, with the thread being interrupted for an InterruptedException.
     * Preconditions:
     * - The `userFileKey` field must be initialized with a valid file key.
     */
    private void deleteFileFromFileIo() {
        if (!userFileKey.isEmpty()) {
            System.out.println("Deleting User file from File.io with User File key: " + userFileKey);
            try {
                final String deleteUrl = FILE_IO_API_URL + "/" + URLEncoder.encode(userFileKey, StandardCharsets.UTF_8);
                final HttpClient client = HttpClient.newHttpClient();
                final HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(deleteUrl))
                        .DELETE()
                        .header(AUTHORIZATION, BEARER + API_KEY)
                        .build();

                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == STATUS_CODE_OK) {
                    System.out.println("User File deleted successfully: " + response.body());
                }
                else {
                    System.err.println("Failed to delete User file. Status code: " + response.statusCode());
                    System.err.println("Response body: " + response.body());
                }
            }
            catch (IOException | InterruptedException ex) {
                System.err.println("Error during User file deletion: " + ex.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        else {
            System.err.println("User File key is empty. Cannot delete file.");
        }
    }

    /**
     * Writes a map of users to a file in JSON format.
     * This method serializes the provided map of user objects to JSON format using the Gson library
     * and writes the resulting JSON string to a file specified by the constant {@code FILE_PATH}.
     * If the write operation is successful, a confirmation message is logged to the console.
     * In case of an I/O error during the process, an error message is logged.
     *
     * @param allUsers a map where the key is a string representing the user's identifier and the value is
     *                 the corresponding {@link User} object to be serialized and saved
     */
    private void writeUsersToFile(Map<String, User> allUsers) {
        System.out.println("Writing all users to JSON file.");

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String jsonContent = gson.toJson(allUsers);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(jsonContent);
            System.out.println("All user data written to file successfully.");
        }
        catch (IOException ex) {
            System.err.println("Error while writing to User file: " + ex.getMessage());
        }
    }

    /**
     * Uploads a file to File.io using a POST request with Bearer authentication.
     * This method sends a file located at the path specified by the {@code FILE_PATH} constant
     * to the File.io API using the HTTP POST method. The request includes a Bearer token for
     * authorization and uses a multipart/form-data content type. On a successful upload, the
     * method parses the API response to retrieve the file key and sets it to the {@code userFileKey} field.
     * If the upload fails, it logs the HTTP status code and response body. Any exceptions
     * during the process are caught and logged, with the thread being interrupted if necessary.
     * Preconditions:
     * - The file at {@code FILE_PATH} must exist and be accessible.
     * - The {@code API_KEY} constant must contain a valid API key for File.io.
     */
    private void uploadFileToFileIo() {
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
                System.out.println("User File uploaded successfully: " + response.body());
                // Parse the response to extract the "key" value and set FILE_KEY
                final JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                userFileKey = jsonResponse.get(KEY).getAsString();
                System.out.println("User File key set to: " + userFileKey);
            }
            else {
                System.err.println("Failed to upload User file. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error during User file upload: " + ex.getMessage());
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

    @Override
    public void loadUsersFromCloud() {
        if (userFileKey.isEmpty()) {
            System.err.println("User File key is empty. Cannot download user file.");
        }

        System.out.println("Downloading User file from File.io with key: " + userFileKey);
        try {
            final String downloadUrl = FILE_IO_API_URL + "/" + URLEncoder.encode(userFileKey, StandardCharsets.UTF_8);
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(downloadUrl))
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("User File downloaded successfully.");

                // Log the entire JSON content received
                final String jsonContent = response.body();

                // Parse the downloaded JSON content
                users = parseDownloadedUsers(jsonContent);

                // Write parsed recipes back to the JSON file
                writeUsersToFile(users);

                // Upload the updated JSON file immediately
                uploadFileToFileIo();

            }
            else {
                System.err.println("Failed to download User file. Status code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("Error during user file download: " + ex.getMessage());
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

        final Gson gson = new Gson();
        final Map<String, User> parsedUsers = new HashMap<>();

        try {
            // Parse the JSON into a map of raw JSON objects
            final Map<String, JsonObject> rawUsers = gson.fromJson(
                    jsonContent, new TypeToken<Map<String, JsonObject>>() { }.getType()
            );

            if (rawUsers != null && !rawUsers.isEmpty()) {
                for (Map.Entry<String, JsonObject> entry : rawUsers.entrySet()) {
                    final String userName = entry.getKey();
                    final JsonObject userObject = entry.getValue();

                    // Extract necessary fields from the JSON object
                    final String password = userObject.get("password").getAsString();
                    final JsonArray likedRecipesArray = userObject.getAsJsonArray("likedRecipes");
                    final JsonArray dislikedRecipesArray = userObject.getAsJsonArray("dislikedRecipes");
                    final JsonArray favoriteRecipesArray = userObject.getAsJsonArray("favoriteRecipes");

                    // Use UserFactory to create the User object
                    final UserFactory userFactory = new CommonUserFactory();
                    final User user = userFactory.create(userName, password);

                    // Populate additional fields (e.g., liked/disliked recipes, favorite recipes)
                    for (JsonElement recipe : likedRecipesArray) {
                        user.addLikedRecipe(recipe.getAsString());
                    }
                    for (JsonElement recipe : dislikedRecipesArray) {
                        user.addDislikedRecipe(recipe.getAsString());
                    }

                    // Populate favorite recipes
                    final String[] favoriterecipes = new String[favoriteRecipesArray.size()];
                    for (int i = 0; i < favoriteRecipesArray.size(); i++) {
                        final JsonElement recipeElement = favoriteRecipesArray.get(i);
                        if (recipeElement.isJsonNull()) {
                            favoriterecipes[i] = null;
                        }
                        else {
                            favoriterecipes[i] = recipeElement.getAsString();
                        }
                    }
                    user.setFavoriteRecipes(favoriterecipes);

                    // Add the constructed user to the map
                    parsedUsers.put(userName, user);
                }
                System.out.println("Successfully parsed users:");
                parsedUsers.forEach((key, value) -> {
                    System.out.println("User: " + key + ", Details: " + value);
                });
            }
            else {
                System.out.println("No users found in the JSON content.");
            }
        }
        catch (JsonSyntaxException | IllegalStateException ex) {
            System.err.println("Error parsing JSON content: " + ex.getMessage());
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
        if (!userFileKey.isEmpty()) {
            deleteFileFromFileIo();
        }
        writeUsersToFile(users);
        uploadFileToFileIo();
    }

    @Override
    public User get(String userName) {
        return users.get(userName);
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

    @Override
    public void addCreatedRecipe(Recipe recipe) {
        final User currentUser = get(getCurrentUsername());
        currentUser.addCreatedRecipe(recipe);
    }
}
