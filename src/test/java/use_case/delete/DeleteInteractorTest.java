import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.delete.DeleteDataAccessInterface;
import use_case.delete.DeleteInputData;
import use_case.delete.DeleteInteractor;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteUserDataAccessInterface;

import static org.mockito.Mockito.*;

class DeleteInteractorTest {

    private DeleteDataAccessInterface mockDeleteDataAccess;
    private DeleteUserDataAccessInterface mockDeleteUserDataAccess;
    private DeleteOutputBoundary mockDeleteOutputBoundary;
    private DeleteInteractor deleteInteractor;

    @BeforeEach
    void setUp() {
        // Use Mockito to create mock objects
        mockDeleteDataAccess = Mockito.mock(DeleteDataAccessInterface.class);
        mockDeleteUserDataAccess = Mockito.mock(DeleteUserDataAccessInterface.class);
        mockDeleteOutputBoundary = Mockito.mock(DeleteOutputBoundary.class);

        // Create DeleteInteractor instance
        deleteInteractor = new DeleteInteractor(mockDeleteDataAccess, mockDeleteUserDataAccess, mockDeleteOutputBoundary);
    }

    @Test
    void testDeleteRecipe_Success() {
        // Prepare test data
        DeleteInputData inputData = new DeleteInputData("user", "recipe1");

        // Call deleteRecipe method
        deleteInteractor.deleteRecipe(inputData);

        // Verify that deleteRecipeFromAllRecipes was called once
        verify(mockDeleteDataAccess, times(1)).deleteRecipeFromAllRecipes(inputData.getRecipeName());

        // Verify that prepareSuccessView was called
        verify(mockDeleteOutputBoundary, times(1)).prepareSuccessView();

        // Verify that prepareFailureView was never called
        verify(mockDeleteOutputBoundary, never()).prepareFailureView();
    }

    @Test
    void testDeleteRecipe_Failure() {
        // Prepare test data
        DeleteInputData inputData = new DeleteInputData("user", "recipe1");

        // Simulate deleteRecipeFromAllRecipes throwing an exception
        doThrow(new RuntimeException("Delete failed")).when(mockDeleteDataAccess).deleteRecipeFromAllRecipes(inputData.getRecipeName());

        // Call deleteRecipe method
        deleteInteractor.deleteRecipe(inputData);

        // Verify that prepareFailureView was called
        verify(mockDeleteOutputBoundary, times(1)).prepareFailureView();

        // Verify that prepareSuccessView was never called
        verify(mockDeleteOutputBoundary, never()).prepareSuccessView();
    }

    @Test
    void testDeleteUserRecipe_Success() {
        // Prepare test data
        DeleteInputData inputData = new DeleteInputData("user", "recipe1");

        // Use Mockito to mock session
        Session mockSession = mock(Session.class);
        when(mockSession.getCurrentUser()).thenReturn(new User("user"));

        // Call deleteUserRecipe method
        deleteInteractor.deleteUserRecipe(inputData);

        // Verify that deleteRecipeForUser was called once
        verify(mockDeleteUserDataAccess, times(1)).deleteRecipeForUser("user", inputData.getRecipeName());

        // Verify that prepareSuccessView was called
        verify(mockDeleteOutputBoundary, times(1)).prepareSuccessView();

        // Verify that prepareFailureView was never called
        verify(mockDeleteOutputBoundary, never()).prepareFailureView();
    }

    @Test
    void testDeleteUserRecipe_Failure() {
        // Prepare test data
        DeleteInputData inputData = new DeleteInputData("user", "recipe1");

        // Simulate deleteRecipeForUser throwing an exception
        doThrow(new RuntimeException("Delete failed")).when(mockDeleteUserDataAccess).deleteRecipeForUser("user", inputData.getRecipeName());

        // Call deleteUserRecipe method
        deleteInteractor.deleteUserRecipe(inputData);

        // Verify that prepareFailureView was called
        verify(mockDeleteOutputBoundary, times(1)).prepareFailureView();

        // Verify that prepareSuccessView was never called
        verify(mockDeleteOutputBoundary, never()).prepareSuccessView();
    }
}
