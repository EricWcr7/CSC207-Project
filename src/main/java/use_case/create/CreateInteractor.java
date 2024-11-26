package use_case.create;

import data_access.RecipeDataAccessObject;
import entity.CommonRecipe;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;

import java.util.List;

public class CreateInteractor implements CreateInputBoundary {
    private final CreateOutputBoundary createPresenter;
    private final CreateDataAccessInterface recipeDataAccessObject;
    private boolean recipesLoaded = false;
    private final RecipeFactory recipeFactory;

    public CreateInteractor(CreateOutputBoundary createPresenter, RecipeFactory recipeFactory, CreateDataAccessInterface recipeDataAccessInterface) {
        this.createPresenter = createPresenter;
        this.recipeDataAccessObject = recipeDataAccessInterface;
        this.recipeFactory = recipeFactory;
    }

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
                    createInputData.getInstruction(), createInputData.getIngredient(),0,0);
            recipeDataAccessObject.saveRecipe(recipeCreated);
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
