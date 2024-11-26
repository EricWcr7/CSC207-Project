package view;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.shopping_list.ShoppingListController;
import interface_adapter.shopping_list.ShoppingListState;
import interface_adapter.shopping_list.ShoppingListViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class ShoppingListView extends JPanel implements PropertyChangeListener {
    private final String viewName = "The Overall Shopping List";
    private ShoppingListViewModel shoppingListViewModel;

    private ReturnToSearchMenuController returnToSearchMenuController;
    private ShoppingListController shoppingListController;

    private final JTextArea shoppingListTextArea;

    private final JButton returnToSearchMenu;

    public ShoppingListView(ShoppingListViewModel shoppingListViewModel) {
        this.shoppingListViewModel = shoppingListViewModel;
        this.shoppingListViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel shoppingListPanel = new JPanel();
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
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnToSearchMenu);
        
        add(shoppingListPanel);
        add(buttonPanel);

        returnToSearchMenu.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(returnToSearchMenu)) {
                        final ShoppingListState currentState = shoppingListViewModel.getState();
                        this.returnToSearchMenuController.execute("", currentState.getUsername(),
                                currentState.getFavouriteRecipes());
                    }
                }
        );
    }

    private void updateView() {
        StringBuilder shoppingListBuilder = new StringBuilder();
        for (Map.Entry<String, Double> entry : shoppingListViewModel.getIngredients().entrySet()) {
            shoppingListBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        shoppingListTextArea.setText(shoppingListBuilder.toString());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property Change Event received with new state: " + evt.getNewValue());
        // Update view when DisplayRecipeState changes
        final ShoppingListState shoppingListState = (ShoppingListState) evt.getNewValue();
        update(evt);
    }
    private void update(PropertyChangeEvent evt) {
        if ("ingredientQuantity".equals(evt.getPropertyName())) {
            updateView();
        }
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

