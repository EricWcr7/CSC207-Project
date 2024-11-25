package use_case.delete_recipe;

public class DeleteOutputData {
    private final boolean success;
    private final String message;

    public DeleteOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

