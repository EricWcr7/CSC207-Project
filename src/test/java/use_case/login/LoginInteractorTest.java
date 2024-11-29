package use_case.login;


import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private static class LocalUserDataAccessObject implements LoginUserDataAccessInterface {

        private final List<User> users = new ArrayList<>();
        private String currentUsername = null;

        @Override
        public boolean existsByName(String username) {
            return users.stream().anyMatch(user -> user.getName().equals(username));
        }

        @Override
        public void save(User user) {
            users.add(user);
        }

        @Override
        public User get(String username) {
            return users.stream().filter(user -> user.getName().equals(username)).findFirst().orElse(null);
        }

        @Override
        public String getCurrentUsername() {
            return currentUsername; // Properly return the currentUsername
        }

        @Override
        public void setCurrentUsername(String username) {
            this.currentUsername = username; // Update currentUsername
        }

        @Override
        public String findFileOnFileIo(String fileName) {
            return null;
        }

        @Override
        public void loadUsersFromCloud() {
            // Simulated method for test cases
        }
    }


    @Test
    void successTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new LocalUserDataAccessObject();

        // Add "Paul" to the repository before testing login
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // Define success presenter
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
                assertNotNull(user.getFavoriteRecipes());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                fail("Switch to signup view is not expected in this test.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);

    }

    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new LocalUserDataAccessObject();

        // Add "Paul" to the repository before testing login
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // Define success presenter
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", userRepository.getCurrentUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToSignupView() {
                fail("Switch to signup view is not expected in this test.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);

        // Ensure no user is logged in before execution
        assertNull(userRepository.getCurrentUsername());

        interactor.execute(inputData);

        // Ensure user is logged in after execution
        assertEquals("Paul", userRepository.getCurrentUsername());
    }

    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("Paul", "wrong_password");
        LoginUserDataAccessInterface userRepository = new LocalUserDataAccessObject();

        // Add "Paul" to the repository before testing login
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        // Define failure presenter
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void switchToSignupView() {
                fail("Switch to signup view is not expected in this test.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserDoesNotExistTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new LocalUserDataAccessObject();

        // No user is added to the repository in this test

        // Define failure presenter
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Paul: Account does not exist.", error);
            }

            @Override
            public void switchToSignupView() {
                fail("Switch to signup view is not expected in this test.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void switchToSignupViewTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new LocalUserDataAccessObject();

        // Define presenter with expectation to switch to signup view
        LoginOutputBoundary signupPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is not expected in this test.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is not expected in this test.");
            }

            @Override
            public void switchToSignupView() {
                // This method is expected to be invoked in this test
                assertTrue(true, "Switch to signup view called successfully.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, signupPresenter);
        interactor.switchToSignupView();
    }

    @Test
    void initializeUserStorageTest() {
        // Mock data access object to simulate file interactions
        LoginUserDataAccessInterface userRepository = new LocalUserDataAccessObject() {
            private boolean cloudUsersLoaded = false;

            @Override
            public String findFileOnFileIo(String fileName) {
                if (fileName.equals("all_users.json")) {
                    // Simulate file exists
                    return "mockFileKey123";
                }
                return "";
            }

            @Override
            public void loadUsersFromCloud() {
                // Simulate loading users from the cloud
                cloudUsersLoaded = true;
            }

            public boolean isCloudUsersLoaded() {
                return cloudUsersLoaded;
            }
        };

        // Execute the method
        try {
            LoginInteractor interactor = new LoginInteractor(userRepository, null); // No presenter needed for this test
            interactor.initializeUserStorage();

        }
        catch (Exception e) {
            fail("Initialization should not throw any exception: " + e.getMessage());
        }
    }



}
