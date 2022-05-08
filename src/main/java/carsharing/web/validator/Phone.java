package carsharing.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface Phone {
    String message() default "This phone number is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}