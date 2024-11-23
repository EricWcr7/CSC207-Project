package use_case.create;

import java.util.Map;

public class CreateInputData {
    private final String dishname;
    private final String instruction;
    private final Map<String, String> ingredient;

    public CreateInputData(String dishname, String instruction, Map<String, String> ingredient) {
        this.dishname = dishname;
        this.instruction = instruction;
        this.ingredient = ingredient;
    }

    public String getDishname() {
        return dishname;
    }

    public String getInstruction() {
        return instruction;
    }

    public Map<String, String> getIngredient() {
        return ingredient;
    }
}
