package LogicEngine.eng.service;

public class Token {

    private TokenType type;
    private String text;
    
    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public TokenType getType() { return type; }
    public String getText() { return text; }
}