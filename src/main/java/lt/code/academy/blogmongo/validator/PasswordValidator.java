package lt.code.academy.blogmongo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lt.code.academy.blogmongo.dto.User;
import lt.code.academy.blogmongo.validator.annotation.Password;

/**
 * @author Andrius Baltrunas
 */
public class PasswordValidator implements ConstraintValidator<Password, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if(user == null) {
            return false;
        }

        String password = user.getPassword();
        String repeatPassword = user.getRepeatPassword();

        return password != null && password.equals(repeatPassword);
    }
}