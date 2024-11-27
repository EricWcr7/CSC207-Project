package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.FavoriteRecipeDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import data_access.RecipeDataAccessObject;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import entity.RecipeFactory;
import entity.UserFactory;
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
import use_case.ReturnToSearchMenu.ReturnToSearchMenuInputBoundary;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuInteractor;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuOutputBoundary;
import use_case.change_password.*;
import use_case.choose_recipe.ChooseRecipeInputBoundary;
import use_case.choose_recipe.ChooseRecipeInteractor;
import use_case.choose_recipe.ChooseRecipeOutputBoundary;
import use_case.create.CreateInputBoundary;
import use_case.create.CreateInteractor;
import use_case.create.CreateOutputBoundary;
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

/**
 * The AppBuilder class is responsible for constructing and initializing
 * the components of the Mealmaster application, including views, use cases,
 * and shared resources. It provides methods for adding views, configuring use cases,
 * and building the main application window.
 * The AppBuilder uses a card layout to manage the application's views and
 * facilitates navigation between them. It follows a modular design, enabling
 * easy addition and modification of functionality.
 * Key Responsibilities:
 * dding and initializing views such as Login, Signup, and Recipe Search views.
 * Configuring use cases for features like login, recipe creation, and shopping lists.
 * Managing shared resources like user and recipe storage.
 * Constructing the main application window with proper initialization.
 *
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final RecipeFactory recipeFactory = new CommonRecipeFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
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
        }
        else {
            System.err.println("RecipeSearchInteractor not initialized.Ensure addRecipeSearchUseCase is called first.");
        }
    }

    private void initializeSharedUserStorage() {
        if (loginInteractor != null) {
            System.out.println("Calling initializeUserStorage in LogInInteractor...");
            loginInteractor.initializeUserStorage();
        }
        else {
            System.err.println("LogInInteractor not initialized. Ensure addLogInUseCase is called first.");
        }
    }

    /**
     * Adds and initializes the "Login View" to the application.
     * This method creates a new instance of the login view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        System.out.println("Adding Login View with name: " + loginView.getViewName());
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds and initializes the "Signup View" to the application.
     * This method creates a new instance of the signup view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        System.out.println("Adding Signup View with name: " + signupView.getViewName());
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds and initializes the "Logged In View" to the application.
     * This method creates a new instance of the logged-in view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        System.out.println("Adding LoggedIn View with name: " + loggedInView.getViewName());
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds and initializes the "Recipe Search View" to the application.
     * This method creates a new instance of the recipe search view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addRecipeSearchView() {
        recipeSearchViewModel = new RecipeSearchViewModel();
        recipeSearchView = new RecipeSearchView(recipeSearchViewModel);
        System.out.println("Adding Recipe Search View with name: " + recipeSearchView.getViewName());
        cardPanel.add(recipeSearchView, recipeSearchView.getViewName());
        return this;
    }

    /**
     * Adds and initializes the "Choose Recipe View" to the application.
     * This method creates a new instance of the choose recipe view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
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
        System.out.println("Adding a Display Recipe View with name: " + displayRecipeView.getViewName());
        cardPanel.add(displayRecipeView, displayRecipeView.getViewName());
        return this;
    }

    /**
     * Adds and initializes the "Favorite Recipe View" to the application.
     * This method creates a new instance of the favorite recipe view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addFavoriteRecipeView() {
        favoriteRecipeViewModel = new FavoriteRecipeViewModel();
        favoriteRecipeView = new FavoriteRecipeView(favoriteRecipeViewModel);
        System.out.println("Adding Display Recipe View with name: " + favoriteRecipeView.getViewName());
        cardPanel.add(favoriteRecipeView, favoriteRecipeView.getViewName());
        return this;
    }

    /**
     * Adds and initializes the "Edit View" to the application.
     * This method creates a new instance of the edit view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addEditView() {
        editViewModel = new EditViewModel();
        editView = new EditView(editViewModel);
        System.out.println("Adding Edit View with name: " + editView.getViewName());
        cardPanel.add(editView, editView.getViewName());
        return this;
    }

    /**
     * Adds and initializes the "Create View" to the application.
     * This method creates a new instance of the create view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addCreateView() {
        createViewModel = new CreateViewModel();
        createView = new CreateView(createViewModel);
        System.out.println("Adding Create View with name: " + createView.getViewName());
        cardPanel.add(createView, createView.getViewName());
        return this;
    }

    /**
     * Adds and initializes the "Shopping List View" to the application.
     * This method creates a new instance of the shopping list view and its associated view model,
     * assigns the view a unique name, and adds it to the card panel for display.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addShoppingListView() {
        shoppingListViewModel = new ShoppingListViewModel();
        shoppingListView = new ShoppingListView(shoppingListViewModel);
        System.out.println("Adding Display Recipe View with name: " + favoriteRecipeView.getViewName());
        cardPanel.add(shoppingListView, shoppingListView.getViewName());
        return this;
    }

    /**
     * Configures and adds the "Signup" use case to the application.
     * This method initializes the components necessary for user signup functionality,
     * including the presenter, interactor, and controller. It assigns the controller
     * to the signup view, enabling users to create new accounts in the application.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Configures and adds the "Login" use case to the application.
     * This method sets up the necessary components for handling user login,
     * including the presenter, interactor, and controller. It assigns the controller
     * to the login view, enabling users to log in to the application.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                recipeSearchViewModel, loginViewModel, signupViewModel);
        loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Configures and adds the "Change Password" use case to the application.
     * This method initializes the components required for handling password changes,
     * including the presenter, interactor, and controller. It assigns the controller
     * to the logged-in view, enabling users to update their password securely.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
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

    /**
     * Configures and adds the "Logout" use case to the application.
     * This method sets up the components needed for handling user logout,
     * including the presenter, interactor, and controller. It connects the controller
     * to the recipe search view, enabling users to log out and return to the login interface.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                recipeSearchViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        recipeSearchView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Configures and adds the "Return to Search Menu" use case to the application.
     * This method initializes the components required for handling the functionality
     * of returning to the search menu, including the presenter, interactor, and controller.
     * It assigns the controller to multiple views, enabling the transition back to the search menu
     * from various parts of the application.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addReturnToSearchMenuUseCase() {
        final ReturnToSearchMenuOutputBoundary returnToSearchMenuOutputBoundary =
                new ReturnToSearchMenuPresenter(viewManagerModel,
                        recipeSearchViewModel, chooseRecipeViewModel,
                        displayRecipeViewModel, favoriteRecipeViewModel, editViewModel, shoppingListViewModel);

        final ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor =
                new ReturnToSearchMenuInteractor(returnToSearchMenuOutputBoundary, userDataAccessObject,
                        userDataAccessObject);

        final ReturnToSearchMenuController returnToSearchMenuController =
                new ReturnToSearchMenuController(returnToSearchMenuInteractor);
        chooseRecipeView.setReturnToSearchMenuController(returnToSearchMenuController);
        displayRecipeView.setReturnToSearchMenuController(returnToSearchMenuController);
        favoriteRecipeView.setReturnToSearchMenuController(returnToSearchMenuController);
        editView.setReturnToSearchMenuController(returnToSearchMenuController);
        shoppingListView.setReturnToSearchMenuController(returnToSearchMenuController);
        return this;
    }

    /**
     * Configures and adds the "Back to Edit View" use case to the application.
     * This method initializes the components required for handling the transition
     * back to the edit view, including the presenter, interactor, and controller.
     * It assigns the controller to the create view, enabling navigation back to the
     * edit view as part of the application's workflow.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addBackToEditViewUsecase() {
        final BackToEditViewOutputBoundary backToEditViewOutputBoundary = new BackToEditViewPresenter(viewManagerModel,
                editViewModel, createViewModel);

        final BackToEditViewInputBoundary backToEditViewInteractor =
                new BackToEditViewInteractor(backToEditViewOutputBoundary);

        final BackToEditViewController backToEditViewController = new BackToEditViewController(backToEditViewInteractor);
        createView.setBackToEditViewConTroller(backToEditViewController);
        return this;
    }

    /**
     * Configures and adds the "Recipe Search" use case to the application.
     * This method initializes the components necessary for searching recipes,
     * including the presenter, interactor, and controller. It assigns the controller
     * to the recipe search view, enabling users to search and filter recipes based on
     * specific criteria.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addRecipeSearchUseCase() {
        final RecipeSearchOutputBoundary recipeSearchOutputBoundary = new RecipeSearchPresenter(
                viewManagerModel, chooseRecipeViewModel, favoriteRecipeViewModel, editViewModel, recipeSearchViewModel);
        recipeSearchInteractor =
                new RecipeSearchInteractor(recipeDataAccessObject, recipeSearchOutputBoundary, userDataAccessObject);
        final RecipeSearchController recipeSearchController = new RecipeSearchController(recipeSearchInteractor);
        recipeSearchView.setRecipeSearchController(recipeSearchController);
        return this;
    }

    /**
     * Configures and adds the "Choose Recipe" use case to the application.
     * This method initializes the components necessary for handling recipe selection,
     * including the presenter, interactor, and controller. It assigns the controller
     * to the choose recipe and favorite recipe views, enabling users to select and view
     * recipes from these interfaces.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
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

    /**
     * Configures and adds the "Favorite Recipe" use case to the application.
     * This method initializes the components required for managing favorite recipes,
     * including the presenter, interactor, and controller. It assigns the controller
     * to the favorite recipe and display recipe views, enabling users to favorite
     * recipes and interact with their list of favorites.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addFavoriteRecipeUseCase() {
        final FavoriteRecipeOutputBoundary favoriteRecipeOutputBoundary = new FavoriteRecipePresenter(
                viewManagerModel, favoriteRecipeViewModel, shoppingListViewModel);
        final FavoriteRecipeInputBoundary favoriteRecipeInteractor = new FavoriteRecipeInteractor(
                favoriteRecipeOutputBoundary, userDataAccessObject, userDataAccessObject);
        final FavoriteRecipeController favoriteRecipeController =
                new FavoriteRecipeController(favoriteRecipeInteractor);
        favoriteRecipeView.setFavoriteRecipeController(favoriteRecipeController);
        displayRecipeView.setFavoriteRecipeController(favoriteRecipeController);
        return this;
    }

    /**
     * Configures and adds the "Shopping List" use case to the application.
     * This method sets up the components needed to handle the shopping list functionality,
     * including the presenter, interactor, and controller. It connects the controller
     * to the shopping list view, enabling users to manage and interact with their shopping list.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addShoppingListUseCase() {
        final ShoppingListOutputBoundary shoppingListPresenter = new ShoppingListPresenter(viewManagerModel,
                shoppingListViewModel);
        final ShoppingListInputBoundary shoppingListInteractor = new ShoppingListInteractor(shoppingListPresenter,
                recipeDataAccessObject);
        final ShoppingListController shoppingListController = new ShoppingListController(shoppingListInteractor);
        shoppingListView.setShoppingListController(shoppingListController);
        return this;
    }

    /**
     * Configures and adds the "Edit Recipe" use case to the application.
     * This method initializes the components required for editing recipes, including
     * the presenter, interactor, and controller. It connects the controller to the
     * edit view, enabling users to modify existing recipes.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addEditUseCase() {
        final EditOutputBoundary editOutputBoundary =
                new EditPresenter(viewManagerModel, createViewModel, editViewModel);
        final EditInputBoundary editInteractor = new EditInteractor(editOutputBoundary);
        final EditController editController = new EditController(editInteractor);
        editView.setEditController(editController);
        return this;
    }

    /**
     * Configures and adds the "Create Recipe" use case to the application.
     * This method initializes the components necessary for creating new recipes,
     * including the presenter, interactor, and controller. It assigns the controller
     * to the create view, enabling users to add new recipes to the application.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addCreateUseCase() {
        final CreateOutputBoundary createOutputBoundary =
                new CreatePresenter(viewManagerModel, recipeSearchViewModel, createViewModel);
        final CreateInputBoundary createInteractor =
                new CreateInteractor(createOutputBoundary, recipeFactory, recipeDataAccessObject);
        final CreateController createController = new CreateController(createInteractor);
        createView.setCreateController(createController);
        return this;
    }

    /**
     * Configures and adds the "Like Recipe" use case to the application.
     * This method sets up the components required to handle the "Like Recipe" functionality,
     * including the presenter, interactor, and controller. It also connects the controller
     * to the view to enable user interactions with the feature.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addLikeRecipeUseCase() {
        final LikeRecipeOutputBoundary likeRecipeOutputBoundary = new LikeRecipePresenter(displayRecipeViewModel);
        final LikeAndDislikeRecipeInputBoundary likeRecipeInteractor = new LikeRecipeInteractor(
                recipeDataAccessObject, userDataAccessObject, likeRecipeOutputBoundary);
        final LikeRecipeController likeRecipeController = new LikeRecipeController(likeRecipeInteractor);
        displayRecipeView.setLikeRecipeController(likeRecipeController);
        return this;
    }

    /**
     * Configures and adds the "Dislike Recipe" use case to the application.
     * This method initializes the necessary components, including the presenter,
     * interactor, and controller, for handling the "Dislike Recipe" functionality.
     * It also sets the controller in the view to enable interaction with the feature.
     *
     * @return the current {@link AppBuilder} instance, allowing for method chaining
     */
    public AppBuilder addDislikeRecipeUseCase() {
        final DislikeRecipeOutputBoundary dislikeRecipeOutputBoundary =
                new DislikeRecipePresenter(displayRecipeViewModel);
        final LikeAndDislikeRecipeInputBoundary dislikeRecipeInteractor = new DislikeRecipeInteractor(
                recipeDataAccessObject, userDataAccessObject, dislikeRecipeOutputBoundary);
        final DislikeRecipeController dislikeRecipeController = new DislikeRecipeController(dislikeRecipeInteractor);
        displayRecipeView.setDislikeRecipeController(dislikeRecipeController);
        return this;
    }

    /**
     * Constructs and initializes the main application window for the Mealmaster application.
     * This method sets up the main JFrame, initializes shared resources, and configures
     * the initial view state for the application.
     *
     * @return a {@link JFrame} object representing the main application window
     */
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



