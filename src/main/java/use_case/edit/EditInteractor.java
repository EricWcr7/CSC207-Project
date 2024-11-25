package use_case.edit;

import data_access.RecipeDataAccessObject;
import entity.Recipe;
import use_case.choose_recipe.ChooseRecipeInputData;
import use_case.choose_recipe.ChooseRecipeOutputData;

public class EditInteractor implements EditInputBoundary {
    private final EditOutputBoundary editPresenter;
    private final EditDataAccessInterface recipeDataAccessObject;

    public EditInteractor(EditDataAccessInterface recipeDataAccessObject, EditOutputBoundary editPresenter) {
        this.editPresenter = editPresenter;
        this.recipeDataAccessObject = recipeDataAccessObject;
    }

    @Override
    public void switchToCreateView() {
        editPresenter.showCreateView();
    }

    @Override
    public void execute(EditInputData editInputData) {
        final String searchDishName = editInputData.getDishName();

        final Recipe recipe = recipeDataAccessObject.getOneRecipe(searchDishName);

        final EditOutputData editOutputData = new EditOutputData(recipe.getName(), recipe.getIngredients(), recipe.getInstructions());
        editPresenter.prepareSuccessView(editOutputData);
    }
}