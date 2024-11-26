package interface_adapter.delete_recipe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The state for the DeleteRecipe View Model.
 */
public class DeleteState {
    private String username;
    private String recipeName;
    private String ingredients;
    private String instructions;
    private boolean isDeleted;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        final boolean oldValue = this.isDeleted;
        this.isDeleted = deleted;
        support.firePropertyChange("isDeleted", oldValue, deleted); // Notify listeners
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
    public boolean getStateValue() {
        return isDeleted;
    }

    // Setter for state value (notifies listeners of changes)
    public void setStateValue(boolean newValue) {
        final boolean oldValue = this.isDeleted;
        this.isDeleted = newValue;
        support.firePropertyChange("stateValue", oldValue, newValue); // Notify listeners
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRecipeName(String recipeName) {
        final String oldValue = this.recipeName;
        this.recipeName = recipeName;
        support.firePropertyChange("recipeName", oldValue, recipeName); // Notify listeners
    }

    public void setIngredients(String ingredients) {
        final String oldValue = this.ingredients;
        this.ingredients = ingredients;
        support.firePropertyChange("ingredients", oldValue, ingredients);
    }

    public void setInstructions(String instructions) {
        final String oldValue = this.instructions;
        this.instructions = instructions;
        support.firePropertyChange("instructions", oldValue, instructions);
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}

