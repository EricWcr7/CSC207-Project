package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {
    public static final String SEARCH_LABEL = "Input what you want to search";

    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

}
