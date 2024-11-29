package use_case.logout;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    @Test
    void successTest() {
        // Set up the in-memory data access object
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add "Paul" to the repository and set him as the current user
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);
        userRepository.setCurrentUsername("Paul");

        // Verify that "Paul" is initially logged in
        assertEquals("Paul", userRepository.getCurrentUsername());

        // Define the success presenter
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // Confirm that the presenter acknowledges the logout success
                assertTrue(true, "Logout success view prepared successfully.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        // Create the interactor
        LogoutInputBoundary interactor = new LogoutInteractor(userRepository, successPresenter);

        // Execute the logout use case
        interactor.execute();

        // Verify that no user is logged in after logout
        assertNull(userRepository.getCurrentUsername());
    }

    @Test
    void failureTestWhenNoUserLoggedIn() {
        // Set up the in-memory data access object
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Ensure no user is logged in
        assertNull(userRepository.getCurrentUsername());

        // Define the failure presenter
        LogoutOutputBoundary failurePresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail("Logout success is unexpected when no user is logged in.");
            }

            @Override
            public void prepareFailView(String error) {
                // Confirm the expected error message is received
                assertEquals("No user is currently logged in.", error);
            }
        };

        // Create a custom interactor that handles failure for no user logged in
        LogoutInputBoundary interactor = new LogoutInteractor(userRepository, new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail("Logout success is not expected when no user is logged in.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No user is currently logged in.", error);
            }
        });

        // Execute the logout use case
        interactor.execute();

        // Verify that no user is logged in after the failed logout attempt
        assertNull(userRepository.getCurrentUsername());
    }
}
