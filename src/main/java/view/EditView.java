package view;

import com.google.gson.*;
import entity.User;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.delete.DeleteController;
import interface_adapter.edit.EditController;
import interface_adapter.edit.EditViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileReader;
import java.io.IOException;


/**
 * The EditView class represents the graphical user interface for editing recipes.
 * It allows users to add, delete, and navigate recipes via a combo box and buttons.
 */
public class EditView extends JPanel implements ActionListener, PropertyChangeListener {

    // Name of the current view, used for UI navigation
    private final String viewName = "Edit recipe";

    // ViewModel containing state and data for this view
    private final EditViewModel editViewModel;

    // Controller to handle creating new recipes
    private EditController editController;

    // Controller to handle deleting recipes
    private DeleteController deleteController;

    // Controller to navigate back to the main search menu
    private ReturnToSearchMenuController returnToSearchMenuController;

    // ComboBox to display the list of recipes
    private final JComboBox<String> recipeComboBox = new JComboBox<>();

    // Buttons for adding, deleting, and going back
    private final JButton back;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton refreshButton;

    /**
     * Constructor for the EditView class.
     *
     * @param editViewModel The ViewModel associated with this view.
     */
    public EditView(EditViewModel editViewModel) {
        this.editViewModel = editViewModel;

        // Listen for property changes from the ViewModel
        this.editViewModel.addPropertyChangeListener(this);

        // Set layout for the main panel
        this.setLayout(new BorderLayout());

        // Create and add the title label at the top
        JLabel titleLabel = new JLabel("My Edit Recipe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set title font
        this.add(titleLabel, BorderLayout.NORTH);

        // Upper panel containing the combo box
        JPanel upperPanel = new JPanel(new FlowLayout());
        recipeComboBox.setPreferredSize(new Dimension(300, 30)); // Set size of the combo box
        upperPanel.add(recipeComboBox);
        this.add(upperPanel, BorderLayout.CENTER);

        // Lower panel containing buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        addButton = new JButton("+");
        back = new JButton("Back");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        buttonsPanel.add(addButton);
        buttonsPanel.add(back);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(refreshButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        back.addActionListener(evt -> {
            if (evt.getSource().equals(back)) {
                this.returnToSearchMenuController.fromEditRecipeBackToSearchMenu();
            }
        });

        addButton.addActionListener(evt -> {
            if (evt.getSource().equals(addButton)) {
                // Switch to the create recipe view
                this.editController.switchToCreate();
                // Refresh the combo box after adding a new recipe

            }
        });

        deleteButton.addActionListener(evt -> {
            // Get the selected recipe from the combo box
            String selectedRecipe = (String) recipeComboBox.getSelectedItem();

            if (selectedRecipe != null && !selectedRecipe.isEmpty()) {
                // Call the DeleteController to handle the deletion logic
                deleteController.deleteRecipe(selectedRecipe);
                // Refresh the combo box after deleting a recipe
                loadCreatedRecipes();
            } else {
                // Show an error message if no recipe is selected
                JOptionPane.showMessageDialog(this, "Please select a recipe to delete!");
            }
        });

        refreshButton.addActionListener(evt -> {
            loadCreatedRecipes(); // 调用刷新下拉框的方法
        });

        recipeComboBox.addActionListener(evt -> {
            // Print the selected recipe to the console for debugging
            String selectedRecipe = (String) recipeComboBox.getSelectedItem();
            if (selectedRecipe != null) {
                System.out.println("Selected recipe: " + selectedRecipe);
            }
        });

        // Load the initial list of recipes into the combo box


    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Print the action command to the console for debugging
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes from the ViewModel (not yet implemented)
    }

    /**
     * Retrieves the name of this view.
     *
     * @return The name of the view as a string.
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the EditController for this view.
     *
     * @param editController The EditController to handle editing logic.
     */
    public void setEditController(EditController editController) {
        this.editController = editController;
    }

    /**
     * Sets the ReturnToSearchMenuController for navigating back to the search menu.
     *
     * @param returnToSearchMenuController The controller to handle navigation.
     */
    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    /**
     * Sets the DeleteController for handling delete operations.
     *
     * @param deleteController The controller to handle recipe deletion logic.
     */
    public void setDeleteController(DeleteController deleteController) {
        this.deleteController = deleteController; // Correctly initialize the DeleteController
    }

    private void updateRecipeComboBox(JsonArray recipeCreatedArray) {
        // 清空下拉框
        recipeComboBox.removeAllItems();

        // 遍历菜谱列表，加载到下拉框中
        for (JsonElement recipeElement : recipeCreatedArray) {
            JsonObject recipe = recipeElement.getAsJsonObject();
            String recipeName = recipe.get("name").getAsString();
            recipeComboBox.addItem(recipeName);
        }

        // 刷新下拉框
        recipeComboBox.revalidate();
        recipeComboBox.repaint();
    }


    public void loadCreatedRecipes() {
        try {
            // Step 1: 从 Session 中获取当前用户
            User currentUser = util.Session.getCurrentUser();
            if (currentUser == null) {
                JOptionPane.showMessageDialog(this, "No user is currently logged in!");
                return;
            }

            String username = currentUser.getName();

            // Step 2: 读取 all_users.json 文件
            try (FileReader reader = new FileReader("all_users.json")) {
                JsonObject allUsers = JsonParser.parseReader(reader).getAsJsonObject();

                // Step 3: 找到对应的用户 JSON 对象
                JsonObject userJson = allUsers.getAsJsonObject(username);
                if (userJson == null) {
                    JOptionPane.showMessageDialog(this, "User not found in JSON: " + username);
                    return;
                }

                // Step 4: 获取 recipeCreated 列表
                JsonArray recipeCreatedArray = userJson.getAsJsonArray("recipeCreated");
                if (recipeCreatedArray == null || recipeCreatedArray.size() == 0) {
                    recipeComboBox.removeAllItems();
                    recipeComboBox.addItem("No recipes created"); // 默认选项
                    recipeComboBox.revalidate();
                    recipeComboBox.repaint();

                    return;
                }

                // Step 5: 更新下拉框
                updateRecipeComboBox(recipeCreatedArray);

                // 默认选中第一个菜谱
                if (recipeComboBox.getItemCount() > 0) {
                    recipeComboBox.setSelectedIndex(0);
                }

                // Debug log
                System.out.println("Loaded recipes for user: " + username);
                System.out.println("Recipes: " + recipeCreatedArray);
                System.out.println("Number of recipes: " + recipeCreatedArray.size());
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user data from all_users.json!");
        } catch (JsonSyntaxException e) {
            JOptionPane.showMessageDialog(this, "User data file format is invalid.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage());
        }
    }


}
