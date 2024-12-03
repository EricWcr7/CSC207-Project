package use_case.return_to_recipe_search_view;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.favorite_receipe.FavoriteRecipeDataAccessInterface;
import use_case.recipe_search.RecipeSearchInteractor;
import use_case.shopping_list.ShoppingListUserDataAccessInterface;

public class ReturnToSearchMenuInteractorTest {

    private static class LocalRecipeDataAccessObject implements FavoriteRecipeDataAccessInterface {

        public String getUsername() {
            return "test";
        }

        public String[] getFavoriteRecipes() {
            return new String[] {"test"};
        }

        public void setUsername(String username) {
        }

        public void setFavoriteRecipes(String[] favoriteRecipes) {

        }

        public void updateUserFavoriteRecipes(User user) {

        }

        public User get(String username) {
            User user = new CommonUser("test", "test");
            return user;
        }
    }

    private static class LocalShoppingListDataAccrssObject implements ShoppingListUserDataAccessInterface{

        public String getCurrentUsername() {
            return "test";
        }

        public User get(String username) {
            User user = new CommonUser(username, "test");
            return user;
        }

    }


    @Test
    void testfromDisplayBackToSearchMenu() {
        ReturnToSearchMenuOutputBoundary dummyPresenter = new ReturnToSearchMenuOutputBoundary() {
            @Override
            public void fromEditRecipeBackToSearchMenu() {

            }

            @Override
            public void fromFavoriteRecipeBackToSearchMenu() {

            }

            @Override
            public void fromShoppingListBackToSearchMenu() {

            }

            @Override
            public void fromDisplayBackToSearchMenu() {

            }

            @Override
            public void fromChooseBackToSearchMenu() {

            }
        };

    LocalRecipeDataAccessObject localRecipeDataAccessObject = new LocalRecipeDataAccessObject();
    LocalShoppingListDataAccrssObject localShoppingListDataAccrssObject = new LocalShoppingListDataAccrssObject();
    ReturnToSearchMenuInteractor returnToSearchMenuInteractor = new ReturnToSearchMenuInteractor(
            dummyPresenter,localRecipeDataAccessObject,localShoppingListDataAccrssObject

    );

    returnToSearchMenuInteractor.fromDisplayBackToSearchMenu();



}

    @Test
    void testfromChooseBackToSearchMenu() {
        ReturnToSearchMenuOutputBoundary dummyPresenter = new ReturnToSearchMenuOutputBoundary() {
            @Override
            public void fromEditRecipeBackToSearchMenu() {

            }

            @Override
            public void fromFavoriteRecipeBackToSearchMenu() {

            }

            @Override
            public void fromShoppingListBackToSearchMenu() {

            }

            @Override
            public void fromDisplayBackToSearchMenu() {

            }

            @Override
            public void fromChooseBackToSearchMenu() {

            }
        };

        LocalRecipeDataAccessObject localRecipeDataAccessObject = new LocalRecipeDataAccessObject();
        LocalShoppingListDataAccrssObject localShoppingListDataAccrssObject = new LocalShoppingListDataAccrssObject();
        ReturnToSearchMenuInteractor returnToSearchMenuInteractor = new ReturnToSearchMenuInteractor(
                dummyPresenter,localRecipeDataAccessObject,localShoppingListDataAccrssObject

        );

        returnToSearchMenuInteractor.fromChooseBackToSearchMenu();
}
    @Test
    void testfromEditRecipeBackToSearchMenu() {
        ReturnToSearchMenuOutputBoundary dummyPresenter = new ReturnToSearchMenuOutputBoundary() {
            @Override
            public void fromEditRecipeBackToSearchMenu() {

            }

            @Override
            public void fromFavoriteRecipeBackToSearchMenu() {

            }

            @Override
            public void fromShoppingListBackToSearchMenu() {

            }

            @Override
            public void fromDisplayBackToSearchMenu() {

            }

            @Override
            public void fromChooseBackToSearchMenu() {

            }
        };

        LocalRecipeDataAccessObject localRecipeDataAccessObject = new LocalRecipeDataAccessObject();
        LocalShoppingListDataAccrssObject localShoppingListDataAccrssObject = new LocalShoppingListDataAccrssObject();
        ReturnToSearchMenuInteractor returnToSearchMenuInteractor = new ReturnToSearchMenuInteractor(
                dummyPresenter, localRecipeDataAccessObject, localShoppingListDataAccrssObject

        );

        returnToSearchMenuInteractor.fromEditRecipeBackToSearchMenu();
    }

    @Test
    void testfromFavoriteRecipeBackToSearchMenu() {
        ReturnToSearchMenuOutputBoundary dummyPresenter = new ReturnToSearchMenuOutputBoundary() {
            @Override
            public void fromEditRecipeBackToSearchMenu() {

            }

            @Override
            public void fromFavoriteRecipeBackToSearchMenu() {

            }

            @Override
            public void fromShoppingListBackToSearchMenu() {

            }

            @Override
            public void fromDisplayBackToSearchMenu() {

            }

            @Override
            public void fromChooseBackToSearchMenu() {

            }
        };

        LocalRecipeDataAccessObject localRecipeDataAccessObject = new LocalRecipeDataAccessObject();
        LocalShoppingListDataAccrssObject localShoppingListDataAccrssObject = new LocalShoppingListDataAccrssObject();
        ReturnToSearchMenuInteractor returnToSearchMenuInteractor = new ReturnToSearchMenuInteractor(
                dummyPresenter, localRecipeDataAccessObject, localShoppingListDataAccrssObject

        );

        returnToSearchMenuInteractor.fromFavoriteRecipeBackToSearchMenu();
    }

    @Test
    void testfromShoppingListBackToSearchMenu() {
        ReturnToSearchMenuOutputBoundary dummyPresenter = new ReturnToSearchMenuOutputBoundary() {
            @Override
            public void fromEditRecipeBackToSearchMenu() {

            }

            @Override
            public void fromFavoriteRecipeBackToSearchMenu() {

            }

            @Override
            public void fromShoppingListBackToSearchMenu() {

            }

            @Override
            public void fromDisplayBackToSearchMenu() {

            }

            @Override
            public void fromChooseBackToSearchMenu() {

            }
        };

        LocalRecipeDataAccessObject localRecipeDataAccessObject = new LocalRecipeDataAccessObject();
        LocalShoppingListDataAccrssObject localShoppingListDataAccrssObject = new LocalShoppingListDataAccrssObject();
        ReturnToSearchMenuInteractor returnToSearchMenuInteractor = new ReturnToSearchMenuInteractor(
                dummyPresenter, localRecipeDataAccessObject, localShoppingListDataAccrssObject

        );

        returnToSearchMenuInteractor.fromShoppingListBackToSearchMenu();
    }







}
