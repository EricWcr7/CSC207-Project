package use_case.create;

import data_access.RecipeDataAccessObject;
import entity.CommonRecipe;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;

import java.util.List;

/**
 * The CreateInteractor class is responsible for handling the creation of recipes.
 * It implements the {@link CreateInputBoundary} interface, acting as the interactor
 * in the Clean Architecture structure. This class coordinates the interaction between
 * the presentation layer and the data access layer to create new recipes.
 */
public class CreateInteractor implements CreateInputBoundary {
    private final CreateOutputBoundary createPresenter;
    private final CreateDataAccessInterface recipeDataAccessObject;
    private boolean recipesLoaded;
    private final RecipeFactory recipeFactory;

    public CreateInteractor(CreateOutputBoundary createPresenter, RecipeFactory recipeFactory, CreateDataAccessInterface recipeDataAccessInterface) {
        this.createPresenter = createPresenter;
        this.recipeDataAccessObject = recipeDataAccessInterface;
        this.recipeFactory = recipeFactory;

    }

    /**
     * Executes the recipe creation workflow. This method performs the following steps:
     * <ul>
     *     <li>Loads recipes from the cloud if they have not been previously loaded.</li>
     *     <li>Checks if the recipe name already exists in the stored recipes.</li>
     *     <li>If the name is unique, creates a new recipe, saves it to the data storage,
     *     writes updated recipes to a file, and uploads the file.</li>
     *     <li>If the name is not unique, prepares a failure view through the presenter.</li>
     * </ul>
     *
     * @param createInputData the input data containing recipe details such as name, ingredients, and instructions
     */
    public void execute(CreateInputData createInputData) {
        if (!recipesLoaded) {
            System.out.println("Loading recipes from cloud");
            recipeDataAccessObject.loadRecipesFromCloud();
            recipesLoaded = true;
        }
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
