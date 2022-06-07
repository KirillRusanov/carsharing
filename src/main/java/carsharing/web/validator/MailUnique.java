package carsharing.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MailUniqueValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface MailUnique {
    String message() default "This email is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}