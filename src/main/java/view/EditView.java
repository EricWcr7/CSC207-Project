package view;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.create.CreateController;
import interface_adapter.edit.EditController;
import interface_adapter.edit.EditViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Edit recipe";
    private final EditViewModel editViewModel;
    private EditController editController;
    private ReturnToSearchMenuController returnToSearchMenuController;

    private final JButton back;
    private final JButton addButton;

    public EditView(EditViewModel editViewModel) {
        this.editViewModel = editViewModel;
        this.editViewModel.addPropertyChangeListener(this);

        // Set layout manager for main panel
        this.setLayout(new BorderLayout());

        // Add label to the top (NORTH)
        JLabel titleLabel = new JLabel("my edit recipe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Adjust font as needed
        this.add(titleLabel, BorderLayout.NORTH);

        // Upper part (empty, can add components if needed)
        JPanel upperPanel = new JPanel();
        upperPanel.setPreferredSize(new Dimension(400, 200)); // Adjust the height as needed

        // Lower part (buttons)
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        addButton = new JButton("+");
        back = new JButton("Back");

        buttonsPanel.add(addButton);
        buttonsPanel.add(back);

        // Add panels to main layout
        this.add(upperPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Set action listeners if needed
        back.addActionListener(this);
        addButton.addActionListener(this);

        back.addActionListener(evt -> {
            if (evt.getSource().equals(back)) {
                this.returnToSearchMenuController.fromEditRecipeBackToSearchMenu();
            }
        });

        addButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addButton)) {
                        this.editController.switchToCreate();
                    }
                }
        );
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
}
