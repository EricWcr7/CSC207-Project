package use_case.edit;

import org.junit.jupiter.api.Test;

// Test class to achieve 100% coverage for EditInteractor
class EditInteractorTest {

    @Test
    void testEditInteractorCoverage() {
        // Define a dummy implementation of EditOutputBoundary
        EditOutputBoundary dummyPresenter = new EditOutputBoundary() {
            @Override
            public void showCreateView() {
                // Empty implementation to satisfy the interface
                System.out.println("Dummy presenter called.");
            }
        };

        // Create an instance of EditInteractor with the dummy presenter
        EditInteractor interactor = new EditInteractor(dummyPresenter);

        // Call the method to ensure it is covered
        interactor.switchToCreateView();
    }
}

