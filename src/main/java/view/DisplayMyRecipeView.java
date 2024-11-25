package view;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.delete_a_recipe.DeleteRecipeController;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.display_recipe.DisplayRecipeViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class DisplayMyRecipeView extends JPanel implements PropertyChangeListener {
    private final String viewName = "display the recipe";
    private DisplayRecipeViewModel displayRecipeViewmodel;

    private ReturnToSearchMenuController returnToSearchMenuController;
    private DeleteRecipeController deleteRecipeController;

    // Example: Dynamic data loaded into variables
    private String dishName;
    private String ingredients;
    private String instructions;

    private final JTextArea ingredientsArea;
    private final JTextArea instructionArea;

    private final JButton returnToSearchMenu;
    private final JButton deleteButton;

    public DisplayMyRecipeView(DisplayRecipeViewModel displayRecipeViewModel) {
        this.displayRecipeViewmodel = displayRecipeViewModel;
        this.displayRecipeViewmodel.addPropertyChangeListener(this);

        System.out.println("At DisplayRecipeView, the dishname is: " + dishName);

        ingredientsArea = new JTextArea(ingredients);
        ingredientsArea.setEditable(false);
        instructionArea = new JTextArea(instructions);
        instructionArea.setEditable(false);
        instructionArea.setLineWrap(true); // 启用自动换行
        instructionArea.setWrapStyleWord(true); // 按单词换行，而不是在字符中间

        returnToSearchMenu = new JButton("Return to Search View ");

        deleteButton = new JButton("Delete");

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

        returnToSearchMenu.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(returnToSearchMenu)) {
                        this.returnToSearchMenuController.execute();
                    }
                }
        );

        deleteButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(deleteButton)) {
                        final DisplayRecipeState state = displayRecipeViewModel.getState();
                        try {
                            this.deleteRecipeController.execute(state.getDishName());
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(this,
                                    "Failed to delete the recipe: " + e.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
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

    public void setDeleteRecipeController(DeleteRecipeController deleteRecipeController) {
        this.deleteRecipeController = deleteRecipeController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property Change Event received with new state: " + evt.getNewValue());
        // Update view when DisplayRecipeState changes
        final DisplayRecipeState displayRecipeState = (DisplayRecipeState) evt.getNewValue();

        update(displayRecipeState);

    }

    private void update(DisplayRecipeState displayRecipeState) {
        // Update UI components with the new state values
        dishName = displayRecipeState.getDishName();
        ingredients = displayRecipeState.getIngredients();
        instructions = displayRecipeState.getInstructions();

        // Update the labels and text areas with the new values
        ((JLabel) ((JPanel) this.getComponent(0)).getComponent(0)).setText("Dish Name: " + dishName);
        ingredientsArea.setText(ingredients);
        instructionArea.setText(formatInstructions(instructions));
    }

    // Format instructions to display them in a more readable way(之前句子太长了，一个屏幕装不下)
    String formatInstructions(String instructions) {
        // 根据句号、感叹号或问号分割句子
        final String[] sentences = instructions.split("(?<=[.!?])\\s*");
        // 用换行符拼接句子
        return String.join("\n", sentences);
    }

    private JPanel createButtonWithLabel(JButton button, JLabel label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(button, BorderLayout.WEST); // 按钮在左侧
        panel.add(label, BorderLayout.EAST); // 数字在右侧
        return panel;
    }

}