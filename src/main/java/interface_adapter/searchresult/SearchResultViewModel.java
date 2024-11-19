package interface_adapter.searchresult;

import interface_adapter.ViewModel;

public class SearchResultViewModel extends ViewModel<SearchResultState> {
    public SearchResultViewModel() {
        super("search result");
        setState(new SearchResultState());
    }
}