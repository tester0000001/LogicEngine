package LogicEngine.eng.service;

public class ValueNode extends ExpressionNode {

    private Object value;

    public ValueNode(Object value) {
        this.value = value;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            throw new RuntimeException("Value '" + value + "' is not a boolean");
        }
    }

    @Override
    public Object evaluateValue(EvaluationContext context) {
        return value;
    }
}