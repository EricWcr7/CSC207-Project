package use_case.like_and_dislike_a_recipe;

public interface UserLikeAndDislikeDataAccessInterface {

    public String getCurrentUsername();

    boolean hasUserLikedRecipe(String recipeName);

    void addLikedRecipe(String recipeName);

    boolean hasUserDislikedRecipe(String recipeName);

    void addDislikedRecipe(String recipeName);
}
