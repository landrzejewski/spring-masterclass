package pl.training.tdd;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemplateTest {

    private final Map<String, String> parameters = Map.of("firstName", "Jan", "lastName", "Kowalski");
    private final Template template = new Template("My name is ${firstName} ${lastName}");

    @Test
    void shouldEvaluateTextWithExpressions() {
        assertEquals("My name is Jan Kowalski", template.evaluate(parameters));
    }

    @Test
    void shouldThrowExceptionWhenParameterIsMissing() {
        assertThrows(IllegalArgumentException.class, () ->  template.evaluate(new HashMap<>()));
    }

    @Test
    void shouldAcceptOnlyAlphaNumericValues() {
        assertThrows(IllegalArgumentException.class, () ->  template.evaluate(Map.of("firstName", "**", "lastName", "**")));
    }

}
