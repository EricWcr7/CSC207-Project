package use_case.create_recipe;

import java.util.List;

import entity.Recipe;
import entity.RecipeFactory;

/**
 * Interactor class for the Create Use Case.
 * Handles the business logic for creating a new recipe.
 */
public class CreateRecipeInteractor implements CreateRecipeInputBoundary {
    private final CreateRecipeOutputBoundary createPresenter;
    private final CreateRecipeDataAccessInterface recipeDataAccessObject;
    private boolean recipesLoaded;
    private final RecipeFactory recipeFactory;
    private final CreateRecipeUserDataAccessInterface userDataAccessObject;

    public CreateRecipeInteractor(CreateRecipeOutputBoundary createPresenter,
                                  RecipeFactory recipeFactory,
                                  CreateRecipeDataAccessInterface recipeDataAccessInterface,
                                  CreateRecipeUserDataAccessInterface userDataAccessInterface) {
        this.createPresenter = createPresenter;
        this.recipeDataAccessObject = recipeDataAccessInterface;
        this.recipeFactory = recipeFactory;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(CreateRecipeInputData createRecipeInputData) {
        if (!recipesLoaded) {
            System.out.println("Loading recipes from cloud");
            recipeDataAccessObject.loadRecipesFromCloud();
            recipesLoaded = true;
        }
        // type need to be fixed later
        final String id = recipeDataAccessObject.getMaxId() + 1;
        final String category = "created by user";
        if (!recipeDataAccessObject.isNameInRecipes(createRecipeInputData.getDishname())) {
            final Recipe recipeCreated = recipeFactory.createRecipe(id, createRecipeInputData.getDishname(), category,
                    createRecipeInputData.getInstruction(), createRecipeInputData.getIngredient(), 0, 0);
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
