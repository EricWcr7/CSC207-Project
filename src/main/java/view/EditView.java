package view;

import com.google.gson.*;
import entity.User;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.delete.DeleteController;
import interface_adapter.edit.EditController;
import interface_adapter.edit.EditViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.JsonSyntaxException;



/**
 * The EditView class represents the graphical user interface for editing recipes.
 * It allows users to add, delete, and navigate recipes via a combo box and buttons.
 */
public class EditView extends JPanel implements ActionListener, PropertyChangeListener {

    // Name of the current view, used for UI navigation
    private final String viewName = "Edit recipe";

    // ViewModel containing state and data for this view
    private final EditViewModel editViewModel;

    // Controller to handle creating new recipes
    private EditController editController;

    // Controller to handle deleting recipes
    private DeleteController deleteController;

    // Controller to navigate back to the main search menu
    private ReturnToSearchMenuController returnToSearchMenuController;

    // ComboBox to display the list of recipes
    private final JComboBox<String> recipeComboBox = new JComboBox<>();

    // Buttons for adding, deleting, and going back
    private final JButton back;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton refreshButton;

    /**
     * Constructor for the EditView class.
     *
     * @param editViewModel The ViewModel associated with this view.
     */
    public EditView(EditViewModel editViewModel) {
        this.editViewModel = editViewModel;

        // Listen for property changes from the ViewModel
        this.editViewModel.addPropertyChangeListener(this);

        // Set layout for the main panel
        this.setLayout(new BorderLayout());

        // Create and add the title label at the top
        JLabel titleLabel = new JLabel("My Edit Recipe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set title font
        this.add(titleLabel, BorderLayout.NORTH);

        // Upper panel containing the combo box
        JPanel upperPanel = new JPanel(new FlowLayout());
        recipeComboBox.setPreferredSize(new Dimension(300, 30)); // Set size of the combo box
        upperPanel.add(recipeComboBox);
        this.add(upperPanel, BorderLayout.CENTER);

        // Lower panel containing buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        addButton = new JButton("+");
        back = new JButton("Back");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        buttonsPanel.add(addButton);
        buttonsPanel.add(back);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(refreshButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        back.addActionListener(evt -> {
            if (evt.getSource().equals(back)) {
                this.returnToSearchMenuController.fromEditRecipeBackToSearchMenu();
            }
        });

        addButton.addActionListener(evt -> {
            if (evt.getSource().equals(addButton)) {
                // Switch to the create recipe view
                this.editController.switchToCreate();
                // Refresh the combo box after adding a new recipe

            }

        });

        deleteButton.addActionListener(evt -> {
            // Get the selected recipe from the combo box
            String selectedRecipe = (String) recipeComboBox.getSelectedItem();

            if (selectedRecipe != null && !selectedRecipe.isEmpty()) {
                // Call the DeleteController to handle the deletion logic
                deleteController.deleteRecipe(selectedRecipe);
                deleteController.deleteRecipeFromUserCreatedRecipes(selectedRecipe);
                // Refresh the combo box after deleting a recipe

            } else {
                // Show an error message if no recipe is selected
                JOptionPane.showMessageDialog(this, "Please select a recipe to delete!");
            }

        });

        refreshButton.addActionListener(evt -> {
            loadCreatedRecipes();

        });

        recipeComboBox.addActionListener(evt -> {
            // Print the selected recipe to the console for debugging
            String selectedRecipe = (String) recipeComboBox.getSelectedItem();
            if (selectedRecipe != null) {
                System.out.println("Selected recipe: " + selectedRecipe);
            }
        });
    }

    /**
     * This class listens to user actions and property changes, providing a mechanism to handle events
     * triggered by the user interface and updates from the underlying ViewModel.
     *
     * The `actionPerformed` method handles user-triggered actions (e.g., button clicks),
     * and the `propertyChange` method is a placeholder for responding to property changes
     * in the application model. These methods ensure that the application's view layer reacts to user
     * interactions and model updates appropriately.
     *
     * This implementation currently focuses on debugging by logging action commands to the console
     * and providing a stub for handling property changes.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Log the action command to the console for debugging purposes.
        // Action commands are strings that uniquely identify the source or purpose of the event,
        // such as the label of the button clicked or the specific action triggered.
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // This method is intended to handle property changes from the ViewModel or other observable components.
        // The PropertyChangeEvent parameter contains details about the change, including the property name,
        // old value, and new value.
        // Currently, this method is not implemented but serves as a placeholder for future functionality.
    }


    /**
     * Retrieves the name of this view.
     *
     * @return The name of the view as a string.
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the EditController for this view.
     *
     * @param editController The EditController to handle editing logic.
     */
    public void setEditController(EditController editController) {
        this.editController = editController;
    }

    /**
     * Sets the ReturnToSearchMenuController for navigating back to the search menu.
     *
     * @param returnToSearchMenuController The controller to handle navigation.
     */
    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    /**
     * Sets the DeleteController for handling delete operations.
     *
     * @param deleteController The controller to handle recipe deletion logic.
     */
    public void setDeleteController(DeleteController deleteController) {
        this.deleteController = deleteController;
    }

    /**
     * Loads the recipes created by the current user from a JSON file and updates the recipe combo box.
     *
     * This method retrieves the current user from the session, fetches their data from the "all_users.json" file,
     * and extracts the array of recipes they have created. The method then updates the recipe combo box
     * with the loaded recipes.
     *
     * If the current user is not logged in or their data is not found, the method exits gracefully without action.
     * Any exceptions related to file reading or JSON parsing are caught and ignored to prevent crashes.
     */
    public void loadCreatedRecipes() {
        try {
            // Retrieve the current user from the session.
            User currentUser = util.Session.getCurrentUser();

            // If no user is logged in (null session), exit the method.
            if (currentUser == null) {
                return;
            }

            // Get the username of the current user for lookup in the JSON file.
            String username = currentUser.getName();

            // Try-with-resources block to safely open and read the "all_users.json" file.
            try (FileReader reader = new FileReader("all_users.json")) {

                // Parse the JSON file into a JsonObject.
                JsonObject allUsers = JsonParser.parseReader(reader).getAsJsonObject();

                // Look up the current user's data using their username as the key.
                JsonObject userJson = allUsers.getAsJsonObject(username);

                // If the user's data is not found in the file, exit the method.
                if (userJson == null) {
                    return;
                }

                // Extract the "recipeCreated" array from the user's data.
                JsonArray recipeCreatedArray = userJson.getAsJsonArray("recipeCreated");

                // Update the recipe combo box with the extracted array of recipes.
                updateRecipeComboBox(recipeCreatedArray);
            }
        }
        catch (IOException | JsonSyntaxException e) {
            // Catch any IO or JSON syntax errors and exit the method without taking further action.
            return;
        }
    }


    /**
     * Updates the items in the recipeComboBox based on the given JsonArray of created recipes.
     *
     * This method clears all existing items in the recipeComboBox, checks if the input array is valid
     * (not null or empty), and then iterates through the JsonArray to extract recipe names.
     * If the name field is found, it adds the recipe names to the combo box for display.
     *
     * The method ensures that both JSON objects with a "name" field and JSON primitives are handled.
     * Finally, it revalidates and repaints the combo box to reflect the changes.
     *
     * @param recipeCreatedArray A JsonArray containing recipe data. Each element can be a JsonObject
     *                           with a "name" field or a JsonPrimitive containing a string.
     */
    private void updateRecipeComboBox(JsonArray recipeCreatedArray) {

        // Clear all existing items in the recipeComboBox to prepare for new data.
        recipeComboBox.removeAllItems();

        // Check if the input array is null or empty. If so, exit the method early.
        if (recipeCreatedArray == null || recipeCreatedArray.size() == 0) {
            return;
        }

        // Iterate through each element in the JsonArray.
        for (JsonElement recipeElement : recipeCreatedArray) {

            // If the element is a JsonObject, check for a "name" field.
            if (recipeElement.isJsonObject()) {
                JsonObject recipeObject = recipeElement.getAsJsonObject();

                // If the JsonObject has a "name" key, extract the value as a string and add it to the combo box.
                if (recipeObject.has("name")) {
                    String recipeName = recipeObject.get("name").getAsString();
                    recipeComboBox.addItem(recipeName);
                }
            }
            // If the element is a JsonPrimitive (e.g., a raw string), directly add it to the combo box.
            else if (recipeElement.isJsonPrimitive()) {
                recipeComboBox.addItem(recipeElement.getAsString());
            }
        }

        // Revalidate and repaint the combo box to ensure the UI reflects the updated items.
        recipeComboBox.revalidate();
        recipeComboBox.repaint();
    }


}
