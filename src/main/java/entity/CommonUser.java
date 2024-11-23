package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final ArrayList<Recipe> recipeCreated;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.recipeCreated = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void addCreatedRecipe(Recipe recipe) {
        recipeCreated.add(recipe);
    }

}
