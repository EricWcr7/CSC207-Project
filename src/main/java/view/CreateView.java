package view;

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

    public CreateView(CreateViewModel createViewModel) {
        this.createViewModel = createViewModel;
        this.createViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        // Add label to the top (NORTH)
        JLabel titleLabel = new JLabel("Create recipe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Adjust font as needed
        this.add(titleLabel, BorderLayout.NORTH);

        // Main panel to hold sub-panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Dish name panel (Single-row JTextField)
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
        JLabel nameLabel = new JLabel("Dish name:");
        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(300, 25)); // Set a fixed width and height for JTextField
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

        confirm.addActionListener(evt -> {
            if (createController != null) {
                final CreateState currentState = createViewModel.getState();
                createController.execute(currentState.getDishName(), currentState.getInstructions(), currentState.getIngredients());
            }
        });
    }

    private void addIngredientRow() {
        JPanel newRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField ingredientNameField = new JTextField(15);
        JTextField ingredientAmountField = new JTextField(10);
        JButton deleteButton = new JButton("Delete");

        // Add action listener to delete the row
        deleteButton.addActionListener(e-> {
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

    private void resetFields() {
        // 清空菜名错误提示
        dishNameErrorField.setText("");

        // 清空菜名输入框
        nameField.setText("");

        // 清空说明输入框
        cookArea.setText("");

        // 清空配料行
        for (Component comp : ingredientRowsPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel row = (JPanel) comp;
                JTextField nameField = (JTextField) row.getComponent(1);
                JTextField amountField = (JTextField) row.getComponent(3);
                nameField.setText("");
                amountField.setText("");
            }
        }

        // 移除所有配料行
        ingredientRowsPanel.removeAll();
        ingredientRowsPanel.revalidate();
        ingredientRowsPanel.repaint();
    }

    public String getViewName() {
        return viewName;
    }

    public void setCreateController(CreateController createController) {
        this.createController = createController;
    }

    public void setBackToEditViewConTroller(BackToEditViewController backToEditViewConTroller) {
        this.backToEditViewController = backToEditViewConTroller;
    }
}
