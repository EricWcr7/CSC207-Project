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
        final Integer width = 800;
        final Integer height = 600;
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
                .addDeleteUseCase()
                .build();
        application.pack();
        application.setSize(width, height);
        application.setVisible(true);
    }
}
