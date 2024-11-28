package use_case.create;

import java.util.List;

import entity.Recipe;
import entity.RecipeFactory;

/**
 * Interactor class for the Create Use Case.
 * Handles the business logic for creating a new recipe.
 */
public class CreateInteractor implements CreateInputBoundary {
    private final CreateOutputBoundary createPresenter;
    private final CreateDataAccessInterface recipeDataAccessObject;
    private boolean recipesLoaded;
    private final RecipeFactory recipeFactory;
    private final CreateUserDataAccessInterface userDataAccessObject;

    public CreateInteractor(CreateOutputBoundary createPresenter,
                            RecipeFactory recipeFactory,
                            CreateDataAccessInterface recipeDataAccessInterface,
                             CreateUserDataAccessInterface userDataAccessInterface) {
        this.createPresenter = createPresenter;
        this.recipeDataAccessObject = recipeDataAccessInterface;
        this.recipeFactory = recipeFactory;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(CreateInputData createInputData) {
        if (!recipesLoaded) {
            System.out.println("Loading recipes from cloud");
            recipeDataAccessObject.loadRecipesFromCloud();
            recipesLoaded = true;
        }
        // type need to be fixed later
        final String id = recipeDataAccessObject.getMaxId() + 1;
        final String category = "created by user";
        if (!recipeDataAccessObject.isNameInRecipes(createInputData.getDishname())) {
            final Recipe recipeCreated = recipeFactory.createRecipe(id, createInputData.getDishname(), category,
                    createInputData.getInstruction(), createInputData.getIngredient(), 0, 0);
            recipeDataAccessObject.saveRecipe(recipeCreated);
            userDataAccessObject.addCreatedRecipe(recipeCreated);
            final List<Recipe> updatedRecipe = recipeDataAccessObject.getCachedRecipes();
            recipeDataAccessObject.writeRecipesToFile(updatedRecipe);
            recipeDataAccessObject.deleteFileFromFileIo();
            recipeDataAccessObject.uploadFileToFileIo();
            createPresenter.prepareSuccessView();
        }
        else {
            createPresenter.prepareFailureView();
        }
    }

}
