package it.bootcamp.model;

import it.bootcamp.validation.RolesConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static it.bootcamp.Constants.MAX_LENGTH_MIDDLE_NAME;
import static it.bootcamp.Constants.MAX_LENGTH_NAME;
import static it.bootcamp.Constants.MAX_LENGTH_SURNAME;
import static it.bootcamp.Constants.MESSAGE_LENGTH_MIDDLE_NAME;
import static it.bootcamp.Constants.MESSAGE_LENGTH_NAME;
import static it.bootcamp.Constants.MESSAGE_LENGTH_SURNAME;
import static it.bootcamp.Constants.MESSAGE_SYMBOL;
import static it.bootcamp.Constants.MIN_LENGTH_MIDDLE_NAME;
import static it.bootcamp.Constants.MIN_LENGTH_NAME;
import static it.bootcamp.Constants.MIN_LENGTH_SURNAME;
import static it.bootcamp.Constants.NOT_BLANK;
import static it.bootcamp.Constants.REG_LATIN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @NotBlank(message = NOT_BLANK)
    @Size(min = MIN_LENGTH_NAME, max = MAX_LENGTH_NAME,
            message = MESSAGE_LENGTH_NAME)
    @Pattern(regexp = REG_LATIN, message = MESSAGE_SYMBOL)
    private String firstName;

    @NotBlank(message = NOT_BLANK)
    @Size(min = MIN_LENGTH_SURNAME, max = MAX_LENGTH_SURNAME,
            message = MESSAGE_LENGTH_SURNAME)
    @Pattern(regexp = REG_LATIN, message = MESSAGE_SYMBOL)
    private String lastName;

    @NotBlank(message = NOT_BLANK)
    @Size(min = MIN_LENGTH_MIDDLE_NAME, max = MAX_LENGTH_MIDDLE_NAME,
            message = MESSAGE_LENGTH_MIDDLE_NAME)
    @Pattern(regexp = REG_LATIN, message = MESSAGE_SYMBOL)
    private String middleName;

    @Email
    private String email;

    @RolesConstraint
    private String role;
}
