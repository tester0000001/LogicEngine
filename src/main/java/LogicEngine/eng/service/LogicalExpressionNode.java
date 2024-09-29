package LogicEngine.eng.service;

public class LogicalExpressionNode extends ExpressionNode {

    private Token operator;
    private ExpressionNode left;
    private ExpressionNode right;

    public LogicalExpressionNode(Token operator, ExpressionNode left, ExpressionNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        boolean leftValue = left.evaluate(context);
        boolean rightValue = right.evaluate(context);

        if (operator.getType() == TokenType.AND) {
            return leftValue && rightValue;
        } else if (operator.getType() == TokenType.OR) {
            return leftValue || rightValue;
        } else {
            throw new RuntimeException("Unknown logical operator: " + operator.getText());
        }
    }
}