package it.bootcamp.mapper;

import it.bootcamp.model.Role;
import it.bootcamp.model.User;
import it.bootcamp.model.UserList;
import it.bootcamp.model.UserResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserMapperTest {
    public static final String TEST_NAME = "Will";
    public static final String TEST_SURNAME = "Smith";
    public static final String TEST_MIDDLE_NAME = "Bill";
    public static final String TEST_EMAIL = "wsb@gmail.com";
    public static final String CUSTOMER_ROLE = "Customer User";

    private static User user;
    private static UserResponse userResponse;

    @BeforeAll
    static void init() {
        user = User.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(new Role(null, CUSTOMER_ROLE))
                .build();

        userResponse = UserResponse.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(CUSTOMER_ROLE)
                .build();
    }

    @Test
    void mapUserToUserList() {
        UserList mapUserList = UserMapper.mapUserToUserList(user);

        assertAll(
                () -> assertNotNull(mapUserList),
                () -> assertNotNull(mapUserList.getFIO(), TEST_SURNAME + " " + TEST_NAME + " " + TEST_MIDDLE_NAME),
                () -> assertNotNull(mapUserList.getEmail(), TEST_EMAIL),
                () -> assertEquals(user.getRole().getName(), CUSTOMER_ROLE)
        );
    }

    @Test
    void mapUserResponseToUser() {
        User mapUser = UserMapper.mapUserResponseToUser(userResponse);
        assertAll(
                () -> assertNotNull(mapUser),
                () -> assertNotNull(mapUser.getFirstName(), TEST_NAME),
                () -> assertNotNull(mapUser.getLastName(), TEST_SURNAME),
                () -> assertNotNull(mapUser.getMiddleName(), TEST_MIDDLE_NAME),
                () -> assertNotNull(mapUser.getEmail(), TEST_EMAIL),
                () -> assertEquals(user.getRole().getName(), CUSTOMER_ROLE)
        );
    }

    @Test
    void mapUserToUserResponse() {
        UserResponse mapUserResponse = UserMapper.mapUserToUserResponse(user);
        assertAll(
                () -> assertNotNull(mapUserResponse),
                () -> assertNotNull(mapUserResponse.getFirstName(), TEST_NAME),
                () -> assertNotNull(mapUserResponse.getLastName(), TEST_SURNAME),
                () -> assertNotNull(mapUserResponse.getMiddleName(), TEST_MIDDLE_NAME),
                () -> assertNotNull(mapUserResponse.getEmail(), TEST_EMAIL),
                () -> assertEquals(user.getRole().getName(), CUSTOMER_ROLE)
        );
    }
}