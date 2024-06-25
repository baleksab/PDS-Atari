package baleksab.pdsbattleship.exception;

import lombok.Getter;

import java.util.Set;

@Getter
public class ValidationException extends RuntimeException {

    private final Set<String> violations;

    public ValidationException(String message, Set<String> violations) {
        super(message);
        this.violations = violations;
    }

}
