package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.choose_recipe.ChooseRecipeController;
import interface_adapter.favorite_recipe.FavoriteRecipeController;
import interface_adapter.favorite_recipe.FavoriteRecipeState;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;
import interface_adapter.return_to_recipe_search_view.ReturnToSearchMenuController;

/**
 * The View for when the user is going to see recipes which they add to favorite file.
 */
public class FavoriteRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "my favorite recipe";
    private final String nullString = "null";
    private final String initalFavoriteRecipe = "Favorite recipes are not initialized. Initializing now.";
    private final String detail = "detail";
    private final String delete = "delete";
    private final String button = "Button ";
    private final String clicked = " clicked!";
    private final String noSave = "There no favorite recipe save here !";
    private final String noDelete = "There no favorite recipe can be delete here !";
    private final String alreadyDelete = "Already delete this recipe !";
    private final String current = "Current favoriteRecipe in account: ";
    private final int oneHundredFifty = 150;
    private final int fifty = 50;
    private final int six = 6;
    private final FavoriteRecipeViewModel favoriteRecipeViewModel;
    private FavoriteRecipeController favoriteRecipeController;
    private ReturnToSearchMenuController returnToSearchMenuController;
    private ChooseRecipeController chooseRecipeController;
    private JButton back;
    private JButton shoppingList;

    public FavoriteRecipeView(FavoriteRecipeViewModel favoriteRecipeViewModel) {
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
        this.favoriteRecipeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(viewName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        back = new JButton("back to recipe search page");
        shoppingList = new JButton("Shopping list");
        buttons.add(back);
        buttons.add(shoppingList);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

        this.add(Box.createRigidArea(new Dimension(oneHundredFifty, 0)));
        this.add(Box.createVerticalStrut(fifty));
        addRecipeSectionsA();
        addRecipeSectionsB();
        addRecipeSectionsC();
        addRecipeSectionsD();
        addRecipeSectionsE();
        addRecipeSectionsF();

        back.addActionListener(evt -> {
            if (evt.getSource().equals(back)) {
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
            }
        });

        shoppingList.addActionListener(evt -> {
            if (evt.getSource().equals(shoppingList)) {
                favoriteRecipeController.switchToShoppingListView();
            }
        });

        revalidate();
        repaint();

    }

    private void addRecipeSectionsA() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = nullString;
        // Default value

        // Null check for currentState and favoriteRecipes
        if (currentState == null || currentState.getFavoriteRecipes() == null) {
            System.out.println(initalFavoriteRecipe);
            if (currentState != null) {
                currentState.setFavoriteRecipes(new String[six]);
                // Set default empty favorite recipes list
                for (int i = 0; i < six; i++) {
                    currentState.getFavoriteRecipes()[i] = "";
                    // Initialize elements to avoid nulls
                }
            }
        }

        if (currentState.getFavoriteRecipes()[0] != null) {
            recipeName = currentState.getFavoriteRecipes()[0];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(3, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf(recipeName));
        // sectionLabel.setPreferredSize(new Dimension(oneHundredFifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailA1 = new JButton(detail);

        sectionPanel.add(buttonDetailA1);

        final JButton buttonDeleteA2 = new JButton(delete);

        sectionPanel.add(buttonDeleteA2);

        // listener for button actions
        buttonDetailA1.addActionListener(evt -> {
            System.out.println(button + buttonDetailA1.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[0] == null) {
                System.out.println(noSave);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[0],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteA2.addActionListener(evt -> {
            System.out.println(button + buttonDeleteA2.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[0] == null) {
                System.out.println(noDelete);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[0] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println(alreadyDelete);
                System.out.println(current + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
                this.favoriteRecipeController.execute(currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsB() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = nullString;
        // Default value

        // Null check for currentState and favoriteRecipes
        if (currentState == null || currentState.getFavoriteRecipes() == null) {
            System.out.println(initalFavoriteRecipe);
            if (currentState != null) {
                currentState.setFavoriteRecipes(new String[six]);
                // Set default empty favorite recipes list
                for (int i = 0; i < six; i++) {
                    currentState.getFavoriteRecipes()[i] = "";
                    // Initialize elements to avoid nulls
                }
            }
        }

        if (currentState.getFavoriteRecipes()[1] != null) {
            recipeName = currentState.getFavoriteRecipes()[1];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(3, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf(recipeName));
        // sectionLabel.setPreferredSize(new Dimension(oneHundredFifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailB1 = new JButton(detail);

        sectionPanel.add(buttonDetailB1);

        final JButton buttonDeleteB2 = new JButton(delete);

        sectionPanel.add(buttonDeleteB2);

        // listener for button actions
        buttonDetailB1.addActionListener(evt -> {
            System.out.println(button + buttonDetailB1.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[1] == null) {
                System.out.println(noSave);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[1],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteB2.addActionListener(evt -> {
            System.out.println(button + buttonDeleteB2.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[1] == null) {
                System.out.println(noDelete);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[1] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println(alreadyDelete);
                System.out.println(current + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
                this.favoriteRecipeController.execute(currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsC() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = nullString;

        // Null check for currentState and favoriteRecipes
        if (currentState == null || currentState.getFavoriteRecipes() == null) {
            System.out.println(initalFavoriteRecipe);
            if (currentState != null) {
                currentState.setFavoriteRecipes(new String[six]);
                // Set default empty favorite recipes list
                for (int i = 0; i < six; i++) {
                    currentState.getFavoriteRecipes()[i] = "";
                    // Initialize elements to avoid nulls
                }
            }
        }

        if (currentState.getFavoriteRecipes()[2] != null) {
            recipeName = currentState.getFavoriteRecipes()[2];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(3, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf(recipeName));
        // sectionLabel.setPreferredSize(new Dimension(oneHundredFifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailC1 = new JButton(detail);

        sectionPanel.add(buttonDetailC1);

        final JButton buttonDeleteC2 = new JButton(delete);

        sectionPanel.add(buttonDeleteC2);

        // listener for button actions
        buttonDetailC1.addActionListener(evt -> {
            System.out.println(button + buttonDetailC1.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2] == null) {
                System.out.println(noSave);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[2],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteC2.addActionListener(evt -> {
            System.out.println(button + buttonDeleteC2.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2] == null) {
                System.out.println(noDelete);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[2] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println(alreadyDelete);
                System.out.println(current + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
                this.favoriteRecipeController.execute(currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsD() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = nullString;

        // Null check for currentState and favoriteRecipes
        if (currentState == null || currentState.getFavoriteRecipes() == null) {
            System.out.println(initalFavoriteRecipe);
            if (currentState != null) {
                currentState.setFavoriteRecipes(new String[six]);
                // Set default empty favorite recipes list
                for (int i = 0; i < six; i++) {
                    currentState.getFavoriteRecipes()[i] = "";
                    // Initialize elements to avoid nulls
                }
            }
        }

        if (currentState.getFavoriteRecipes()[2 + 1] != null) {
            recipeName = currentState.getFavoriteRecipes()[2 + 1];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(3, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf(recipeName));
        // sectionLabel.setPreferredSize(new Dimension(oneHundredFifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailD1 = new JButton(detail);

        sectionPanel.add(buttonDetailD1);

        final JButton buttonDeleteD2 = new JButton(delete);

        // listener for button actions
        buttonDetailD1.addActionListener(evt -> {
            System.out.println(button + buttonDetailD1.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 1] == null) {
                System.out.println(noSave);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[2 + 1],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteD2.addActionListener(evt -> {
            System.out.println(button + buttonDeleteD2.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 1] == null) {
                System.out.println(noDelete);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[2 + 1] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println(alreadyDelete);
                System.out.println(current + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
                this.favoriteRecipeController.execute(currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });
        sectionPanel.add(buttonDeleteD2);

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsE() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = nullString;

        // Null check for currentState and favoriteRecipes
        if (currentState == null || currentState.getFavoriteRecipes() == null) {
            System.out.println(initalFavoriteRecipe);
            if (currentState != null) {
                currentState.setFavoriteRecipes(new String[six]);
                // Set default empty favorite recipes list
                for (int i = 0; i < six; i++) {
                    currentState.getFavoriteRecipes()[i] = "";
                    // Initialize elements to avoid nulls
                }
            }
        }
        if (currentState.getFavoriteRecipes()[2 + 2] != null) {
            recipeName = currentState.getFavoriteRecipes()[2 + 2];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(3, 1));
        // 6 rows for A-F
        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left
        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf(recipeName));
        // sectionLabel.setPreferredSize(new Dimension(oneHundredFifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailE1 = new JButton(detail);

        sectionPanel.add(buttonDetailE1);

        final JButton buttonDeleteE2 = new JButton(delete);

        // listener for button actions
        buttonDetailE1.addActionListener(evt -> {
            System.out.println(button + buttonDetailE1.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 2] == null) {
                System.out.println(noSave);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[2 + 2],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteE2.addActionListener(evt -> {
            System.out.println(button + buttonDeleteE2.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 2] == null) {
                System.out.println(noDelete);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[2 + 2] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println(alreadyDelete);
                System.out.println(current + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
                this.favoriteRecipeController.execute(currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });
        sectionPanel.add(buttonDeleteE2);

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsF() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = nullString;

        // Null check for currentState and favoriteRecipes
        if (currentState == null || currentState.getFavoriteRecipes() == null) {
            System.out.println(initalFavoriteRecipe);
            if (currentState != null) {
                currentState.setFavoriteRecipes(new String[six]);
                // Set default empty favorite recipes list
                for (int i = 0; i < six; i++) {
                    currentState.getFavoriteRecipes()[i] = "";
                    // Initialize elements to avoid nulls
                }
            }
        }

        if (currentState.getFavoriteRecipes()[2 + 2 + 1] != null) {
            recipeName = currentState.getFavoriteRecipes()[2 + 2 + 1];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(3, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf(recipeName));
        // sectionLabel.setPreferredSize(new Dimension(oneHundredFifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailF1 = new JButton(detail);

        sectionPanel.add(buttonDetailF1);

        final JButton buttonDeleteF2 = new JButton(delete);

        // listener for button actions
        buttonDetailF1.addActionListener(evt -> {
            System.out.println(button + buttonDetailF1.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 2 + 1] == null) {
                System.out.println(noSave);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[2 + 2 + 1],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteF2.addActionListener(evt -> {
            System.out.println(button + buttonDeleteF2.getText() + clicked);
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 2 + 1] == null) {
                System.out.println(noDelete);
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[2 + 2 + 1] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println(alreadyDelete);
                System.out.println(current + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
                this.favoriteRecipeController.execute(currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });
        sectionPanel.add(buttonDeleteF2);

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final FavoriteRecipeState favoriteRecipeState = (FavoriteRecipeState) evt.getNewValue();
        favoriteRecipeViewModel.setState(favoriteRecipeState);
        // Update UI components with the new state values
        // System.out.println("there are some change: update favoriteRecipe view");
        this.removeAll();
        this.favoriteRecipeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("my favorite recipe");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        back = new JButton("back to recipe search page");
        shoppingList = new JButton("Shopping List");
        buttons.add(back);
        buttons.add(shoppingList);

        this.add(title);
        this.add(buttons);

        this.add(Box.createRigidArea(new Dimension(oneHundredFifty, 0)));
        this.add(Box.createVerticalStrut(fifty));
        addRecipeSectionsA();
        addRecipeSectionsB();
        addRecipeSectionsC();
        addRecipeSectionsD();
        addRecipeSectionsE();
        addRecipeSectionsF();

        back.addActionListener(evt1 -> {
            if (evt1.getSource().equals(back)) {
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
            }
        });

        shoppingList.addActionListener(evt2 -> {
            if (evt2.getSource().equals(shoppingList)) {
                favoriteRecipeController.switchToShoppingListView();
            }
        });

        revalidate();
        repaint();

    }

    public String getViewName() {
        return viewName;
    }

    public void setFavoriteRecipeController(FavoriteRecipeController favoriteRecipeController) {
        this.favoriteRecipeController = favoriteRecipeController;
    }

    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    public void setChooseRecipeController(ChooseRecipeController chooseRecipeController) {
        this.chooseRecipeController = chooseRecipeController;
    }
}
