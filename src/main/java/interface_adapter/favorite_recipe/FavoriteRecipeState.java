package interface_adapter.favorite_recipe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The state for the FavoriteRecipe View Model.
 */
public class FavoriteRecipeState {
    private final int six = 6;
    private String username;
    private String[] favoriteRecipes = new String[six];
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(String[] favoriteRecipes) {
        final String[] oldValue = this.favoriteRecipes;
        this.favoriteRecipes = favoriteRecipes;
        support.firePropertyChange("favoriteRecipes", oldValue, favoriteRecipes); // Notify listeners
    }

    // Add a PropertyChangeListener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Remove a PropertyChangeListener
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    // Getter for state value
    public String[] getStateValue() {
        return favoriteRecipes;
    }

    // Setter for state value (notifies listeners of changes)
    public void setStateValue(String[] newValue) {
        final String[] oldValue = this.favoriteRecipes;
        this.favoriteRecipes = newValue;
        support.firePropertyChange("stateValue", oldValue, newValue); // Notify listeners
    }
}
