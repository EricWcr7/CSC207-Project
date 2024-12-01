package interface_adapter.create_recipe;

import interface_adapter.ViewModel;

/**
 * The CreateViewModel class extends the {@link ViewModel} and manages the state
 * for the "Create Recipe" view in the application. It initializes the view model
 * with a default state and provides constants for the UI labels and dimensions.
 */
public class CreateRecipeViewModel extends ViewModel<CreateRecipeState> {

    public static final int NAME_FIELD_WIDTH = 300;
    public static final int NAME_FIELD_HEIGHT = 25;
    public static final int INSTRUCTIONS_AREA_ROWS = 3;
    public static final int INSTRUCTIONS_AREA_COLUMNS = 20;
    public static final int INGREDIENT_SCROLL_WIDTH = 400;
    public static final int INGREDIENT_SCROLL_HEIGHT = 150;
    public static final int FONT_SIZE = 20;
    public static final int BUTTON_GAP = 10;

    public CreateRecipeViewModel() {
        super("Create recipe");
        setState(new CreateRecipeState());
    }
}
