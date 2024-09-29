package LogicEngine.eng.service;

public class VariableNode extends ExpressionNode {

    private String variableName;

    public VariableNode(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        Object value = evaluateValue(context);
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            throw new RuntimeException("Variable '" + variableName + "' is not a boolean value");
        }
    }

    @Override
    public Object evaluateValue(EvaluationContext context) {
        Object value = context.resolveVariable(variableName);
        return value;
    }
}