package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.return_to_recipe_search_view.ReturnToSearchMenuController;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import interface_adapter.favorite_recipe.FavoriteRecipeController;
import interface_adapter.like_and_dislike.dislike_a_recipe.DislikeRecipeController;
import interface_adapter.like_and_dislike.like_a_recipe.LikeRecipeController;

/**
 * The View for when the user is displaying a recipe.
 */
public class DisplayRecipeView extends JPanel implements PropertyChangeListener {
    private static final String FONT_ARIAL = "Arial";
    private static final int FONT_SIZE_DEFAULT = 14;

    private final String viewName = "display the recipe";
    private DisplayRecipeViewModel displayRecipeViewmodel;

    private ReturnToSearchMenuController returnToSearchMenuController;
    private LikeRecipeController likeRecipeController;
    private FavoriteRecipeController favoriteRecipeController;
    private DislikeRecipeController dislikeRecipeController;

    // Example: Dynamic data loaded into variables
    private String dishName;
    private String ingredients;
    private String instructions;

    private int likeNumber;
    private int dislikeNumber;

    private final JTextArea ingredientsArea;
    private final JTextArea instructionArea;

    private final JButton returnToSearchMenu;
    private final JButton likeButton;
    private final JButton dislikeButton;
    private final JButton favoriteButton;

    private final JLabel likeCount;
    private final JLabel dislikeCount;

