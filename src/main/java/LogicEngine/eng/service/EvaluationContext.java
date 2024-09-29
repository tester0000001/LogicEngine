package LogicEngine.eng.service;

import java.util.Map;

public class EvaluationContext {

    private Map<String, Object> data;

    public EvaluationContext(Map<String, Object> data) {
        this.data = data;
    }

    public Object resolveVariable(String variableName) {
        String[] parts = variableName.split("\\.");
        Object current = data;
        for (String part : parts) {
            if (current instanceof Map) {
                current = ((Map<?, ?>) current).get(part);
            } else {
                throw new RuntimeException("Cannot resolve variable '" + variableName + "' at '" + part + "'");
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }
}