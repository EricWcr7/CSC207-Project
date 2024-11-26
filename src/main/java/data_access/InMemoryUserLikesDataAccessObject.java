package data_access;

import use_case.like_and_dislike_a_recipe.UserLikesDataAccessInterface;

import java.util.HashSet;
import java.util.Set;

public class InMemoryUserLikesDataAccessObject implements UserLikesDataAccessInterface {
    // Set to store user likes, represented as a combination of userName and recipeName
    private final Set<String> userLikes = new HashSet<>();

    /**
     * Checks if a user has already liked a specific recipe.
     *
     * @param userName   the name of the user
     * @param recipeName the name of the recipe
     * @return true if the user has liked the recipe before; false otherwise
     */
    @Override
    public boolean hasUserLikedRecipe(String userName, String recipeName) {
        // Use a unique key based on userName and recipeName
        String key = generateKey(userName, recipeName);
        return userLikes.contains(key);
    }

    /**
     * Adds a like record indicating that a user has liked a specific recipe.
     *
     * @param userName   the name of the user
     * @param recipeName the name of the recipe
     */
    @Override
    public void addUserLike(String userName, String recipeName) {
        // Use a unique key based on userName and recipeName
        String key = generateKey(userName, recipeName);
        userLikes.add(key);
    }

    /**
     * Generates a unique key for a user-recipe combination.
     *
     * @param userName   the name of the user
     * @param recipeName the name of the recipe
     * @return a unique key as a string
     */
    private String generateKey(String userName, String recipeName) {
        return userName + "::" + recipeName; // Separator "::" ensures unique keys
    }
}
