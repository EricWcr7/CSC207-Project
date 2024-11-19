package view;

import interface_adapter.BackToEditView.BackToEditViewController;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.create.CreateController;
import interface_adapter.create.CreateViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Create recipe";
    private final CreateViewModel createViewModel;
    private CreateController createController;
    private BackToEditViewController backToEditViewController;

    private final JButton back;
    private final JButton confirm;

    public CreateView(CreateViewModel createViewModel) {
        this.createViewModel = createViewModel;
        this.createViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        // Add label to the top (NORTH)
        JLabel titleLabel = new JLabel("Create recipe", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Adjust font as needed
        this.add(titleLabel, BorderLayout.NORTH);

        // Upper panel with text fields and labels
        JPanel upperPanel = new JPanel(new GridLayout(3, 2, 5, 10)); // 3 rows, 2 columns, with spacing
        JLabel nameLabel = new JLabel("Dish name:");
        JTextField nameField = new JTextField(20);

        JLabel introLabel = new JLabel("Introduction:");
        JTextArea introArea = new JTextArea(3, 20);
        introArea.setLineWrap(true);
        introArea.setWrapStyleWord(true);

        JLabel cookLabel = new JLabel("How to cook:");
        JTextArea cookArea = new JTextArea(3, 20);
        cookArea.setLineWrap(true);
        cookArea.setWrapStyleWord(true);

        upperPanel.add(nameLabel);
        upperPanel.add(nameField);
        upperPanel.add(introLabel);
        upperPanel.add(new JScrollPane(introArea)); // Add scroll for large text areas
        upperPanel.add(cookLabel);
        upperPanel.add(new JScrollPane(cookArea)); // Add scroll for large text areas

        this.add(upperPanel, BorderLayout.CENTER);

        // Lower panel with buttons
        JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        confirm = new JButton("Confirm");
        back = new JButton("Back");

        lowerPanel.add(back);
        lowerPanel.add(confirm);

        this.add(lowerPanel, BorderLayout.SOUTH);

        // Set action listeners if needed
        back.addActionListener(this);
        confirm.addActionListener(this);

        back.addActionListener(evt -> {
            if (evt.getSource().equals(back)) {
                this.backToEditViewController.backToEditView();
            }
        });
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

    public void setCreateController(CreateController createController) {
        this.createController = createController;
    }

    public void setBackToEditViewConTroller(BackToEditViewController backToEditViewConTroller) {
        this.backToEditViewController = backToEditViewConTroller;
}}
