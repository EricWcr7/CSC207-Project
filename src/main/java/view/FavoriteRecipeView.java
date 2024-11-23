package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.favorite_recipe.FavoriteRecipeController;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;

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
    private final JButton back;

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
                this.returnToSearchMenuController.fromFavoriteRecipeBackToSearchMenu();
            }
        });
    }

    private void addRecipeSectionsA() {
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf("A"));
        sectionLabel.setPreferredSize(new Dimension(fifty, thirty));
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
        });

        // listener for button actions
        buttonDeleteA2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteA2.getText() + " clicked!");
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsB() {
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf("B"));
        sectionLabel.setPreferredSize(new Dimension(fifty, thirty));
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
        });

        // listener for button actions
        buttonDeleteB2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteB2.getText() + " clicked!");
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsC() {
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf("C"));
        sectionLabel.setPreferredSize(new Dimension(fifty, thirty));
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
        });

        // listener for button actions
        buttonDeleteC2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteC2.getText() + " clicked!");
        });

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsD() {
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf("D"));
        sectionLabel.setPreferredSize(new Dimension(fifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailD1 = new JButton("detail");

        sectionPanel.add(buttonDetailD1);

        final JButton buttonDeleteD2 = new JButton("delete");

        // listener for button actions
        buttonDetailD1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailD1.getText() + " clicked!");
        });

        // listener for button actions
        buttonDeleteD2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteD2.getText() + " clicked!");
        });
        sectionPanel.add(buttonDeleteD2);

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsE() {
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
        // 6 rows for A-F
        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left
        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf("E"));
        sectionLabel.setPreferredSize(new Dimension(fifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailE1 = new JButton("detail");

        sectionPanel.add(buttonDetailE1);

        final JButton buttonDeleteE2 = new JButton("delete");

        // listener for button actions
        buttonDetailE1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailE1.getText() + " clicked!");
        });

        // listener for button actions
        buttonDeleteE2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteE2.getText() + " clicked!");
        });
        sectionPanel.add(buttonDeleteE2);

        // Add the section panel to the grid
        recipeGrid.add(sectionPanel);

        // Add the grid to the main panel
        this.add(recipeGrid);
    }

    private void addRecipeSectionsF() {
        // Main panel for recipe sections
        final JPanel recipeGrid = new JPanel(new GridLayout(6, 1));
        // 6 rows for A-F

        final JPanel sectionPanel = new JPanel();
        // Panel for each section
        sectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Align items to the left

        // Section label
        final JLabel sectionLabel = new JLabel(String.valueOf("F"));
        sectionLabel.setPreferredSize(new Dimension(fifty, thirty));
        // Size for the label
        sectionPanel.add(sectionLabel);

        // Add three buttons for the section

        final JButton buttonDetailF1 = new JButton("detail");

        sectionPanel.add(buttonDetailF1);

        final JButton buttonDeleteF2 = new JButton("delete");

        // listener for button actions
        buttonDetailF1.addActionListener(evt -> {
            System.out.println("Button " + buttonDetailF1.getText() + " clicked!");
        });

        // listener for button actions
        buttonDeleteF2.addActionListener(evt -> {
            System.out.println("Button " + buttonDeleteF2.getText() + " clicked!");
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
}
