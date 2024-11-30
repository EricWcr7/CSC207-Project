package use_case.delete;

import data_access.RecipeDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteInteractorTest {

    private DeleteOutputBoundary mockPresenter;
    private RecipeDataAccessObject mockRecipeDAO;
    private DUserDataAccessInterface mockUserDAO;
    private DeleteInteractor deleteInteractor;

    @BeforeEach
    public void setUp() {
        mockPresenter = Mockito.mock(DeleteOutputBoundary.class);
        mockRecipeDAO = Mockito.mock(RecipeDataAccessObject.class);
        mockUserDAO = Mockito.mock(DUserDataAccessInterface.class);
        deleteInteractor = new DeleteInteractor(mockPresenter, mockRecipeDAO, mockUserDAO);
    }

    @Test
    public void testDeleteRecipeSuccess() {
        // 设置 Mock 行为
        when(mockUserDAO.removeUserCreatedRecipe("testUser", "testRecipe")).thenReturn(true);
        when(mockRecipeDAO.removeRecipeFromLocalFile("new_recipes.json", "testRecipe")).thenReturn(true);
        when(mockRecipeDAO.isNameInRecipes("testRecipe")).thenReturn(true);

        // 执行测试
        DeleteInputData inputData = new DeleteInputData("testUser", "testRecipe");
        deleteInteractor.execute(inputData);

        // 验证行为
        verify(mockUserDAO).removeUserCreatedRecipe("testUser", "testRecipe");
        verify(mockRecipeDAO).removeRecipeFromLocalFile("new_recipes.json", "testRecipe");
        verify(mockRecipeDAO).writeRecipesToFile(anyList());
        verify(mockRecipeDAO).deleteFileFromFileIo();
        verify(mockRecipeDAO).uploadFileToFileIo();
        verify(mockPresenter).prepareSuccessView();
    }

    @Test
    public void testDeleteUserRecipeFailure() {
        // 设置 Mock 行为
        when(mockUserDAO.removeUserCreatedRecipe("testUser", "testRecipe")).thenReturn(false);

        // 执行测试
        DeleteInputData inputData = new DeleteInputData("testUser", "testRecipe");
        deleteInteractor.execute(inputData);

        // 验证行为
        verify(mockUserDAO).removeUserCreatedRecipe("testUser", "testRecipe");
        verify(mockPresenter).prepareFailureView();
        verifyNoInteractions(mockRecipeDAO);
    }
}
