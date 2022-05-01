package carsharing.web.dto;

import carsharing.web.validator.MailUnique;
import carsharing.web.validator.Phone;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDTO extends CustomerDTO {

    @MailUnique
    @Email(message = "The field must be in the email format")
    private String email;

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,20}", message = "Password must: " +
            "\nBe at least 8 characters and no more than 20, " +
            "\nContain at least 1 uppercase letter, " +
            "\nContain special characters or numbers.")
    private String password;

    @Size(max = 18, min = 2, message = "The field \"Name\" - must be between 2 characters and 18 characters")
    private String name;

    @Size(max = 18, min = 2, message = "The field \"Surname\" - must be between 2 characters and 18 characters")
    private String surname;

    @Phone
    @Size(max = 13, min = 12, message = "The field \"Phone number\" - must have 13 characters")
    private String phoneNumber;

}
