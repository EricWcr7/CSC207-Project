package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.shopping_list.ShoppingListController;
import interface_adapter.shopping_list.ShoppingListState;
import interface_adapter.shopping_list.ShoppingListViewModel;

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

    private final JTextArea shoppingListTextArea;

    private final JButton returnToSearchMenu;
    private final JButton generateShoppingList;

    public ShoppingListView(ShoppingListViewModel shoppingListViewModel) {
        this.shoppingListViewModel = shoppingListViewModel;
        this.shoppingListViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        final JPanel shoppingListPanel = new JPanel();
        shoppingListPanel.setLayout(new BoxLayout(shoppingListPanel, BoxLayout.Y_AXIS));
        shoppingListPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        shoppingListTextArea = new JTextArea();
        shoppingListTextArea.setEditable(false);
        add(new JScrollPane(shoppingListTextArea), BorderLayout.CENTER);
        updateView();
        shoppingListTextArea.setEditable(false);
        shoppingListTextArea.setEditable(false);
        shoppingListTextArea.setLineWrap(true);
        shoppingListTextArea.setWrapStyleWord(true);
        shoppingListPanel.add(shoppingListTextArea);
        
        returnToSearchMenu = new JButton("Return to Search View");
        generateShoppingList = new JButton("Generate Shopping List");
        
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnToSearchMenu);
        buttonPanel.add(generateShoppingList);
        
        add(shoppingListPanel);
        add(buttonPanel);

        returnToSearchMenu.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(returnToSearchMenu)) {
                        this.returnToSearchMenuController.fromShoppingListBackToSearchMenu();
                    }
                }
        );

        generateShoppingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(generateShoppingList)) {
                    final ShoppingListState currentState = shoppingListViewModel.getState();
                    shoppingListController.execute(currentState.getUsername(),
                            currentState.getRecipeNames());
                }
            }
        });
    }

    /**
     * Updates the shopping list text area with the latest ingredients from the view model.
     * If the ingredient set is empty or null, the text area displays a message indicating
     * that no ingredients are available. Otherwise, it lists each ingredient in the text area.
     */
    private void updateView() {
        final Set<String> ingredients = shoppingListViewModel.getIngredients();
        System.out.println("Ingredients Set: " + ingredients);
        shoppingListTextArea.setText("");
        if (ingredients != null && !ingredients.isEmpty()) {
            for (String ingredient : ingredients) {
                shoppingListTextArea.append(ingredient + "\n");
            }
        }
        else {
            shoppingListTextArea.setText("No ingredients available");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property Change Event received with new state: " + evt.getNewValue());
        // Update view when DisplayRecipeState changes
        updateView();
    }

    /**
     * Retrieves the name of this view.
     *
     * @return the name of the shopping list view, typically used for identifying the view
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the controller responsible for handling the action of returning to the search menu.
     *
     * @param returnToSearchMenuController the controller for managing the return to search menu action
     */
    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    /**
     * Sets the controller responsible for managing the generation of the shopping list.
     *
     * @param shoppingListController the controller for executing the shopping list generation
     */
    public void setShoppingListController(ShoppingListController shoppingListController) {
        this.shoppingListController = shoppingListController;
    }
}

