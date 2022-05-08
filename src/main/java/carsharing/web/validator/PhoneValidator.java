package carsharing.web.validator;

import carsharing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String numberPhone, ConstraintValidatorContext constraintValidatorContext) {
        return customerService.getCustomerDtoByPhone(numberPhone) == null;
    }
}