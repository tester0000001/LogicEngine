package LogicEngine.eng.controller;

import LogicEngine.eng.dto.EvaluateRequestDTO;
import LogicEngine.eng.dto.EvaluateResponseDTO;
import LogicEngine.eng.dto.ErrorResponseDTO;
import LogicEngine.eng.model.Expression;
import LogicEngine.eng.repository.ExpressionRepository;
import LogicEngine.eng.service.ExpressionEvaluator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

    private final ExpressionRepository expressionRepository;
    private final ExpressionEvaluator expressionEvaluator;

    public EvaluateController(ExpressionRepository expressionRepository, ExpressionEvaluator expressionEvaluator) {
        this.expressionRepository = expressionRepository;
        this.expressionEvaluator = expressionEvaluator;
    }

    @PostMapping
    public ResponseEntity<?> evaluateExpression(@RequestBody EvaluateRequestDTO request) {
        Long expressionId = request.getExpressionId();
        Object data = request.getData();

        Expression expression = expressionRepository.findById(expressionId)
                .orElseThrow(() -> new RuntimeException("Expression not found with ID: " + expressionId));

        boolean result;
        try {
            result = expressionEvaluator.evaluate(expression.getValue(), data);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }

        return ResponseEntity.ok(new EvaluateResponseDTO(result));
    }
}