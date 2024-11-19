package interface_adapter.create;

import use_case.create.CreateInputBoundary;

public class CreateController {

    private final CreateInputBoundary createInteractor;

    public CreateController(CreateInputBoundary createInteractor) {
        this.createInteractor = createInteractor;
    }

}
