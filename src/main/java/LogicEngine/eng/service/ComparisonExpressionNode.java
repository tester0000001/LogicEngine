package LogicEngine.eng.service;

public class ComparisonExpressionNode extends ExpressionNode {

    private Token operator;
    private ExpressionNode left;
    private ExpressionNode right;

    public ComparisonExpressionNode(Token operator, ExpressionNode left, ExpressionNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        Object leftValue = left.evaluateValue(context);
        Object rightValue = right.evaluateValue(context);

        if (operator.getType() == TokenType.EQUALS) {
            return leftValue.equals(rightValue);
        } else if (operator.getType() == TokenType.NOT_EQUALS) {
            return !leftValue.equals(rightValue);
        } else if (operator.getType() == TokenType.LESS_THAN) {
            return compare(leftValue, rightValue) < 0;
        } else if (operator.getType() == TokenType.GREATER_THAN) {
            return compare(leftValue, rightValue) > 0;
        } else if (operator.getType() == TokenType.LESS_THAN_OR_EQUALS) {
            return compare(leftValue, rightValue) <= 0;
        } else if (operator.getType() == TokenType.GREATER_THAN_OR_EQUALS) {
            return compare(leftValue, rightValue) >= 0;
        } else {
            throw new RuntimeException("Unknown comparison operator: " + operator.getText());
        }
    }

    private int compare(Object leftValue, Object rightValue) {
        if (leftValue instanceof Number && rightValue instanceof Number) {
            double leftNum = ((Number) leftValue).doubleValue();
            double rightNum = ((Number) rightValue).doubleValue();
            return Double.compare(leftNum, rightNum);
        } else if (leftValue instanceof String && rightValue instanceof String) {
            return ((String) leftValue).compareTo((String) rightValue);
        } else {
            throw new RuntimeException("Cannot compare values: " + leftValue + " and " + rightValue);
        }
    }
}