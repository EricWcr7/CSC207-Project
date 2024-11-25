package interface_adapter.edit;

import use_case.choose_recipe.ChooseRecipeInputData;
import use_case.edit.EditInputBoundary;
import use_case.edit.EditInputData;

public class EditController {
    private final EditInputBoundary editInteractor;

    public EditController(EditInputBoundary editInteractor) {
        this.editInteractor = editInteractor;
    }

    public void switchToCreate(){
        editInteractor.switchToCreateView();
    }

    public void execute(String searchDishName) {
        // Create the input data for the search operation
        final EditInputData editInputData = new EditInputData(searchDishName);

        // Perform the choose dish through the interactor
        editInteractor.execute(editInputData);

        System.out.println("Select with dish name: " + searchDishName);
    }
}
