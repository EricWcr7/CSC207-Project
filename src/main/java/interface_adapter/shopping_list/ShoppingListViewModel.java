package interface_adapter.shopping_list;

import interface_adapter.ViewModel;
import java.util.List;
import java.util.Map;

public class ShoppingListViewModel extends ViewModel<ShoppingListState> {

    public ShoppingListViewModel() {
        super("The Overall Shopping List");
        setState(new ShoppingListState());
    }
}
