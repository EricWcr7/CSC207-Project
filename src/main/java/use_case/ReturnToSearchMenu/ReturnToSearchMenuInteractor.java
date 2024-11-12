package use_case.ReturnToSearchMenu;

public class ReturnToSearchMenuInteractor implements ReturnToSearchMenuInputBoundary{
    private ReturnToSearchMenuOutputBoundary returnToSearchMenuPresenter;

    public ReturnToSearchMenuInteractor(ReturnToSearchMenuOutputBoundary returnToSearchMenuPresenter) {
        this.returnToSearchMenuPresenter = returnToSearchMenuPresenter;
    }

    @Override
    public void execute() {
        returnToSearchMenuPresenter.prepareSuccessView();
    }
}