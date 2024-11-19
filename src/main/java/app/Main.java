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
        // TODO: add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addRecipeSearchView()
                                            .addChooseRecipeView()
                                            .addDisplayRecipeView()
                                            .addFavoriteRecipeView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addRecipeSearchUseCase()
                                            .addChooseRecipeUseCase()
                                            .addReturnToSearchMenuUseCase()
                                            .addFavoriteRecipeUseCase()
                                            .build();
        application.pack();
        application.setSize(width, height);
        application.setVisible(true);
    }
}
