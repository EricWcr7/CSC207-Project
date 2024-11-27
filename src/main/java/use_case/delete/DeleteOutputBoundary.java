package use_case.delete;

/**
 * OutputBoundary 接口，用于定义删除用例的反馈逻辑。
 */
public interface DeleteOutputBoundary {
    /**
     * 删除操作成功时调用，准备成功视图。
     */
    void prepareSuccessView();

    /**
     * 删除操作失败时调用，准备失败视图。
     */
    void prepareFailureView();
}

