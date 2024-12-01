package use_case.back_to_edit_view;

/**
 * The BackToEditViewInteractor class serves as the interactor in the Clean Architecture design pattern.
 * It handles the business logic required to navigate back to the Edit Recipe View.
 */
public class BackToEditViewInteractor implements BackToEditViewInputBoundary {

    // An instance of the output boundary interface, responsible for presenting the result to the user.
    private BackToEditViewOutputBoundary backToEditViewPresenter;

    /**
     * Constructs a new BackToEditViewInteractor with the given output boundary.
     *
     * @param backToEditViewPresenter the output boundary that will handle the presentation logic
     *                                for navigating back to the Edit Recipe View.
     */
    public BackToEditViewInteractor(BackToEditViewOutputBoundary backToEditViewPresenter) {
        this.backToEditViewPresenter = backToEditViewPresenter;
    }

    /**
     * Executes the business logic for navigating back to the Edit Recipe View.
     * This method delegates the presentation logic to the output boundary (presenter).
     */
    @Override
    public void backToEditRecipeView() {
        // Delegate the responsibility of updating the UI or view to the presenter.
        backToEditViewPresenter.backToEditRecipeView();
    }
}
