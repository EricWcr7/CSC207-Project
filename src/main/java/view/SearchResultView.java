package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.logout.LogoutController;
import interface_adapter.recipe_search.RecipeSearchController;
import interface_adapter.recipe_search.RecipeSearchState;
import interface_adapter.recipe_search.RecipeSearchViewModel;

//public class RecipeSearchView extends JPanel implements PropertyChangeListener {
public class SearchResultView extends JPanel {

    private final String viewName = "search result";
    private final RecipeSearchViewModel recipeSearchViewModel;
    private RecipeSearchController recipeSearchController;

    private final JButton back;
    private final JButton search;

    private final JTextField dishInputField = new JTextField(50);

    public SearchResultView(RecipeSearchViewModel recipeSearchViewModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
//        this.recipeSearchViewModel.addPropertyChangeListener(this);

        final JPanel searchPanel = new JPanel();
        search = new JButton("Search");
        searchPanel.add(dishInputField);
        searchPanel.add(search);
        final JLabel title = new JLabel("Please enter the dish you want to search for");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        back = new JButton("back");
        buttons.add(back);

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
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    // 监听 点击search 的行为
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            // 返回一个初始化的search state
                            // 由于监听器的存在 所以会实时的更新 current state中的内容 然后controller就能
                            // 根据这些数据开始run usecase
                            final RecipeSearchState currentState = recipeSearchViewModel.getState();

                            recipeSearchController.execute(
                                    currentState.getSearchKeyword()
                            );
                        }
                    }
                }
        );

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//    }
    }

    public String getViewName() {
        return viewName; }
}



