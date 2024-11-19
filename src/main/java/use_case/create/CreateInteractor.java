package use_case.create;

public class CreateInteractor implements CreateInputBoundary {
    private final CreateOutputBoundary createPresenter;

    public CreateInteractor(CreateOutputBoundary createPresenter) {
        this.createPresenter = createPresenter;
    }


}
