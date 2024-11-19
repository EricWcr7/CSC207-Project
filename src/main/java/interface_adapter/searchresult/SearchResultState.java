package interface_adapter.searchresult;

public class SearchResultState {
    private String searchKeyword = "";

    public void setSearchKeyWord(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
}