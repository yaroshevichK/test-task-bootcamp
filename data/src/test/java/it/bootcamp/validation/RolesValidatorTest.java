package it.bootcamp.validation;

import it.bootcamp.model.UserResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RolesValidatorTest {
    public static final String TEST_NAME = "Will";
    public static final String TEST_SURNAME = "Smith";
    public static final String TEST_MIDDLE_NAME = "Bill";
    public static final String TEST_EMAIL = "wsb@gmail.com";
    public static final String CUSTOMER_ROLE = "Customer User";

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private UserResponse userResponse;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    void ValidatorIsLoaded() {
        assertNotNull(validator);
    }

    @Test
    void isNotValidName() {
        userResponse = UserResponse.builder()
                .firstName("")
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(CUSTOMER_ROLE)
                .build();

        Set<ConstraintViolation<UserResponse>> violations = validator.validate(userResponse);

        assertFalse(violations.isEmpty());
    }

    @Test
    void isNotValidRole() {
        userResponse = UserResponse.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role("Admin")
                .build();

        Set<ConstraintViolation<UserResponse>> violations = validator.validate(userResponse);

        assertFalse(violations.isEmpty());
    }
}