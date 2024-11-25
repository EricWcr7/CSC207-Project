package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.*;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.choose_recipe.ChooseRecipeController;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.favorite_recipe.FavoriteRecipeController;
import interface_adapter.favorite_recipe.FavoriteRecipeState;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;
import interface_adapter.searchresult.SearchResultController;

/**
 * The View for when the user is going to see recipes which they add to favorite file.
 */
public class FavoriteRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "my favorite recipe";
    private final int threeHundred = 300;
    private final int oneHundredFifty = 150;
    private final int thirty = 30;
    private final int fifty = 50;
    private final FavoriteRecipeViewModel favoriteRecipeViewModel;
    private FavoriteRecipeController favoriteRecipeController;
    private ReturnToSearchMenuController returnToSearchMenuController;
    private ChooseRecipeController chooseRecipeController;
    private JButton back;

    public FavoriteRecipeView(FavoriteRecipeViewModel favoriteRecipeViewModel) {
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
        this.favoriteRecipeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("my favorite recipe");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        back = new JButton("back to recipe search page");
        buttons.add(back);

        this.add(title);
        this.add(buttons);

        this.add(Box.createRigidArea(new Dimension(threeHundred, 0)));
        this.add(Box.createVerticalStrut(oneHundredFifty));
        addRecipeSectionsA();
        addRecipeSectionsB();
        addRecipeSectionsC();
        addRecipeSectionsD();
        addRecipeSectionsE();
        addRecipeSectionsF();

        back.addActionListener(evt -> {
            if (evt.getSource().equals(back)) {
                final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu(
                        currentState.getUsername(), currentState.getFavoriteRecipes()
                );
            }
        });
    }

    private void addRecipeSectionsA() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = "null";
        if (currentState.getFavoriteRecipes()[0] != null) {
            recipeName = currentState.getFavoriteRecipes()[0];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
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

        final JButton buttonDetailA1 = new JButton("detail");

        sectionPanel.add(buttonDetailA1);

        final JButton buttonDeleteA2 = new JButton("delete");

        sectionPanel.add(buttonDeleteA2);

        // listener for button actions
        buttonDetailA1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailA1.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[0] == null) {
                System.out.println("There no favorite recipe save here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[0],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteA2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteA2.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[0] == null) {
                System.out.println("There no favorite recipe can be delete here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[0] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println("Already delete this recipe !");
                System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu(
                        currentState1.getUsername(), currentState1.getFavoriteRecipes()
                );
            }
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsB() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = "null";
        if (currentState.getFavoriteRecipes()[1] != null) {
            recipeName = currentState.getFavoriteRecipes()[1];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
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

        final JButton buttonDetailB1 = new JButton("detail");

        sectionPanel.add(buttonDetailB1);

        final JButton buttonDeleteB2 = new JButton("delete");

        sectionPanel.add(buttonDeleteB2);

        // listener for button actions
        buttonDetailB1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailB1.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[1] == null) {
                System.out.println("There no favorite recipe save here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[1],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteB2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteB2.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[1] == null) {
                System.out.println("There no favorite recipe can be delete here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[1] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println("Already delete this recipe !");
                System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu(
                        currentState1.getUsername(), currentState1.getFavoriteRecipes()
                );
            }
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsC() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = "null";
        if (currentState.getFavoriteRecipes()[2] != null) {
            recipeName = currentState.getFavoriteRecipes()[2];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
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

        final JButton buttonDetailC1 = new JButton("detail");

        sectionPanel.add(buttonDetailC1);

        final JButton buttonDeleteC2 = new JButton("delete");

        sectionPanel.add(buttonDeleteC2);

        // listener for button actions
        buttonDetailC1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailC1.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2] == null) {
                System.out.println("There no favorite recipe save here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[2],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteC2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteC2.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2] == null) {
                System.out.println("There no favorite recipe can be delete here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[2] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println("Already delete this recipe !");
                System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu(
                        currentState1.getUsername(), currentState1.getFavoriteRecipes()
                );
            }
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsD() {
        final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
        String recipeName = "null";
        if (currentState.getFavoriteRecipes()[2 + 1] != null) {
            recipeName = currentState.getFavoriteRecipes()[2 + 1];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
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

        final JButton buttonDetailD1 = new JButton("detail");

        sectionPanel.add(buttonDetailD1);

        final JButton buttonDeleteD2 = new JButton("delete");

        // listener for button actions
        buttonDetailD1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailD1.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 1] == null) {
                System.out.println("There no favorite recipe save here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[2 + 1],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteD2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteD2.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 1] == null) {
                System.out.println("There no favorite recipe can be delete here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[2 + 1] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println("Already delete this recipe !");
                System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu(
                        currentState1.getUsername(), currentState1.getFavoriteRecipes()
                );
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
        String recipeName = "null";
        if (currentState.getFavoriteRecipes()[2 + 2] != null) {
            recipeName = currentState.getFavoriteRecipes()[2 + 2];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
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

        final JButton buttonDetailE1 = new JButton("detail");

        sectionPanel.add(buttonDetailE1);

        final JButton buttonDeleteE2 = new JButton("delete");

        // listener for button actions
        buttonDetailE1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailE1.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 2] == null) {
                System.out.println("There no favorite recipe save here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[2 + 2],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteE2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteE2.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 2] == null) {
                System.out.println("There no favorite recipe can be delete here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[2 + 2] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println("Already delete this recipe !");
                System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu(
                        currentState1.getUsername(), currentState1.getFavoriteRecipes()
                );
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
        String recipeName = "null";
        if (currentState.getFavoriteRecipes()[2 + 2 + 1] != null) {
            recipeName = currentState.getFavoriteRecipes()[2 + 2 + 1];
        }
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
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

        final JButton buttonDetailF1 = new JButton("detail");

        sectionPanel.add(buttonDetailF1);

        final JButton buttonDeleteF2 = new JButton("delete");

        // listener for button actions
        buttonDetailF1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailF1.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 2 + 1] == null) {
                System.out.println("There no favorite recipe save here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                chooseRecipeController.execute(currentState1.getFavoriteRecipes()[2 + 2 + 1],
                        currentState1.getUsername(), currentState1.getFavoriteRecipes());
            }
        });

        // listener for button actions
        buttonDeleteF2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteF2.getText() + " clicked!");
            if (favoriteRecipeViewModel.getState().getFavoriteRecipes()[2 + 2 + 1] == null) {
                System.out.println("There no favorite recipe can be delete here !");
            }
            else {
                final FavoriteRecipeState currentState1 = favoriteRecipeViewModel.getState();
                final String[] favoriteRecipes = currentState1.getFavoriteRecipes();
                favoriteRecipes[2 + 2 + 1] = null;
                currentState1.setFavoriteRecipes(favoriteRecipes);
                favoriteRecipeViewModel.setState(currentState1);
                System.out.println("Already delete this recipe !");
                System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu(
                        currentState1.getUsername(), currentState1.getFavoriteRecipes()
                );
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

        final JPanel buttons = new JPanel();
        back = new JButton("back to recipe search page");
        buttons.add(back);

        this.add(title);
        this.add(buttons);

        this.add(Box.createRigidArea(new Dimension(threeHundred, 0)));
        this.add(Box.createVerticalStrut(oneHundredFifty));
        addRecipeSectionsA();
        addRecipeSectionsB();
        addRecipeSectionsC();
        addRecipeSectionsD();
        addRecipeSectionsE();
        addRecipeSectionsF();

        back.addActionListener(evt1 -> {
            if (evt1.getSource().equals(back)) {
                final FavoriteRecipeState currentState = favoriteRecipeViewModel.getState();
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu(
                        currentState.getUsername(), currentState.getFavoriteRecipes()
                );
            }
        });
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
