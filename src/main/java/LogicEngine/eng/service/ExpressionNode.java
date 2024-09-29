package LogicEngine.eng.service;

public abstract class ExpressionNode {
    public abstract boolean evaluate(EvaluationContext context);
    public Object evaluateValue(EvaluationContext context) {
        throw new UnsupportedOperationException("Cannot evaluate value");
    }
}