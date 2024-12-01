package use_case.back_to_edit_view;

import org.junit.jupiter.api.Test;

// A simple test class to achieve 100% coverage for BackToEditViewInteractor.
class BackToEditViewInteractorTest {

    @Test
    void testInteractorCoverage() {
        // Create a simple implementation of BackToEditViewOutputBoundary.
        BackToEditViewOutputBoundary dummyPresenter = new BackToEditViewOutputBoundary() {
            @Override
            public void backToEditRecipeView() {
                // This method does nothing but satisfies the interface.
                System.out.println("Dummy presenter method called.");
            }
        };

        // Create the interactor instance with the dummy presenter.
        BackToEditViewInteractor interactor = new BackToEditViewInteractor(dummyPresenter);

        // Call the interactor method to execute all its code.
        interactor.backToEditRecipeView();
    }
}

