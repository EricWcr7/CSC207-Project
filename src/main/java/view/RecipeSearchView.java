package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.logout.LogoutController;
import interface_adapter.recipe_search.RecipeSearchController;
import interface_adapter.recipe_search.RecipeSearchState;
import interface_adapter.recipe_search.RecipeSearchViewModel;

public class RecipeSearchView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "search recipe";
    private final RecipeSearchViewModel recipeSearchViewModel;
    private RecipeSearchController recipeSearchController;
    private LogoutController logoutController;

    private final JButton logOut;
    private final JButton edit;
    private final JButton favorite;
    private final JButton search;

    private final JTextField dishInputField = new JTextField(50);

    public RecipeSearchView(RecipeSearchViewModel recipeSearchViewModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.recipeSearchViewModel.addPropertyChangeListener(this);

        final JPanel searchPanel = new JPanel();
        search = new JButton("Search");
        searchPanel.add(dishInputField);
        searchPanel.add(search);
        final JLabel title = new JLabel("Please enter the dish you want to search for");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        edit = new JButton("My Edit recipe");
        buttons.add(edit);

        favorite = new JButton("My favorite recipe");
        buttons.add(favorite);

        logOut = new JButton("Log Out");
        buttons.add(logOut);

        this.add(title);
        this.add(searchPanel);
        this.add(buttons);

        dishInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setSearchKeyWord(dishInputField.getText());
                recipeSearchViewModel.setState(currentState);
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

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            final RecipeSearchState currentState = recipeSearchViewModel.getState();
                            // Use searchRecipe instead of execute
                            recipeSearchController.execute(
                                    currentState.getSearchKeyword()
                            );
                        }
                    }
                }
        );

        logOut.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        this.logoutController.execute();
                    }
                }
        );

        edit.addActionListener(
                evt -> {
                    if (evt.getSource().equals(edit)) {
                        recipeSearchController.switchToEditView();
                    }
                }
        );

        favorite.addActionListener(
                evt -> {
                    if (evt.getSource().equals(favorite)) {
                        recipeSearchController.switchToFavoriteRecipeView();
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
        final RecipeSearchState state = (RecipeSearchState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(RecipeSearchState state) {
        dishInputField.setText(state.getSearchKeyword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setRecipeSearchController(RecipeSearchController recipeSearchController) {
        this.recipeSearchController = recipeSearchController;
    }
}



