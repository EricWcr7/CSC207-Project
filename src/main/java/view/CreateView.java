package view;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import interface_adapter.BackToEditView.BackToEditViewController;
import interface_adapter.create.CreateController;
import interface_adapter.create.CreateState;
import interface_adapter.create.CreateViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CreateView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Create recipe";
    private final CreateViewModel createViewModel;
    private CreateController createController;
    private BackToEditViewController backToEditViewController;

    private final JButton back;
    private final JButton confirm;
    private JTextField nameField;
    private JTextArea cookArea;
    private final JPanel ingredientRowsPanel = new JPanel();
    private JLabel dishNameErrorField = new JLabel();
    private final EditView editView;

    public CreateView(CreateViewModel createViewModel, EditView editView) {
        this.createViewModel = createViewModel;
        this.editView = editView;

        this.createViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        // Add label to the top (NORTH)
        JLabel titleLabel = new JLabel("Create recipe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // Adjust font as needed
        this.add(titleLabel, BorderLayout.NORTH);

        // Main panel to hold sub-panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Dish name panel (Single-row JTextField)
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Align components to the left
        JLabel nameLabel = new JLabel("Dish name:");
        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(300, 25));
        // Set a fixed width and height for JTextField
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Add listener to update dish name in state
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                CreateState currentState = createViewModel.getState();
                currentState.setDishName(nameField.getText().trim());
                createViewModel.setState(currentState);
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

        // Ingredients panel
        JPanel introPanel = new JPanel();
        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));

        JLabel introLabel = new JLabel("Ingredients:");
        introPanel.add(introLabel);

        // Scroll pane to make the ingredients panel scrollable
        JScrollPane introScrollPane = new JScrollPane(introPanel);
        introScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        introScrollPane.setPreferredSize(new Dimension(400, 150));

        // Panel for adding dynamic input rows
        ingredientRowsPanel.setLayout(new BoxLayout(ingredientRowsPanel, BoxLayout.Y_AXIS));
        introPanel.add(ingredientRowsPanel);

        // Button to add more ingredient rows
        JButton addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.addActionListener(evt -> addIngredientRow());

        introPanel.add(Box.createVerticalStrut(10)); // Add spacing
        introPanel.add(addIngredientButton);

        // Instructions panel
        JPanel cookPanel = new JPanel(new BorderLayout());
        JLabel cookLabel = new JLabel("Instructions:");
        cookArea = new JTextArea(3, 20);
        cookArea.setLineWrap(true);
        cookArea.setWrapStyleWord(true);
        cookPanel.add(cookLabel, BorderLayout.NORTH);
        cookPanel.add(new JScrollPane(cookArea), BorderLayout.CENTER);

        // Add listener to update instructions in state
        cookArea.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                CreateState currentState = createViewModel.getState();
                currentState.setInstructions(cookArea.getText().trim());
                createViewModel.setState(currentState);
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
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing between components
        mainPanel.add(introScrollPane); // Add the scrollable ingredients panel
        mainPanel.add(Box.createVerticalStrut(10)); // Add spacing
        mainPanel.add(cookPanel);

        this.add(mainPanel, BorderLayout.CENTER);

        // Lower panel with buttons
        JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        confirm = new JButton("Confirm");
        back = new JButton("Back");

        lowerPanel.add(back);
        lowerPanel.add(confirm);

        this.add(lowerPanel, BorderLayout.SOUTH);

        // Set action listeners if needed
        back.addActionListener(evt -> {
            if (backToEditViewController != null) {
                backToEditViewController.backToEditView();
            }
        });

        // Add an ActionListener to the "confirm" button to handle user input when clicked
        confirm.addActionListener(evt -> {
            // Check if the CreateController is initialized and available
            if (createController != null) {
                // Retrieve the current state of the CreateViewModel, which contains user-provided inputs
                final CreateState currentState = createViewModel.getState();

                // Extract the dish name entered by the user from the current state
                String dishName = currentState.getDishName();

                // Extract the recipe instructions entered by the user from the current state
                String instructions = currentState.getInstructions();

                // Convert the ingredients Map (immutable) from the current state to a mutable HashMap
                // This is necessary for further modifications or to meet method requirements
                HashMap<String, String> ingredients = new HashMap<>(currentState.getIngredients());

                // Execute the create logic by passing the user inputs (dish name, instructions, ingredients)
                // This will handle adding the new recipe to the backend or relevant data storage
                createController.execute(dishName, instructions, ingredients);

                // Add the newly created recipe to the "new_recipes.json" file for persistent storage
                // This ensures the newly added recipe is saved locally
                addToNewRecipesJson(dishName, instructions, ingredients);

                // If the EditView is available, refresh its dropdown menu to include the newly added recipe
                // This keeps the UI synchronized with the updated data
                if (editView != null) {
                    editView.loadNewRecipes();
                }
            }
        });


    }

    private void addIngredientRow() {
        JPanel newRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField ingredientNameField = new JTextField(15);
        JTextField ingredientAmountField = new JTextField(10);
        JButton deleteButton = new JButton("Delete");

        // Add action listener to delete the row
        deleteButton.addActionListener(e -> {
            ingredientRowsPanel.remove(newRow); // Remove the row
            updateIngredientsState(); // Update state after removing
            ingredientRowsPanel.revalidate(); // Update the layout
            ingredientRowsPanel.repaint();
        });

        // Add listeners to update state for ingredient fields
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

        // Add components to the row
        newRow.add(new JLabel("Name:"));
        newRow.add(ingredientNameField);
        newRow.add(new JLabel("Amount:"));
        newRow.add(ingredientAmountField);
        newRow.add(deleteButton);

        // Add the new row to the ingredients panel
        ingredientRowsPanel.add(newRow);
        ingredientRowsPanel.revalidate();
        ingredientRowsPanel.repaint();
    }

    private void updateIngredientsState() {
        CreateState currentState = createViewModel.getState();

        // Clear existing ingredients to avoid duplicates
        currentState.getIngredients().clear();

        for (Component comp : ingredientRowsPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel row = (JPanel) comp;
                JTextField nameField = (JTextField) row.getComponent(1);
                JTextField amountField = (JTextField) row.getComponent(3);

                String name = nameField.getText().trim();
                String amount = amountField.getText().trim();

                // Add ingredient directly to state
                currentState.addIngredient(name, amount);
            }
        }

        // Update ViewModel with the new state
        createViewModel.setState(currentState);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        CreateState state = (CreateState) evt.getNewValue();

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
                JPanel row = (JPanel) comp;

                // Retrieve the text fields for ingredient name and amount
                // These are located in the second and fourth components of the row
                JTextField nameField = (JTextField) row.getComponent(1);
                JTextField amountField = (JTextField) row.getComponent(3);

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
     * @param createController the CreateController instance to be associated with this view.
     */
    public void setCreateController(CreateController createController) {
        this.createController = createController;
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
            FileReader reader;
            JsonObject jsonObject;
            JsonArray recipesArray;

            try {
                // Attempt to read the existing "new_recipes.json" file
                reader = new FileReader("new_recipes.json");
                jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                // Parse the JSON content
                recipesArray = jsonObject.getAsJsonArray("recipes");
                // Get the "recipes" array
                reader.close();
                // Close the file reader
            } catch (IOException e) {
                // If the file does not exist, create a new JSON structure
                jsonObject = new JsonObject();
                // Create the root JSON object
                recipesArray = new JsonArray();
                // Create an empty JSON array for recipes
                jsonObject.add("recipes", recipesArray);
                // Add the array to the root object
            }

            // Create a new JSON object to represent the recipe
            JsonObject newRecipe = new JsonObject();
            newRecipe.addProperty("name", dishName);
            // Add the dish name to the recipe
            newRecipe.addProperty("instructions", instructions);
            // Add the instructions to the recipe

            // Add the ingredients as a JSON object
            JsonObject ingredientsObject = new JsonObject();
            for (String ingredient : ingredients.keySet()) {
                ingredientsObject.addProperty(ingredient, ingredients.get(ingredient));
                // Add each ingredient and its quantity
            }
            newRecipe.add("ingredients", ingredientsObject);
            // Add the ingredients object to the recipe

            // Add the new recipe to the recipes array
            recipesArray.add(newRecipe);

            // Write the updated JSON structure back to the file
            FileWriter writer = new FileWriter("new_recipes.json");
            writer.write(jsonObject.toString());
            // Convert the JSON structure to a string
            writer.close();
            // Close the file writer
        } catch (IOException e) {
            // Handle any I/O errors
            e.printStackTrace();
            // Print the error stack trace for debugging
            JOptionPane.showMessageDialog(this, "Error saving to new_recipes.json!"); // Show an error message to the user
        }
    }
}