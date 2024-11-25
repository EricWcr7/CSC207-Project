package view;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.delete_recipe.DeleteController;
import interface_adapter.delete_recipe.DeleteState;
import interface_adapter.delete_recipe.DeleteViewModel;
import use_case.delete_recipe.DeleteOutputData;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class DeleteRecipeView extends JPanel implements PropertyChangeListener {
    private final String viewName = "delete recipe";
    private DeleteViewModel deleteViewModel;

    private String dishName;
    private String ingredients;
    private String instructions;

    private final JTextArea ingredientsArea;
    private final JTextArea instructionArea;

    private final JButton returnToSearchMenu;
    private final JButton deleteButton;

    private ReturnToSearchMenuController returnToSearchMenuController;
    private DeleteController deleteController;

    public DeleteRecipeView(DeleteViewModel deleteViewModel) {
        this.deleteViewModel = deleteViewModel;
        this.deleteViewModel.addPropertyChangeListener(this);
        this.dishName = dishName;
        this.ingredients = ingredients;
        this.instructions = instructions;

        ingredientsArea = new JTextArea(ingredients);
        ingredientsArea.setEditable(false);
        instructionArea = new JTextArea(instructions);
        instructionArea.setEditable(false);
        instructionArea.setLineWrap(true);
        instructionArea.setWrapStyleWord(true);

        returnToSearchMenu = new JButton("Return to Search View");
        deleteButton = new JButton("Delete Recipe");

        // Use BoxLayout for a cleaner vertical layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Panel for recipe details
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS)); // Vertical layout for recipe details
        recipePanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align it to the left

        // Dish name
        JLabel dishNameLabel = new JLabel("Dish Name: ");
        dishNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recipePanel.add(dishNameLabel);

        JLabel dishNameValue = new JLabel(dishName);
        recipePanel.add(dishNameValue);

        // Ingredients
        JLabel ingredientsLabel = new JLabel("Ingredients: ");
        ingredientsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recipePanel.add(ingredientsLabel);
        recipePanel.add(ingredientsArea);

        // Instructions
        JLabel instructionsLabel = new JLabel("Instructions: ");
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recipePanel.add(instructionsLabel);
        recipePanel.add(instructionArea);

        // Panel for buttons (aligned horizontally)
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(returnToSearchMenu);
        buttonsPanel.add(deleteButton);

        // Add recipe details and buttons panels to the main view
        add(recipePanel);
        add(buttonsPanel);

        // Action listener for return to search menu button
        returnToSearchMenu.addActionListener(
                evt -> {
                    if (evt.getSource().equals(returnToSearchMenu)) {
                        final DeleteState currentState = deleteViewModel.getState();
                        this.returnToSearchMenuController.execute();
                    }
                }
        );

        // Action listener for delete button
        deleteButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(deleteButton)) {
                        DeleteOutputData deleteOutputData = this.deleteController.deleteRecipe(dishName);
                        if (deleteOutputData.isSuccess()) {
                            JOptionPane.showMessageDialog(this, deleteOutputData.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, deleteOutputData.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
    }

    public String getViewName() {
        return viewName;
    }

    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    public void setDeleteController(DeleteController deleteController) {
        this.deleteController = deleteController;
    }
}

