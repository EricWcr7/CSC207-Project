package view;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.create.CreateController;
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

public class EditView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Edit recipe";
    private final EditViewModel editViewModel;
    private EditController editController;
    private DeleteController deleteController;
    private ReturnToSearchMenuController returnToSearchMenuController;

    private final JComboBox<String> recipeComboBox = new JComboBox<>();

    private final JButton back;
    private final JButton addButton;
    private final JButton deleteButton;

    public EditView(EditViewModel editViewModel) {
        this.editViewModel = editViewModel;
        this.editViewModel.addPropertyChangeListener(this);

        // Set layout manager for main panel
        this.setLayout(new BorderLayout());

        // Add label to the top (NORTH)
        JLabel titleLabel = new JLabel("my edit recipe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Adjust font as needed
        this.add(titleLabel, BorderLayout.NORTH);

        // Upper part with JComboBox
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout());

        // Configure JComboBox
        recipeComboBox.setPreferredSize(new Dimension(300, 30));
        upperPanel.add(recipeComboBox);

        // Lower part (buttons)
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        addButton = new JButton("+");
        back = new JButton("Back");
        deleteButton = new JButton("Delete");

        buttonsPanel.add(addButton);
        buttonsPanel.add(back);
        buttonsPanel.add(deleteButton);

        // Add panels to main layout
        this.add(upperPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Set action listeners for buttons
        back.addActionListener(evt -> {
            if (evt.getSource().equals(back)) {
                this.returnToSearchMenuController.fromEditRecipeBackToSearchMenu();
            }
        });

        addButton.addActionListener(evt -> {
            if (evt.getSource().equals(addButton)) {
                this.editController.switchToCreate();
                // 添加新菜谱后刷新下拉框
                loadNewRecipes();
            }
        });


        deleteButton.addActionListener(evt -> {
            // 获取下拉框中选中的菜名
            String selectedRecipe = (String) recipeComboBox.getSelectedItem();

            if (selectedRecipe != null && !selectedRecipe.isEmpty()) {
                // 调用 DeleteController 进行删除逻辑
                deleteController.deleteRecipe(selectedRecipe);
            } else {
                // 如果没有选中任何菜名，提示用户
                JOptionPane.showMessageDialog(this, "Please select a recipe to delete!");
            }
            loadNewRecipes();
        });


        recipeComboBox.addActionListener(evt -> {
            String selectedRecipe = (String) recipeComboBox.getSelectedItem();
            if (selectedRecipe != null) {
                System.out.println("Selected recipe: " + selectedRecipe);
                // Future: Trigger editing logic for the selected recipe
            }
        });

        loadNewRecipes();
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

    public void setEditController(EditController editController) {
        this.editController = editController;
    }

    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    public void setDeleteController(DeleteController deleteController) {
        this.deleteController = deleteController; // 正确初始化 deleteController
    }

    public void loadNewRecipes() {
        try (FileReader reader = new FileReader("new_recipes.json")) {
            // 解析 JSON 文件
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray recipesArray = jsonObject.getAsJsonArray("recipes");

            // 清空下拉框内容
            recipeComboBox.removeAllItems();

            // 遍历 JSON 数组，将菜谱名称添加到下拉框
            for (int i = 0; i < recipesArray.size(); i++) {
                JsonObject recipe = recipesArray.get(i).getAsJsonObject();
                String recipeName = recipe.get("name").getAsString(); // 获取菜谱名称
                recipeComboBox.addItem(recipeName); // 添加到下拉框
            }

            // 确保下拉框刷新界面
            recipeComboBox.revalidate();
            recipeComboBox.repaint();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading recipes from new_recipes.json!");
        }
    }

}
