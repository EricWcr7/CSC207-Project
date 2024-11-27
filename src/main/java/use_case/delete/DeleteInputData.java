package use_case.delete;

public class DeleteInputData {
    private final String recipeName;

    // 构造方法，初始化菜谱名称
    public DeleteInputData(String recipeName) {
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }
        this.recipeName = recipeName;
    }

    // Getter 方法，用于访问菜谱名称
    public String getRecipeName() {
        return recipeName;
    }
}
