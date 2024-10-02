package LogicEngine.eng.controller;

import LogicEngine.eng.dto.ExpressionRequestDTO;
import LogicEngine.eng.dto.ExpressionResponseDTO;
import LogicEngine.eng.model.Expression;
import LogicEngine.eng.repository.ExpressionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expression")
public class ExpressionController {

    private final ExpressionRepository expressionRepository;

    public ExpressionController(ExpressionRepository expressionRepository) {
        this.expressionRepository = expressionRepository;
    }

    @PostMapping
    public ResponseEntity<?> createExpression(@RequestBody ExpressionRequestDTO request) {
        Expression expression = new Expression(request.getName(), request.getValue());
        Expression savedExpression = expressionRepository.save(expression);
        return ResponseEntity.ok(new ExpressionResponseDTO(savedExpression.getId()));
    }
}