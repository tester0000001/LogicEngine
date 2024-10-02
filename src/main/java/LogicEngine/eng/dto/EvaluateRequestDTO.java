package LogicEngine.eng.dto;

public class EvaluateRequestDTO {
    private Long expressionId;
    private Object data;

    public Long getExpressionId() {
        return expressionId;
    }

    public void setExpressionId(Long expressionId) {
        this.expressionId = expressionId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
