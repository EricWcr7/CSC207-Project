package app;

import java.awt.CardLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.FavoriteRecipeDataAccessObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import data_access.InMemoryUserDataAccessObject;
import data_access.RecipeDataAccessObject;
import entity.*;
import interface_adapter.*;
import interface_adapter.BackToEditView.BackToEditViewController;
import interface_adapter.BackToEditView.BackToEditViewPresenter;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuPresenter;
import interface_adapter.change_password.*;
import interface_adapter.choose_recipe.*;
import interface_adapter.create.CreateController;
import interface_adapter.create.CreatePresenter;
import interface_adapter.create.CreateViewModel;
import interface_adapter.delete.DeleteController;
import interface_adapter.delete.DeletePresenter;
import interface_adapter.delete.DeleteViewModel;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import interface_adapter.edit.EditController;
import interface_adapter.edit.EditPresenter;
import interface_adapter.edit.EditViewModel;
import interface_adapter.favorite_recipe.FavoriteRecipeViewModel;
import interface_adapter.like_and_dislike.dislike_a_recipe.DislikeRecipeController;
import interface_adapter.like_and_dislike.dislike_a_recipe.DislikeRecipePresenter;
import interface_adapter.like_and_dislike.like_a_recipe.LikeRecipeController;
import interface_adapter.like_and_dislike.like_a_recipe.LikeRecipePresenter;
import interface_adapter.login.*;
import interface_adapter.logout.*;
import interface_adapter.recipe_search.*;
import interface_adapter.shopping_list.ShoppingListController;
import interface_adapter.shopping_list.ShoppingListPresenter;
import interface_adapter.shopping_list.ShoppingListViewModel;
import interface_adapter.signup.*;
import interface_adapter.favorite_recipe.FavoriteRecipeController;
import interface_adapter.favorite_recipe.FavoriteRecipePresenter;
import use_case.BackToEditView.BackToEditViewInputBoundary;
import use_case.BackToEditView.BackToEditViewInteractor;
import use_case.BackToEditView.BackToEditViewOutputBoundary;
import use_case.returnToSearchMenu.ReturnToSearchMenuInputBoundary;
import use_case.returnToSearchMenu.ReturnToSearchMenuInteractor;
import use_case.returnToSearchMenu.ReturnToSearchMenuOutputBoundary;
import use_case.change_password.*;
import use_case.choose_recipe.ChooseRecipeInputBoundary;
import use_case.choose_recipe.ChooseRecipeInteractor;
import use_case.choose_recipe.ChooseRecipeOutputBoundary;
import use_case.create.CreateInputBoundary;
import use_case.create.CreateInteractor;
import use_case.create.CreateOutputBoundary;
import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.edit.EditInputBoundary;
import use_case.edit.EditInteractor;
import use_case.edit.EditOutputBoundary;
import use_case.favorite_receipe.FavoriteRecipeInputBoundary;
import use_case.favorite_receipe.FavoriteRecipeInteractor;
import use_case.favorite_receipe.FavoriteRecipeOutputBoundary;
import use_case.like_and_dislike_a_recipe.LikeAndDislikeRecipeInputBoundary;
import use_case.like_and_dislike_a_recipe.dislike.DislikeRecipeInteractor;
import use_case.like_and_dislike_a_recipe.dislike.DislikeRecipeOutputBoundary;
import use_case.like_and_dislike_a_recipe.like.LikeRecipeInteractor;
import use_case.like_and_dislike_a_recipe.like.LikeRecipeOutputBoundary;
import use_case.login.*;
import use_case.logout.*;
import use_case.recipe_search.*;
import use_case.shopping_list.ShoppingListInputBoundary;
import use_case.shopping_list.ShoppingListInteractor;
import use_case.shopping_list.ShoppingListOutputBoundary;
import use_case.signup.*;
import view.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final RecipeFactory recipeFactory = new CommonRecipeFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final RecipeDataAccessObject recipeDataAccessObject = new RecipeDataAccessObject();
    private final FavoriteRecipeDataAccessObject favoriteRecipeDataAccessObject = new FavoriteRecipeDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private RecipeSearchView recipeSearchView;
    private RecipeSearchViewModel recipeSearchViewModel;
    private ChooseRecipeView chooseRecipeView;
    private ChooseRecipeViewModel chooseRecipeViewModel;
    private DisplayRecipeView displayRecipeView;
    private DisplayRecipeViewModel displayRecipeViewModel;
    private FavoriteRecipeView favoriteRecipeView;
    private FavoriteRecipeViewModel favoriteRecipeViewModel;
    private EditView editView;
    private EditViewModel editViewModel;
    private CreateView createView;
    private CreateViewModel createViewModel;
    private ShoppingListView shoppingListView;
    private ShoppingListViewModel shoppingListViewModel;
    private DeleteViewModel deleteViewModel;

    private RecipeSearchInputBoundary recipeSearchInteractor;
    private LoginInputBoundary loginInteractor;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Initializes the shared recipe storage with all recipes from the API.
     */
    private void initializeSharedRecipeStorage() {
        if (recipeSearchInteractor != null) {
            System.out.println("Calling initializeRecipeStorage in RecipeSearchInteractor...");
            recipeSearchInteractor.initializeRecipeStorage();
        } else {
            System.err.println("RecipeSearchInteractor not initialized. Ensure addRecipeSearchUseCase is called first.");
        }
    }

    private void initializeSharedUserStorage() {
        if (loginInteractor != null) {
            System.out.println("Calling initializeUserStorage in LogInInteractor...");
            loginInteractor.initializeUserStorage();
        } else {
            System.err.println("LogInInteractor not initialized. Ensure addLogInUseCase is called first.");
        }
    }

    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        System.out.println("Adding Login View with name: " + loginView.getViewName());
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        System.out.println("Adding Signup View with name: " + signupView.getViewName());
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        System.out.println("Adding LoggedIn View with name: " + loggedInView.getViewName());
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    public AppBuilder addRecipeSearchView() {
        recipeSearchViewModel = new RecipeSearchViewModel();
        recipeSearchView = new RecipeSearchView(recipeSearchViewModel);
        System.out.println("Adding Recipe Search View with name: " + recipeSearchView.getViewName());
        cardPanel.add(recipeSearchView, recipeSearchView.getViewName());
        return this;
    }

    public AppBuilder addChooseRecipeView() {
        chooseRecipeViewModel = new ChooseRecipeViewModel();
        chooseRecipeView = new ChooseRecipeView(chooseRecipeViewModel);
        System.out.println("Adding Choose Recipe View with name: " + chooseRecipeView.getViewName());
        cardPanel.add(chooseRecipeView, chooseRecipeView.getViewName());
        return this;
    }

    /**
     * Adds the Display Recipe View to the application.
     *
     * @return this builder
     */
    public AppBuilder addDisplayRecipeView() {
        displayRecipeViewModel = new DisplayRecipeViewModel();
        displayRecipeView = new DisplayRecipeView(displayRecipeViewModel);
        System.out.println("Adding Display Recipe View with name: " + displayRecipeView.getViewName());
        cardPanel.add(displayRecipeView, displayRecipeView.getViewName());
        return this;
    }

    public AppBuilder addFavoriteRecipeView() {
        favoriteRecipeViewModel = new FavoriteRecipeViewModel();
        favoriteRecipeView = new FavoriteRecipeView(favoriteRecipeViewModel);
        System.out.println("Adding Display Recipe View with name: " + favoriteRecipeView.getViewName());
        cardPanel.add(favoriteRecipeView, favoriteRecipeView.getViewName());
        return this;
    }

    public AppBuilder addEditView() {
        editViewModel = new EditViewModel();
        editView = new EditView(editViewModel);
        System.out.println("Adding Edit View with name: " + editView.getViewName());
        cardPanel.add(editView, editView.getViewName());
        return this;
    }

    public AppBuilder addCreateView() {
        createViewModel = new CreateViewModel();
        createView = new CreateView(createViewModel,editView); // 注入 EditView 实例
        System.out.println("Adding Create View with name: " + createView.getViewName());
        cardPanel.add(createView, createView.getViewName());
        return this;
    }

    public AppBuilder addShoppingListView() {
        shoppingListViewModel = new ShoppingListViewModel();
        shoppingListView = new ShoppingListView(shoppingListViewModel);
        System.out.println("Adding Display Recipe View with name: " + favoriteRecipeView.getViewName());
        cardPanel.add(shoppingListView, shoppingListView.getViewName());
        return this;
    }

    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                recipeSearchViewModel, loginViewModel, signupViewModel);
        loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                recipeSearchViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        recipeSearchView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addReturnToSearchMenuUseCase() {
        final ReturnToSearchMenuOutputBoundary returnToSearchMenuOutputBoundary =
                new ReturnToSearchMenuPresenter(viewManagerModel,
                        recipeSearchViewModel, chooseRecipeViewModel,
                        displayRecipeViewModel, favoriteRecipeViewModel, editViewModel, shoppingListViewModel);

        final ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor =
                new ReturnToSearchMenuInteractor(returnToSearchMenuOutputBoundary, userDataAccessObject,
                        userDataAccessObject);

        final ReturnToSearchMenuController returnToSearchMenuController = new ReturnToSearchMenuController(returnToSearchMenuInteractor);
        chooseRecipeView.setReturnToSearchMenuController(returnToSearchMenuController);
        displayRecipeView.setReturnToSearchMenuController(returnToSearchMenuController);
        favoriteRecipeView.setReturnToSearchMenuController(returnToSearchMenuController);
        editView.setReturnToSearchMenuController(returnToSearchMenuController);
        shoppingListView.setReturnToSearchMenuController(returnToSearchMenuController);
        return this;
    }

    public AppBuilder addBackTOEditViewUsecase() {
        final BackToEditViewOutputBoundary backToEditViewOutputBoundary = new BackToEditViewPresenter(viewManagerModel,
                editViewModel, createViewModel);

        final BackToEditViewInputBoundary backToEditViewInteractor =
                new BackToEditViewInteractor(backToEditViewOutputBoundary);

        final BackToEditViewController backToEditViewController = new BackToEditViewController(backToEditViewInteractor);
        createView.setBackToEditViewConTroller(backToEditViewController);
        return this;
    }

    public AppBuilder addRecipeSearchUseCase() {
        final RecipeSearchOutputBoundary recipeSearchOutputBoundary = new RecipeSearchPresenter(
                viewManagerModel, chooseRecipeViewModel, favoriteRecipeViewModel, editViewModel, recipeSearchViewModel);

        recipeSearchInteractor = new RecipeSearchInteractor(recipeDataAccessObject, recipeSearchOutputBoundary, userDataAccessObject);

        final RecipeSearchController recipeSearchController = new RecipeSearchController(recipeSearchInteractor);
        recipeSearchView.setRecipeSearchController(recipeSearchController);
        return this;
    }

    public AppBuilder addChooseRecipeUseCase() {
        final ChooseRecipeOutputBoundary chooseRecipeOutputBoundary = new ChooseRecipePresenter(
                viewManagerModel, chooseRecipeViewModel, displayRecipeViewModel);

        final ChooseRecipeInputBoundary chooseRecipeInteractor = new ChooseRecipeInteractor(
                recipeDataAccessObject, chooseRecipeOutputBoundary);

        final ChooseRecipeController chooseRecipeController = new ChooseRecipeController(chooseRecipeInteractor);
        chooseRecipeView.setChooseRecipeController(chooseRecipeController);
        favoriteRecipeView.setChooseRecipeController(chooseRecipeController);
        return this;
    }

    public AppBuilder addFavoriteRecipeUseCase() {
        final FavoriteRecipeOutputBoundary favoriteRecipeOutputBoundary = new FavoriteRecipePresenter(
                viewManagerModel, favoriteRecipeViewModel, shoppingListViewModel);

        final FavoriteRecipeInputBoundary favoriteRecipeInteractor = new FavoriteRecipeInteractor(
                favoriteRecipeOutputBoundary, userDataAccessObject, userDataAccessObject);

        final FavoriteRecipeController favoriteRecipeController = new FavoriteRecipeController(favoriteRecipeInteractor);
        favoriteRecipeView.setFavoriteRecipeController(favoriteRecipeController);
        displayRecipeView.setFavoriteRecipeController(favoriteRecipeController);
        return this;
    }

    public AppBuilder addShoppingListUseCase() {
        final ShoppingListOutputBoundary shoppingListPresenter = new ShoppingListPresenter(viewManagerModel,
                shoppingListViewModel);

        final ShoppingListInputBoundary shoppingListInteractor = new ShoppingListInteractor(shoppingListPresenter,
                recipeDataAccessObject);

        final ShoppingListController shoppingListController = new ShoppingListController(shoppingListInteractor);

        shoppingListView.setShoppingListController(shoppingListController);
        return this;
    }


    public AppBuilder addEditUseCase() {
        final EditOutputBoundary editOutputBoundary = new EditPresenter(viewManagerModel, createViewModel, editViewModel);

        final EditInputBoundary editInteractor = new EditInteractor(editOutputBoundary);

            final EditController editController = new EditController(editInteractor);
            editView.setEditController(editController);
            return this;
        }

    public AppBuilder addCreateUseCase() {
        final CreateOutputBoundary createOutputBoundary = new CreatePresenter(viewManagerModel, recipeSearchViewModel, createViewModel);
        final CreateInputBoundary createInteractor = new CreateInteractor(createOutputBoundary, recipeFactory, recipeDataAccessObject);

        final CreateController createController = new CreateController(createInteractor);
        createView.setCreateController(createController);
        return this;
    }

    public AppBuilder addLikeRecipeUseCase() {
        final LikeRecipeOutputBoundary likeRecipeOutputBoundary = new LikeRecipePresenter(displayRecipeViewModel);

        final LikeAndDislikeRecipeInputBoundary likeRecipeInteractor = new LikeRecipeInteractor(
                recipeDataAccessObject, userDataAccessObject, likeRecipeOutputBoundary);

        final LikeRecipeController likeRecipeController = new LikeRecipeController(likeRecipeInteractor);
        displayRecipeView.setLikeRecipeController(likeRecipeController);
        return this;
    }

    public AppBuilder addDislikeRecipeUseCase() {
        final DislikeRecipeOutputBoundary dislikeRecipeOutputBoundary = new DislikeRecipePresenter(displayRecipeViewModel);

        final LikeAndDislikeRecipeInputBoundary dislikeRecipeInteractor = new DislikeRecipeInteractor(
                recipeDataAccessObject, userDataAccessObject, dislikeRecipeOutputBoundary);

        final DislikeRecipeController dislikeRecipeController = new DislikeRecipeController(dislikeRecipeInteractor);
        displayRecipeView.setDislikeRecipeController(dislikeRecipeController);
        return this;
    }

    public AppBuilder addDeleteUseCase() {
        // 创建 DeleteViewModel
        final DeleteViewModel deleteViewModel = new DeleteViewModel();

        // 创建 DeletePresenter（实现 DeleteOutputBoundary）
        final DeleteOutputBoundary deleteOutputBoundary = new DeletePresenter(deleteViewModel);

        // 创建 DeleteInteractor（实现 DeleteInputBoundary）
        final DeleteInputBoundary deleteInteractor = new DeleteInteractor(deleteOutputBoundary, recipeDataAccessObject);

        // 创建 DeleteController
        final DeleteController deleteController = new DeleteController(deleteInteractor);

        // 将 DeleteController 设置到 EditView
        editView.setDeleteController(deleteController);

        System.out.println("Delete Use Case added successfully.");
        return this;
    }

    public void initializeNewRecipesFile() {
        File file = new File("new_recipes.json");
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("recipes", new JsonArray()); // 初始化空的 "recipes" 数组
                writer.write(jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to initialize new_recipes.json file.");
            }
        }
    }

    public JFrame build() {
            final JFrame application = new JFrame("Mealmaster");
            application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initializeSharedUserStorage();
        initializeSharedRecipeStorage();

        application.add(cardPanel);

        System.out.println("Setting initial view state to: " + loginView.getViewName());
        viewManagerModel.setState(loginView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}



