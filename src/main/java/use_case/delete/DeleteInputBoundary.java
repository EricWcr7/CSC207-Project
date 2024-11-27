package use_case.delete;

public interface DeleteInputBoundary {
    /**
     * 执行删除菜谱的用例逻辑。
     * @param inputData 封装的删除输入数据
     */
    void execute(DeleteInputData inputData);
}

