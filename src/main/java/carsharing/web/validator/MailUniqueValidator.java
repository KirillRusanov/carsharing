package carsharing.web.validator;

import carsharing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MailUniqueValidator implements ConstraintValidator<MailUnique, String> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void initialize(MailUnique constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return customerService.getCustomerDtoByEmail(email) == null;
    }
}