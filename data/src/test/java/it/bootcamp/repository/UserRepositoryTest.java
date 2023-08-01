package it.bootcamp.repository;

import it.bootcamp.model.Role;
import it.bootcamp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static it.bootcamp.Constants.CUSTOMER_ROLE;
import static org.h2.engine.Constants.DEFAULT_PAGE_SIZE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    public static final String TEST_NAME = "Will";
    public static final String TEST_SURNAME = "Smith";
    public static final String TEST_MIDDLE_NAME = "Bill";
    public static final String TEST_EMAIL = "wsb@gmail.com";
    public static final String NOT_EXIST_EMAIL = "";
    public static final int TEST_PAGE_NUMBER = 1;
    public static final String SORT_FIELD = "email";
    public static final int NOT_CORRECT_PAGE_NUMBER = 2;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private static Stream<String> testEmail() {
        return Stream.of("ii@gmail.com", "pp@gmail.com", "ss@gmail.com", "vv@gmail.com");
    }

    @ParameterizedTest
    @MethodSource(value = "testEmail")
    void findUserByEmail(String email) {
        User userByEmail = userRepository.findUserByEmail(email);
        assertNotNull(userByEmail);
    }

    @Test
    void findUserByNotExistEmail() {
        User userByEmail = userRepository.findUserByEmail(NOT_EXIST_EMAIL);
        assertNull(userByEmail);
    }

    @Test
    void isShouldCreateUser() {
        Role customer = roleRepository.findRoleByName(CUSTOMER_ROLE);
        User user = User.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(customer)
                .build();

        User newUser = userRepository.save(user);

        assertAll(
                () -> assertNotNull(newUser),
                () -> assertEquals(newUser.getId(),5),
                () -> assertNotNull(newUser.getFirstName(), TEST_NAME),
                () -> assertNotNull(newUser.getLastName(), TEST_SURNAME),
                () -> assertNotNull(newUser.getMiddleName(), TEST_MIDDLE_NAME),
                () -> assertNotNull(newUser.getEmail(), TEST_EMAIL),
                () -> assertEquals(user.getRole().getName(), CUSTOMER_ROLE)
        );
    }

    @Test
    void shouldFindAllUsers() {
        Pageable pageable = PageRequest.of(TEST_PAGE_NUMBER - 1, DEFAULT_PAGE_SIZE,
                Sort.Direction.ASC, SORT_FIELD);
        Page<User> userPage = userRepository.findAll(pageable);

        assertEquals(userPage.getContent().size(), 4);
    }

    @Test
    void testNotCorrectNumberPage() {
        Pageable pageable = PageRequest.of(NOT_CORRECT_PAGE_NUMBER - 1, DEFAULT_PAGE_SIZE,
                Sort.Direction.ASC, SORT_FIELD);
        Page<User> userPage = userRepository.findAll(pageable);

        assertAll(
                () -> assertTrue(NOT_CORRECT_PAGE_NUMBER > userPage.getTotalPages()),
                () -> assertTrue(userPage.getContent().isEmpty())
        );
    }
}