    public DisplayRecipeView(DisplayRecipeViewModel displayRecipeViewModel) {
        this.displayRecipeViewmodel = displayRecipeViewModel;
        this.displayRecipeViewmodel.addPropertyChangeListener(this);

        System.out.println("At DisplayRecipeView, the dishname is: " + dishName);

        ingredientsArea = new JTextArea(ingredients);
        ingredientsArea.setEditable(false);
        instructionArea = new JTextArea(instructions);
        instructionArea.setEditable(false);
        // 启用自动换行
        instructionArea.setLineWrap(true);
        // 按单词换行，而不是在字符中间
        instructionArea.setWrapStyleWord(true);

        returnToSearchMenu = new JButton("Return to Search View ");

        likeButton = new JButton("Like");
        likeCount = new JLabel();

        dislikeButton = new JButton("Dislike");
        dislikeCount = new JLabel();

        favoriteButton = new JButton("Favorite");

        // Use BoxLayout for a cleaner vertical layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Panel for recipe details
        final JPanel recipePanel = new JPanel();
        // Vertical layout for recipe details
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
        // Align it to the left
        recipePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Dish name
        final JLabel dishNameLabel = new JLabel("Dish Name: ");
        dishNameLabel.setFont(new Font(FONT_ARIAL, Font.BOLD, FONT_SIZE_DEFAULT));
        recipePanel.add(dishNameLabel);

        final JLabel dishNameValue = new JLabel(dishName);
        recipePanel.add(dishNameValue);

        // Ingredients
        final JLabel ingredientsLabel = new JLabel("Ingredients: ");
        ingredientsLabel.setFont(new Font(FONT_ARIAL, Font.BOLD, FONT_SIZE_DEFAULT));
        recipePanel.add(ingredientsLabel);
        recipePanel.add(ingredientsArea);

        // Instructions
        final JLabel instructionsLabel = new JLabel("Instructions: ");
        instructionsLabel.setFont(new Font(FONT_ARIAL, Font.BOLD, FONT_SIZE_DEFAULT));
        recipePanel.add(instructionsLabel);
        recipePanel.add(instructionArea);

        // Panel for buttons (aligned horizontally)
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(returnToSearchMenu);
        buttonsPanel.add(createButtonWithLabel(likeButton, likeCount));
        buttonsPanel.add(createButtonWithLabel(dislikeButton, dislikeCount));
        buttonsPanel.add(favoriteButton);

        // Add recipe details and buttons panels to the main view
        add(recipePanel);
        add(buttonsPanel);

        returnToSearchMenu.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(returnToSearchMenu)) {
                        this.returnToSearchMenuController.fromDisplayBackToSearchMenu();
                    }
                }
        );

        likeButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(likeButton)) {
                        final DisplayRecipeState state = displayRecipeViewModel.getState();
                        this.likeRecipeController.execute(state.getDishName());
                    }
                }
        );

        dislikeButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(dislikeButton)) {
                        final DisplayRecipeState state = displayRecipeViewModel.getState();
                        this.dislikeRecipeController.execute(state.getDishName());
                    }
                }
        );

        favoriteButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(favoriteButton)) {
                        System.out.println("I like this recipe !");
                        final DisplayRecipeState state = displayRecipeViewModel.getState();
                        if (findPlaceToSaveFavoriteRecipe(state) == -1) {
                            System.out.println("That full in favorite file, you can not save more recipes in file !");
                        }
                        else {
                            final String[] currentList = state.getFavoriteRecipes();
                            currentList[findPlaceToSaveFavoriteRecipe(state)] = state.getDishName();
                            // state.setFavoriteRecipes(currentList);
                            // displayRecipeViewModel.setState(state);
                            // System.out.println("Add " + state.getDishName() + " into my favoriteRecipes file !");
                            // final String username = state.getUsername();
                            // final String[] favoriteRecipes = state.getFavoriteRecipes();
                            // System.out.println("Current logged in account: " + username);
                            // System.out.println("Current favoriteRecipe in account: " + Arrays.toString(favoriteRecipes));
                            favoriteRecipeController.execute(state.getUsername(), currentList);

                        }

                    }
                }
        );
    }

    public int findPlaceToSaveFavoriteRecipe(DisplayRecipeState state) {
        String[] favoriteRecipesList = state.getFavoriteRecipes();

        // Ensure favoriteRecipesList is initialized
        if (favoriteRecipesList == null) {
            System.out.println("Favorite recipes list is not initialized, initializing now.");
            favoriteRecipesList = new String[6];
            for (int i = 0; i < favoriteRecipesList.length; i++) {
                favoriteRecipesList[i] = null; // Initialize each element to an empty string
            }
            state.setFavoriteRecipes(favoriteRecipesList);
        }

        // Find the first available (empty) spot in the list to save the favorite recipe
        int firstIndex = -1;
        for (int i = favoriteRecipesList.length - 1; i > -1; i--) {
            if (favoriteRecipesList[i] == null) { // Check for an empty string instead of null
                firstIndex = i;
                // break; // Stop once we find the first empty spot
            }
        }

        return firstIndex;
    }


    public String getViewName() {
        return viewName;
    }

    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    public void setLikeRecipeController(LikeRecipeController likeRecipeController) {
        this.likeRecipeController = likeRecipeController;
    }

    public void setDislikeRecipeController(DislikeRecipeController dislikeRecipeController) {
        this.dislikeRecipeController = dislikeRecipeController;
    }

    public void setFavoriteRecipeController(FavoriteRecipeController favoriteRecipeController) {
        this.favoriteRecipeController = favoriteRecipeController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property Change Event received with new state: " + evt.getNewValue());
        // Update view when DisplayRecipeState changes
        final DisplayRecipeState displayRecipeState = (DisplayRecipeState) evt.getNewValue();

        update(displayRecipeState);

    }

    private void update(DisplayRecipeState displayRecipeState) {

        setLikedMessagePopUp(displayRecipeState);
        setDislikedMessagePopUp(displayRecipeState);

        // Update UI components with the new state values
        dishName = displayRecipeState.getDishName();
        ingredients = displayRecipeState.getIngredients();
        instructions = displayRecipeState.getInstructions();
        likeNumber = displayRecipeState.getLikeNumber(dishName);
        dislikeNumber = displayRecipeState.getDislikeNumber(dishName);

        // Update the labels and text areas with the new values
        ((JLabel) ((JPanel) this.getComponent(0)).getComponent(0)).setText("Dish Name: " + dishName);
        ingredientsArea.setText(ingredients);
        instructionArea.setText(formatInstructions(instructions));
        likeCount.setText(String.valueOf(likeNumber));
        dislikeCount.setText(String.valueOf(dislikeNumber));
    }

    private void setDislikedMessagePopUp(DisplayRecipeState displayRecipeState) {
        final String dislikedMessage = displayRecipeState.getDislikedMessage();
        if (dislikedMessage != null && !dislikedMessage.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    dislikedMessage,
                    "Notification",
                    JOptionPane.INFORMATION_MESSAGE
            );
            displayRecipeState.clearDislikedMessage();
        }
    }

    private void setLikedMessagePopUp(DisplayRecipeState displayRecipeState) {
        final String likedMessage = displayRecipeState.getLikedMessage();
        if (likedMessage != null && !likedMessage.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    likedMessage,
                    "Notification",
                    JOptionPane.INFORMATION_MESSAGE
            );
            displayRecipeState.clearLikedMessage();
        }
    }

    // Format instructions to display them in a more readable way(之前句子太长了，一个屏幕装不下)
    String formatInstructions(String theInstructions) {
        // 根据句号、感叹号或问号分割句子
        final String[] sentences = theInstructions.split("(?<=[.!?])\\s*");
        // 用换行符拼接句子
        return String.join("\n", sentences);
    }

    private JPanel createButtonWithLabel(JButton button, JLabel label) {
        final JPanel panel = new JPanel(new BorderLayout());
        // 按钮在左侧
        panel.add(button, BorderLayout.WEST);
        // 数字在右侧
        panel.add(label, BorderLayout.EAST);
        return panel;
    }

}
