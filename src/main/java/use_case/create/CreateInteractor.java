package use_case.create;

import data_access.RecipeDataAccessObject;
import entity.CommonRecipe;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;

import java.util.List;

public class CreateInteractor implements CreateInputBoundary {
    private final CreateOutputBoundary createPresenter;
    private final RecipeDataAccessObject recipeDataAccessObject;
    private boolean recipesLoaded = false;
    private final RecipeFactory recipeFactory;

    public CreateInteractor(CreateOutputBoundary createPresenter, RecipeFactory recipeFactory) {
        this.createPresenter = createPresenter;
        this.recipeDataAccessObject = new RecipeDataAccessObject();
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
            final Recipe recipeCreated = recipeFactory.createRecipe(id, category, createInputData.getDishname(),
                    createInputData.getInstruction(), createInputData.getIngredient());
            final CommonRecipe recipeCreatedfix = (CommonRecipe) recipeCreated;
            recipeDataAccessObject.saveRecipe(recipeCreatedfix);
            final List<CommonRecipe> updatedRecipe = recipeDataAccessObject.getCachedRecipes();
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
