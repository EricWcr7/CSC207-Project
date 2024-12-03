import data_access.RecipeDataAccessObject;
import entity.Recipe;
import org.junit.jupiter.api.Test;
import use_case.delete.*;

import java.util.ArrayList;
import java.util.List;

class DeleteInteractorTest {

    /**
     * A local implementation of DeleteDataAccessInterface for testing purposes.
     */
    private static class LocalDeleteDataAccess implements DeleteDataAccessInterface {
        @Override
        public void deleteFileFromFileIo() {

        }

        @Override
        public void uploadFileToFileIo() {

        }

        @Override
        public void writeRecipesToFile(List<Recipe> updatedRecipes) {

        }

        @Override
        public List<Recipe> getCachedRecipes() {
            return null;
        }

        @Override
        public void loadRecipesFromCloud() {

        }

        @Override
        public void removeRecipeByName(String recipeName) {

        }

        @Override
        public String findFileOnFileIo(String fileName) {
            return null;
        }
    }

    /**
     * A local implementation of DeleteUserDataAccessInterface for testing purposes.
     */
    private static class LocalDeleteUserDataAccess implements DeleteUserDataAccessInterface {
        private final List<String> userRecipes = new ArrayList<>();

        @Override
        public void deleteRecipeForUser(String recipeName) {
            userRecipes.remove(recipeName);
        }
    }

    /**
     * A local implementation of RecipeDataAccessObject for testing purposes.
     */
    private static class LocalRecipeDataAccessObject extends RecipeDataAccessObject {
        private final List<Recipe> recipes = new ArrayList<>();

        @Override
        public void loadRecipesFromCloud() {

        }

        @Override
        public boolean isNameInRecipes(String recipeName) {
            return recipes.stream().anyMatch(recipe -> recipe.getName().equals(recipeName));
        }

        @Override
        public void removeRecipeByName(String recipeName) {
            recipes.removeIf(recipe -> recipe.getName().equals(recipeName));
        }

        @Override
        public List<Recipe> getCachedRecipes() {
            return new ArrayList<>(recipes);
        }

        @Override
        public void writeRecipesToFile(List<Recipe> updatedRecipes) {

        }

        @Override
        public void deleteFileFromFileIo() {

        }

        @Override
        public void uploadFileToFileIo() {

        }

    }

    @Test
    void simpleDeleteTest() {

        LocalDeleteDataAccess deleteDataAccess = new LocalDeleteDataAccess();
        LocalDeleteUserDataAccess deleteUserDataAccess = new LocalDeleteUserDataAccess();
        LocalRecipeDataAccessObject recipeDataAccessObject = new LocalRecipeDataAccessObject();


        DeleteOutputBoundary deletePresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }

            @Override
            public void prepareFailureView() {

            }
        };


        DeleteInteractor interactor = new DeleteInteractor(
                deleteDataAccess,
                deleteUserDataAccess,
                deletePresenter,
                recipeDataAccessObject
        );


        DeleteInputData inputData = new DeleteInputData("TestRecipe");


        interactor.execute(inputData);
    }

    @Test
    void deleteUserRecipeFailureTest() {

        LocalDeleteDataAccess deleteDataAccess = new LocalDeleteDataAccess();
        LocalRecipeDataAccessObject recipeDataAccessObject = new LocalRecipeDataAccessObject();

        DeleteUserDataAccessInterface deleteUserDataAccess = new DeleteUserDataAccessInterface() {
            @Override
            public void deleteRecipeForUser(String recipeName) {
                throw new RuntimeException("Simulated deletion failure.");
            }
        };


        DeleteOutputBoundary deletePresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }

            @Override
            public void prepareFailureView() {


            }
        };
    }

    @Test
    void simpleDeleteUserRecipeTest() {
        // 创建本地实现的依赖对象
        LocalDeleteDataAccess deleteDataAccess = new LocalDeleteDataAccess();
        LocalDeleteUserDataAccess deleteUserDataAccess = new LocalDeleteUserDataAccess();
        LocalRecipeDataAccessObject recipeDataAccessObject = new LocalRecipeDataAccessObject();
        DeleteOutputBoundary deletePresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView() {

            }

            @Override
            public void prepareFailureView() {

            }
        };


        DeleteInteractor interactor = new DeleteInteractor(
                deleteDataAccess,
                deleteUserDataAccess,
                deletePresenter,
                recipeDataAccessObject
        );


        DeleteInputData inputData = new DeleteInputData("TestRecipe");


        interactor.deleteUserRecipe(inputData);
    }


}

