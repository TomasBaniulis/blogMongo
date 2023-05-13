package lt.code.academy.blogmongo.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lt.code.academy.blogmongo.validator.PasswordValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Andrius Baltrunas
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "{password.constraint.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}