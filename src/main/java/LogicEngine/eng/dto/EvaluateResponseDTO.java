package LogicEngine.eng.dto;

public class EvaluateResponseDTO {
    private final boolean result;

    public EvaluateResponseDTO(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
