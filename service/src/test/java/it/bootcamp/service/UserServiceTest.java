package it.bootcamp.service;

import it.bootcamp.model.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceTest {
    public static final String TEST_NAME = "Will";
    public static final String TEST_SURNAME = "Smith";
    public static final String TEST_MIDDLE_NAME = "Bill";
    public static final String TEST_EMAIL = "wsb@gmail.com";
    public static final String CUSTOMER_ROLE = "Customer User";

    @MockBean
    private UserService userService;

    @Test
    void createUser() {
        UserResponse user = UserResponse.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(CUSTOMER_ROLE)
                .build();

        userService.createUser(user);
        verify(userService, times(1)).createUser(any());
    }

    @Test
    void findUserByEmail() {
        userService.findUserByEmail(TEST_EMAIL);
        verify(userService, times(1)).findUserByEmail(any());
    }
}