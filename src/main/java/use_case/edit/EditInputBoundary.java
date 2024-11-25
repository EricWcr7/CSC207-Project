package use_case.edit;

public interface EditInputBoundary {

    void switchToCreateView();
    // from P4 to P6
    void execute(EditInputData editInputData);
    // from P4 to P9
}