package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.return_to_RecipeSearchView.ReturnToSearchMenuController;
import interface_adapter.choose_recipe.ChooseRecipeController;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;

public class ChooseRecipeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "choose recipe";
    private final ChooseRecipeViewModel chooseRecipeViewModel;
    private ChooseRecipeController chooseRecipeController;
    private ReturnToSearchMenuController returnToSearchMenuController;
    private final JButton returnToSearchMenu;
    private final JTextField dishInputField = new JTextField(50);
    private final JComboBox<String> recipeComboBox = new JComboBox<>(); // ComboBox to hold recipe options

    public ChooseRecipeView(ChooseRecipeViewModel chooseRecipeViewModel) {
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.chooseRecipeViewModel.addPropertyChangeListener(this);

        returnToSearchMenu = new JButton("Return to Search View");

        // Set up search panel with input field and return button
        final JPanel searchPanel = new JPanel();
        searchPanel.add(dishInputField);
        searchPanel.add(returnToSearchMenu);

        // Set layout for the main panel
        this.setLayout(new FlowLayout());
        this.add(searchPanel);
        this.add(recipeComboBox); // Add the combo box to the main panel

        // Set up listener for the "Return to Search View" button
        returnToSearchMenu.addActionListener(evt -> {
            if (evt.getSource().equals(returnToSearchMenu)) {
                final ChooseRecipeState currentState = chooseRecipeViewModel.getState();
                this.returnToSearchMenuController.fromChooseRecipeBackToSearchMenu();
            }
        });

        // Set up ActionListener for JComboBox to handle selection events
        recipeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (recipeComboBox.getSelectedItem() != null) {
                    String selectedRecipe = (String) recipeComboBox.getSelectedItem();
                    System.out.println("Selected recipe: " + selectedRecipe);
                    // Future: Add logic here to display recipe details or trigger other actions
                    final ChooseRecipeState currentState = chooseRecipeViewModel.getState();
                    chooseRecipeController.execute(selectedRecipe,
                            currentState.getUsername(), currentState.getFavoriteRecipes());
                }
            }
        });
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property Change Event received with new state: " + evt.getNewValue());
        // Update view when ChooseRecipeState changes
        final ChooseRecipeState chooseRecipeState = (ChooseRecipeState) evt.getNewValue();
        setFields(chooseRecipeState);
    }

    private void setFields(ChooseRecipeState chooseRecipeState) {
        dishInputField.setText(chooseRecipeState.getSearchKeyword());

        // Clear existing items in the combo box to display new recipes
        recipeComboBox.removeAllItems();

        // Populate the combo box with the recipe names
        List<String> recipeNames = chooseRecipeState.getRecipeNames();
        for (String recipeName : recipeNames) {
            recipeComboBox.addItem(recipeName);
        }

        // Refresh the combo box to display updated recipes
        recipeComboBox.revalidate();
        recipeComboBox.repaint();
    }

    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    public void setChooseRecipeController(ChooseRecipeController chooseRecipeController) {
        this.chooseRecipeController = chooseRecipeController;
    }
}



