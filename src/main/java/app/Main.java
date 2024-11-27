package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final int width = 800;
        final int height = 600;
        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addRecipeSearchView()
                .addChooseRecipeView()
                .addDisplayRecipeView()
                .addFavoriteRecipeView()
                .addEditView()
                .addCreateView()
                .addShoppingListView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .addRecipeSearchUseCase()
                .addChooseRecipeUseCase()
                .addReturnToSearchMenuUseCase()
                .addBackToEditViewUsecase()
                .addFavoriteRecipeUseCase()
                .addEditUseCase()
                .addCreateUseCase()
                .addLikeRecipeUseCase()
                .addDislikeRecipeUseCase()
                .addShoppingListUseCase()
                .build();
        application.pack();
        application.setSize(width, height);
        application.setVisible(true);
    }
}
