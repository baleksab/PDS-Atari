package baleksab.pdsatari.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.*;

import java.util.Set;
import java.util.stream.Collectors;


@ApplicationScoped
public class BeanValidator {

    private final Validator validator;

    public BeanValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            this.validator = factory.getValidator();
        }
    }

    public <T> Set<String> validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
    }
}

