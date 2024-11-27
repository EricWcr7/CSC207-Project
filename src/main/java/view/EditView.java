package view;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.create.CreateController;
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

        buttonsPanel.add(addButton);
        buttonsPanel.add(back);
        buttonsPanel.add(deleteButton);
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
                loadNewRecipes();
            }
        });

        deleteButton.addActionListener(evt -> {
            // Get the selected recipe from the combo box
            String selectedRecipe = (String) recipeComboBox.getSelectedItem();

            if (selectedRecipe != null && !selectedRecipe.isEmpty()) {
                // Call the DeleteController to handle the deletion logic
                deleteController.deleteRecipe(selectedRecipe);
                // Refresh the combo box after deleting a recipe
                loadNewRecipes();
            } else {
                // Show an error message if no recipe is selected
                JOptionPane.showMessageDialog(this, "Please select a recipe to delete!");
            }
        });

        recipeComboBox.addActionListener(evt -> {
            // Print the selected recipe to the console for debugging
            String selectedRecipe = (String) recipeComboBox.getSelectedItem();
            if (selectedRecipe != null) {
                System.out.println("Selected recipe: " + selectedRecipe);
            }
        });

        // Load the initial list of recipes into the combo box
        loadNewRecipes();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Print the action command to the console for debugging
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes from the ViewModel (not yet implemented)
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
        this.deleteController = deleteController; // Correctly initialize the DeleteController
    }

    /**
     * Loads the list of recipes from the "new_recipes.json" file
     * and updates the combo box with the recipe names.
     */
    public void loadNewRecipes() {
        try (FileReader reader = new FileReader("new_recipes.json")) {
            // Parse the JSON file into a JsonObject
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray recipesArray = jsonObject.getAsJsonArray("recipes");

            // Clear the existing items in the combo box
            recipeComboBox.removeAllItems();

            // Iterate through the JSON array and add recipe names to the combo box
            for (int i = 0; i < recipesArray.size(); i++) {
                JsonObject recipe = recipesArray.get(i).getAsJsonObject();
                String recipeName = recipe.get("name").getAsString(); // Extract recipe name
                recipeComboBox.addItem(recipeName); // Add recipe name to the combo box
            }

            // Refresh the combo box to reflect the changes
            recipeComboBox.revalidate();
            recipeComboBox.repaint();
        } catch (IOException e) {
            // Print the stack trace and show an error message if the file cannot be loaded
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading recipes from new_recipes.json!");
        }
    }
}
