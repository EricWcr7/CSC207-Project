package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import interface_adapter.back_to_edit_view.BackToEditViewController;
import interface_adapter.create_recipe.CreateRecipeController;
import interface_adapter.create_recipe.CreateRecipeState;
import interface_adapter.create_recipe.CreateRecipeViewModel;

/**
 * The CreateView class represents the "Create Recipe" view in the application.
 * It extends JPaneland implements ActionListenerPropertyChangeListener
 * to handle user interactions and observe changes in the view model.
 *
 * <p>This class provides the user interface for creating a recipe, including input fields
 * for the dish name, cooking instructions, and ingredients. It also manages the navigation
 * between the "Create Recipe" view and other views.</p>
 */
public class CreateView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Create recipe";
    private final CreateRecipeViewModel createRecipeViewModel;
    private CreateRecipeController createRecipeController;
    private BackToEditViewController backToEditViewController;

    private final JButton back;
    private final JButton confirm;
    private final JTextField nameField;
    private final JTextArea cookArea;
    private final JPanel ingredientRowsPanel = new JPanel();
    private JLabel dishNameErrorField = new JLabel();
    private final EditView editView;

    public CreateView(CreateRecipeViewModel createRecipeViewModel, EditView editView) {
        this.createRecipeViewModel = createRecipeViewModel;
        this.editView = editView;
        this.createRecipeViewModel.addPropertyChangeListener(this);
        this.setLayout(new BorderLayout());
        final JLabel titleLabel = new JLabel("Create recipe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, CreateRecipeViewModel.FONT_SIZE));
        this.add(titleLabel, BorderLayout.NORTH);
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        final JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JLabel nameLabel = new JLabel("Dish name:");
        nameField = new JTextField(CreateRecipeViewModel.INSTRUCTIONS_AREA_COLUMNS);
        nameField.setFont(new Font("Arial", Font.PLAIN, CreateRecipeViewModel.FONT_SIZE));
        nameField.setPreferredSize(new Dimension(CreateRecipeViewModel.NAME_FIELD_WIDTH, CreateRecipeViewModel.NAME_FIELD_HEIGHT));
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CreateRecipeState currentState = createRecipeViewModel.getState();
                currentState.setDishName(nameField.getText().trim());
                createRecipeViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        final JPanel introPanel = new JPanel();
        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
        final JLabel introLabel = new JLabel("Ingredients:");
        introPanel.add(introLabel);
        // Scroll pane to make the ingredients panel scrollable
        final JScrollPane introScrollPane = new JScrollPane(introPanel);
        introScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        introScrollPane.setPreferredSize(new Dimension(CreateRecipeViewModel.INGREDIENT_SCROLL_WIDTH,
                CreateRecipeViewModel.INGREDIENT_SCROLL_HEIGHT));

        // Panel for adding dynamic input rows
        ingredientRowsPanel.setLayout(new BoxLayout(ingredientRowsPanel, BoxLayout.Y_AXIS));
        introPanel.add(ingredientRowsPanel);

        // Button to add more ingredient rows
        final JButton addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.addActionListener(evt -> addIngredientRow());

        introPanel.add(Box.createVerticalStrut(CreateRecipeViewModel.BUTTON_GAP));
        introPanel.add(addIngredientButton);

        // Instructions panel
        final JPanel cookPanel = new JPanel(new BorderLayout());
        final JLabel cookLabel = new JLabel("Instructions:");
        cookArea = new JTextArea(CreateRecipeViewModel.INSTRUCTIONS_AREA_ROWS, CreateRecipeViewModel.INSTRUCTIONS_AREA_COLUMNS);
        cookArea.setFont(new Font("Arial", Font.BOLD, CreateRecipeViewModel.FONT_SIZE));
        cookArea.setLineWrap(true);
        cookArea.setWrapStyleWord(true);
        cookPanel.add(cookLabel, BorderLayout.NORTH);
        cookPanel.add(new JScrollPane(cookArea), BorderLayout.CENTER);

        // Add listener to update instructions in state
        cookArea.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CreateRecipeState currentState = createRecipeViewModel.getState();
                currentState.setInstructions(cookArea.getText().trim());
                createRecipeViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        // Add sub-panels to mainPanel
        mainPanel.add(dishNameErrorField);
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(CreateRecipeViewModel.BUTTON_GAP));
        mainPanel.add(introScrollPane);
        mainPanel.add(Box.createVerticalStrut(CreateRecipeViewModel.BUTTON_GAP));
        mainPanel.add(cookPanel);

        this.add(mainPanel, BorderLayout.CENTER);

        final JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        confirm = new JButton("Confirm");
        back = new JButton("Back");

        lowerPanel.add(back);
        lowerPanel.add(confirm);

        this.add(lowerPanel, BorderLayout.SOUTH);

        back.addActionListener(evt -> {
            if (backToEditViewController != null) {
                backToEditViewController.backToEditView();
            }
        });
        confirm.addActionListener(evt -> {
            if (createRecipeController != null) {
                final CreateRecipeState currentState = createRecipeViewModel.getState();
                final String dishName = currentState.getDishName();
                final String instructions = currentState.getInstructions();
                final HashMap<String, String> ingredients = new HashMap<>(currentState.getIngredients());
                createRecipeController.execute(dishName, instructions, ingredients);

                // Add the newly created recipe to the "new_recipes.json" file for persistent storage
                // This ensures the newly added recipe is saved locally
                addToNewRecipesJson(dishName, instructions, ingredients);

                // If the EditView is available, refresh its dropdown menu to include the newly added recipe
                // This keeps the UI synchronized with the updated data

            }
        });
    }

    private void addIngredientRow() {
        final JPanel newRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JTextField ingredientNameField = new JTextField(15);
        final JTextField ingredientAmountField = new JTextField(10);
        final JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(event -> {
            ingredientRowsPanel.remove(newRow);
            updateIngredientsState();
            ingredientRowsPanel.revalidate();
            ingredientRowsPanel.repaint();
        });

        ingredientNameField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                updateIngredientsState();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState();
            }
        });

        ingredientAmountField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                updateIngredientsState();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState();
            }
        });

        newRow.add(new JLabel("Name:"));
        newRow.add(ingredientNameField);
        newRow.add(new JLabel("Amount:"));
        newRow.add(ingredientAmountField);
        newRow.add(deleteButton);

        ingredientRowsPanel.add(newRow);
        ingredientRowsPanel.revalidate();
        ingredientRowsPanel.repaint();
    }

    private void updateIngredientsState() {
        final CreateRecipeState currentState = createRecipeViewModel.getState();

        currentState.getIngredients().clear();

        for (Component comp : ingredientRowsPanel.getComponents()) {
            if (comp instanceof JPanel) {
                final JPanel row = (JPanel) comp;
                final JTextField nameField = (JTextField) row.getComponent(1);
                final JTextField amountField = (JTextField) row.getComponent(3);
                final String name = nameField.getText().trim();
                final String amount = amountField.getText().trim();

                currentState.addIngredient(name, amount);
            }
        }

        // Update ViewModel with the new state
        createRecipeViewModel.setState(currentState);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();
        final CreateRecipeState state = (CreateRecipeState) evt.getNewValue();

        switch (propertyName) {
            case "dishNameError":
                // Update UI for dish name error
                System.out.println("Dish name error updated: " + state.getDishNameError());
                dishNameErrorField.setText(state.getDishNameError());
                break;

            case "create recipe":
                System.out.println("create recipe");
                resetFields();
                break;

            case "Back to search":
                System.out.println("Back to search");
                resetFields();
                break;

            default:
                System.out.println("Unhandled property: " + propertyName);
                break;
        }
    }

    /**
     * Resets all input fields in the Create View to their default (empty) state.
     * This method is typically used to clear the form after a recipe is successfully created
     * or when the user cancels the creation process.
     */
    private void resetFields() {
        // Clear the error message field for the dish name
        // This removes any validation error messages shown to the user
        dishNameErrorField.setText("");

        // Clear the dish name input field
        // This ensures the user starts with an empty text field for entering a new dish name
        nameField.setText("");

        // Clear the instructions text area
        // This resets the area where users can enter cooking instructions
        cookArea.setText("");

        // Clear all ingredient rows that have been added to the form
        // Iterate through all components in the ingredientRowsPanel
        for (Component comp : ingredientRowsPanel.getComponents()) {
            // Check if the component is a JPanel (representing a single ingredient row)
            if (comp instanceof JPanel) {
                final JPanel row = (JPanel) comp;
                final JTextField nameField = (JTextField) row.getComponent(1);
                final JTextField amountField = (JTextField) row.getComponent(3);

                // Clear the text fields to remove any user input
                nameField.setText("");
                amountField.setText("");
            }
        }

        // Remove all ingredient rows from the ingredientRowsPanel
        // This clears the form so no rows are visible to the user
        ingredientRowsPanel.removeAll();

        // Trigger a UI refresh to reflect the changes
        // Revalidate the panel layout and repaint to update the display
        ingredientRowsPanel.revalidate();
        ingredientRowsPanel.repaint();
    }

    /**
     * Retrieves the name of the current view.
     *
     * @return the name of the view as a String.
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the CreateController for the view. The CreateController is responsible for
     * handling actions related to creating new recipes.
     *
     * @param createRecipeController the CreateController instance to be associated with this view.
     */
    public void setCreateController(CreateRecipeController createRecipeController) {
        this.createRecipeController = createRecipeController;
    }

    /**
     * Sets the BackToEditViewController for the view. This controller is responsible for
     * handling the logic to navigate back to the EditView from other views.
     *
     * @param backToEditViewConTroller the BackToEditViewController instance to be associated with this view.
     */
    public void setBackToEditViewConTroller(BackToEditViewController backToEditViewConTroller) {
        this.backToEditViewController = backToEditViewConTroller;
    }

    /**
     * Adds a new recipe to the "new_recipes.json" file. If the file doesn't exist, it creates a new one.
     *
     * @param dishName     The name of the dish to be added.
     * @param instructions The cooking instructions for the dish.
     * @param ingredients  A map containing the ingredients and their quantities.
     */
    private void addToNewRecipesJson(String dishName, String instructions, HashMap<String, String> ingredients) {
        try {
            // Variables for handling JSON file and objects
            final FileReader reader;
            JsonObject jsonObject;
            JsonArray recipesArray;

            try {
                // Attempt to read the existing "new_recipes.json" file
                reader = new FileReader("new_recipes.json");
                jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                recipesArray = jsonObject.getAsJsonArray("recipes");
                reader.close();
            }
            catch (IOException ioException) {
                jsonObject = new JsonObject();
                recipesArray = new JsonArray();
                jsonObject.add("recipes", recipesArray);
            }
            final JsonObject newRecipe = new JsonObject();
            newRecipe.addProperty("name", dishName);
            newRecipe.addProperty("instructions", instructions);

            final JsonObject ingredientsObject = new JsonObject();
            for (String ingredient : ingredients.keySet()) {
                ingredientsObject.addProperty(ingredient, ingredients.get(ingredient));
            }
            newRecipe.add("ingredients", ingredientsObject);

            recipesArray.add(newRecipe);

            final FileWriter writer = new FileWriter("new_recipes.json");
            writer.write(jsonObject.toString());
            writer.close();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving to new_recipes.json!");
        }
    }
}
