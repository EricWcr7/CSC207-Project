package interface_adapter.favorite_recipe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The state for the FavoriteRecipe View Model.
 */
public class FavoriteRecipeState {
    private final int six = 6;
    private String username = "";
    private String[] favoriteRecipes = new String[six];
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public FavoriteRecipeState() {
        // Ensure favoriteRecipes array is initialized with non-null values
        for (int i = 0; i < six; i++) {
            favoriteRecipes[i] = "null";
            // Initialize each element with an empty string to avoid nulls
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }

    /**
     * Set new favoriteRecipes in FavoriteRecipeState.
     * @param favoriteRecipes the list of favoriteRecipes.
     */
    public void setFavoriteRecipes(String[] favoriteRecipes) {
        final String[] oldValue = this.favoriteRecipes;
        this.favoriteRecipes = favoriteRecipes;
        support.firePropertyChange("favoriteRecipes", oldValue, favoriteRecipes);
        // Notify listeners
    }

    /**
     * Set new favoriteRecipes in FavoriteRecipeState.
     * @param listener the listener any change about favoriteRecipe view.
     */
    // Add a PropertyChangeListener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
