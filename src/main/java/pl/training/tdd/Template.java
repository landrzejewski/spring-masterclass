package pl.training.tdd;

import java.util.Map;

public class Template {

    private static final String EXPRESSION_START = "\\$\\{";
    private static final String EXPRESSION_END = "}";
    private static final String EXPRESSION = ".*\\$\\{[^}]+}.*";
    private static final String INVALID_VALUE = "\\W+";

    private final String textWithExpressions;

    public Template(String textWithExpressions) {
        this.textWithExpressions = textWithExpressions;
    }

    public String evaluate(Map<String, String> parameters) {
        validateParameters(parameters);
        String result = substituteParameters(textWithExpressions, parameters);
        validateEvaluatedText(result);
        return result;
    }

    private void validateParameters(Map<String, String> parameters) {
        if (parameters.values().stream().anyMatch(parameter -> parameter.matches(INVALID_VALUE))) {
            throw new IllegalArgumentException();
        }
    }

    private void validateEvaluatedText(String text) {
        if (text.matches(EXPRESSION)) {
            throw new IllegalArgumentException();
        }
    }

    private String substituteParameters(String textWithExpressions, Map<String, String> parameters) {
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String expression = createExpression(parameter.getKey());
            textWithExpressions = textWithExpressions.replaceAll(expression, parameter.getValue());
        }
        return textWithExpressions;
    }

    private String createExpression(String parameter) {
        return EXPRESSION_START + parameter + EXPRESSION_END;
    }

}
