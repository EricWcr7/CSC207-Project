package use_case.choose_recipe;

/**
 * Represents the input data for the "choose recipe" use case.
 * This class encapsulates the data required to process a "choose recipe" request, including the name of the dish
 * the user is searching for, the username of the currently logged-in user, and the user's favorite recipes.
 * It serves as the data transfer object between the controller and the interactor.
 */
public class ChooseRecipeInputData {
    private final String dishName;

    public ChooseRecipeInputData(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Gets the name of the dish to search for.
     *
     * @return the name of the dish
     */
    public String getDishName() {
        return dishName;
    }

}
