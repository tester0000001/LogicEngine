package LogicEngine.eng.controller;

import LogicEngine.eng.model.Expression;
import LogicEngine.eng.repository.ExpressionRepository;
import LogicEngine.eng.service.ExpressionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private ExpressionRepository expressionRepository;

    @Autowired
    private ExpressionEvaluator expressionEvaluator;

    @PostMapping
    public ResponseEntity<?> evaluateExpression(
            @RequestParam Long expressionId,
            @RequestBody Object data) {

        Expression expression = expressionRepository.findById(expressionId)
                .orElseThrow(() -> new RuntimeException("Expression not found with ID: " + expressionId));

        boolean result;
        try {
            result = expressionEvaluator.evaluate(expression.getValue(), data);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new EvaluateResponse(result));
    }

    public static class EvaluateResponse {
        private boolean result;

        public EvaluateResponse(boolean result) {
            this.result = result;
        }

        public boolean isResult() { return result; }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() { return error; }

        public void setError(String error) {
            this.error = error;
        }
    }
}