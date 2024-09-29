package LogicEngine.eng.controller;

import LogicEngine.eng.model.Expression;
import LogicEngine.eng.repository.ExpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expression")
public class ExpressionController {

    @Autowired
    private ExpressionRepository expressionRepository;

    @PostMapping
    public ResponseEntity<?> createExpression(@RequestBody ExpressionRequest request) {
        Expression expression = new Expression(request.getName(), request.getValue());
        Expression savedExpression = expressionRepository.save(expression);
        return ResponseEntity.ok(new ExpressionResponse(savedExpression.getId()));
    }

    public static class ExpressionRequest {
        private String name;
        private String value;

        public String getName() { return name; }
        public String getValue() { return value; }

        public void setName(String name) {
            this.name = name;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class ExpressionResponse {
        private Long id;

        public ExpressionResponse(Long id) {
            this.id = id;
        }

        public Long getId() { return id; }

        public void setId(Long id) {
            this.id = id;
        }
    }
}