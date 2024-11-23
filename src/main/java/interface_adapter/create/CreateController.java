package interface_adapter.create;

import use_case.create.CreateInputBoundary;
import use_case.create.CreateInputData;


import java.util.Map;

public class CreateController {

    private final CreateInputBoundary createInteractor;

    public CreateController(CreateInputBoundary createInteractor) {
        this.createInteractor = createInteractor;
    }

    public void execute(String dishname, String instruction, Map<String, String> ingredient) {
        final CreateInputData createInputData = new CreateInputData(
                dishname, instruction, ingredient);
        createInteractor.execute(createInputData);
    }
}
