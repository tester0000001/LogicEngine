package LogicEngine.eng.service;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private final String input;
    private int pos = 0;
    private int length;
    private char currentChar;

    public Tokenizer(String input) {
        this.input = input;
        this.length = input.length();
        advance();
    }

    private void advance() {
        if (pos < length) {
            currentChar = input.charAt(pos++);
        } else {
            currentChar = '\0';
        }
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }

            if (currentChar == '(') {
                tokens.add(new Token(TokenType.LPAREN, "("));
                advance();
            } else if (currentChar == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")"));
                advance();
            } else if (currentChar == '&' && peek() == '&') {
                tokens.add(new Token(TokenType.AND, "&&"));
                advance();
                advance();
            } else if (currentChar == '|' && peek() == '|') {
                tokens.add(new Token(TokenType.OR, "||"));
                advance();
                advance();
            } else if (currentChar == '!' && peek() == '=') {
                tokens.add(new Token(TokenType.NOT_EQUALS, "!="));
                advance();
                advance();
            } else if (currentChar == '=' && peek() == '=') {
                tokens.add(new Token(TokenType.EQUALS, "=="));
                advance();
                advance();
            } else if (currentChar == '<' && peek() == '=') {
                tokens.add(new Token(TokenType.LESS_THAN_OR_EQUALS, "<="));
                advance();
                advance();
            } else if (currentChar == '>' && peek() == '=') {
                tokens.add(new Token(TokenType.GREATER_THAN_OR_EQUALS, ">="));
                advance();
                advance();
            } else if (currentChar == '<') {
                tokens.add(new Token(TokenType.LESS_THAN, "<"));
                advance();
            } else if (currentChar == '>') {
                tokens.add(new Token(TokenType.GREATER_THAN, ">"));
                advance();
            } else if (currentChar == '"') {
                tokens.add(new Token(TokenType.STRING, parseString()));
            } else if (Character.isDigit(currentChar)) {
                tokens.add(new Token(TokenType.NUMBER, parseNumber()));
            } else if (isIdentifierStart(currentChar)) {
                tokens.add(new Token(TokenType.IDENTIFIER, parseIdentifier()));
            } else {
                throw new RuntimeException("Unknown character: " + currentChar);
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private char peek() {
        if (pos < length) {
            return input.charAt(pos);
        } else {
            return '\0';
        }
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private String parseString() {
        StringBuilder result = new StringBuilder();
        advance();
        while (currentChar != '\0' && currentChar != '"') {
            result.append(currentChar);
            advance();
        }
        if (currentChar != '"') {
            throw new RuntimeException("Unterminated string literal");
        }
        advance();
        return result.toString();
    }

    private String parseNumber() {
        StringBuilder result = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }
        return result.toString();
    }

    private String parseIdentifier() {
        StringBuilder result = new StringBuilder();
        while (isIdentifierPart(currentChar)) {
            result.append(currentChar);
            advance();
        }
        return result.toString();
    }

    private boolean isIdentifierStart(char c) {
        return Character.isLetter(c) || c == '_';
    }

    private boolean isIdentifierPart(char c) {
        return Character.isLetterOrDigit(c) || c == '_' || c == '.';
    }
}