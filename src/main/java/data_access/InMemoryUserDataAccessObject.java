package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.like_and_dislike_a_recipe.UserLikeAndDislikeDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        UserLikeAndDislikeDataAccessInterface,
        LogoutUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
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

}
