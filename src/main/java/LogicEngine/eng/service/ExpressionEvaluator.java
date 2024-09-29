package LogicEngine.eng.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExpressionEvaluator {

    private final ObjectMapper objectMapper;

    public ExpressionEvaluator() {
        this.objectMapper = new ObjectMapper();
    }

    public boolean evaluate(String expression, Object jsonData) throws Exception {
        Map<String, Object> dataMap = objectMapper.convertValue(jsonData, Map.class);

        Tokenizer tokenizer = new Tokenizer(expression);
        ExpressionParser parser = new ExpressionParser(tokenizer.tokenize());
        ExpressionNode ast = parser.parseExpression();

        EvaluationContext context = new EvaluationContext(dataMap);
        return ast.evaluate(context);
    }
}