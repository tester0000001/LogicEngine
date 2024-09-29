package LogicEngine.eng.service;

import java.util.List;

public class ExpressionParser {

    private final List<Token> tokens;
    private int pos = 0;
    private Token currentToken;

    public ExpressionParser(List<Token> tokens) {
        this.tokens = tokens;
        advance();
    }

    private void advance() {
        if (pos < tokens.size()) {
            currentToken = tokens.get(pos++);
        } else {
            currentToken = new Token(TokenType.EOF, "");
        }
    }

    public ExpressionNode parseExpression() {
        return parseOrExpression();
    }

    private ExpressionNode parseOrExpression() {
        ExpressionNode node = parseAndExpression();
        while (currentToken.getType() == TokenType.OR) {
            Token op = currentToken;
            advance();
            ExpressionNode right = parseAndExpression();
            node = new LogicalExpressionNode(op, node, right);
        }
        return node;
    }

    private ExpressionNode parseAndExpression() {
        ExpressionNode node = parseComparisonExpression();
        while (currentToken.getType() == TokenType.AND) {
            Token op = currentToken;
            advance();
            ExpressionNode right = parseComparisonExpression();
            node = new LogicalExpressionNode(op, node, right);
        }
        return node;
    }

    private ExpressionNode parseComparisonExpression() {
        if (currentToken.getType() == TokenType.LPAREN) {
            advance();
            ExpressionNode node = parseExpression();
            expect(TokenType.RPAREN);
            return node;
        } else {
            ExpressionNode left = parseOperand();
            Token op = currentToken;
            if (isComparisonOperator(op.getType())) {
                advance();
                ExpressionNode right = parseOperand();
                return new ComparisonExpressionNode(op, left, right);
            } else {
                throw new RuntimeException("Expected comparison operator but found: " + currentToken.getText());
            }
        }
    }

    private ExpressionNode parseOperand() {
        if (currentToken.getType() == TokenType.IDENTIFIER) {
            Token token = currentToken;
            advance();
            return new VariableNode(token.getText());
        } else if (currentToken.getType() == TokenType.STRING) {
            Token token = currentToken;
            advance();
            return new ValueNode(token.getText());
        } else if (currentToken.getType() == TokenType.NUMBER) {
            Token token = currentToken;
            advance();
            return new ValueNode(Integer.parseInt(token.getText()));
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken.getText());
        }
    }

    private void expect(TokenType type) {
        if (currentToken.getType() == type) {
            advance();
        } else {
            throw new RuntimeException("Expected token: " + type + " but found: " + currentToken.getType());
        }
    }

    private boolean isComparisonOperator(TokenType type) {
        return type == TokenType.EQUALS || type == TokenType.NOT_EQUALS ||
               type == TokenType.LESS_THAN || type == TokenType.GREATER_THAN ||
               type == TokenType.LESS_THAN_OR_EQUALS || type == TokenType.GREATER_THAN_OR_EQUALS;
    }
}