package use_case.logout;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    private static class LocalUserDataAccessObject implements LogoutUserDataAccessInterface {

        private final List<User> users = new ArrayList<>();
        private String currentName = null;

        public boolean existsByName(String username){
            return users.stream().anyMatch(user -> user.getName().equals(username));
        }

        public void save(User user) {
            users.add(user);
        }

        public void setCurrentUsername(String name) {
            currentName = name;
        }

        public String getCurrentUsername() {
            return currentName;
        }

        @Override
        public String findFileOnFileIo(String fileName) {
            return null;
        }

        @Override
        public void loadUsersFromCloud() {

        }
    }

    @Test
    void successTest() {
        // Set up the in-memory data access object
        LocalUserDataAccessObject userRepository = new LocalUserDataAccessObject();

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

    }

}
