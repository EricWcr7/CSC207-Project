package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interface_adapter.return_to_recipe_search_view.ReturnToSearchMenuController;
import interface_adapter.shopping_list.ShoppingListController;
import interface_adapter.shopping_list.ShoppingListViewModel;

import java.awt.*;

import javax.swing.*;

/**
 * The ShoppingListView class represents the graphical user interface (GUI) for displaying
 * the shopping list to the user. It extends `JPanel` and listens for property changes in
 * the associated `ShoppingListViewModel` to dynamically update the UI.
 * This class provides interactive elements, such as buttons for returning to the search menu
 * and generating a shopping list, and displays the list of ingredients in a text area.
 * In the Clean Architecture framework, this class resides in the interface adapter layer,
 * bridging the view model and the actual UI components.
 */
public class ShoppingListView extends JPanel implements PropertyChangeListener {
    private final String viewName = "The Overall Shopping List";
    private ShoppingListViewModel shoppingListViewModel;

    private ReturnToSearchMenuController returnToSearchMenuController;
    private ShoppingListController shoppingListController;

    private final JPanel shoppingListPanel;
    private final JButton returnToSearchMenu;
    private final JButton generateShoppingList;

    public ShoppingListView(ShoppingListViewModel shoppingListViewModel) {
        this.shoppingListViewModel = shoppingListViewModel;
        this.shoppingListViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout(10, 10)); // Add spacing between components

        // Title Panel
        JLabel titleLabel = new JLabel("Shopping List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Shopping List Panel
        shoppingListPanel = new JPanel();
        shoppingListPanel.setLayout(new BoxLayout(shoppingListPanel, BoxLayout.Y_AXIS));
        shoppingListPanel.setBorder(BorderFactory.createTitledBorder("Ingredients"));

        JScrollPane scrollPane = new JScrollPane(shoppingListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        returnToSearchMenu = new JButton("Return to Search View");
        generateShoppingList = new JButton("Generate Shopping List");
        buttonPanel.add(returnToSearchMenu);
        buttonPanel.add(generateShoppingList);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        returnToSearchMenu.addActionListener(evt -> {
            if (evt.getSource().equals(returnToSearchMenu)) {
                this.returnToSearchMenuController.fromShoppingListBackToSearchMenu();
            }
        });

        generateShoppingList.addActionListener(evt -> {
            if (evt.getSource().equals(generateShoppingList)) {
                shoppingListController.execute();
            }
        });

        updateView();
    }

    /**
     * Updates the shopping list panel with the latest ingredients from the view model.
     * If the ingredient set is empty or null, the panel displays a message indicating
     * that no ingredients are available. Otherwise, it lists each ingredient in a centered label.
     */
    private void updateView() {
        shoppingListPanel.removeAll(); // Clear the panel
        final Set<String> ingredients = shoppingListViewModel.getIngredients();
        if (ingredients != null && !ingredients.isEmpty()) {
            for (String ingredient : ingredients) {
                JLabel ingredientLabel = new JLabel(ingredient, SwingConstants.CENTER);
                ingredientLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                ingredientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                shoppingListPanel.add(ingredientLabel);
            }
        } else {
            JLabel noIngredientsLabel = new JLabel("No ingredients available", SwingConstants.CENTER);
            noIngredientsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noIngredientsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            shoppingListPanel.add(noIngredientsLabel);
        }
        shoppingListPanel.revalidate();
        shoppingListPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateView();
    }

    public String getViewName() {
        return viewName;
    }

    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    public void setShoppingListController(ShoppingListController shoppingListController) {
        this.shoppingListController = shoppingListController;
    }
}
