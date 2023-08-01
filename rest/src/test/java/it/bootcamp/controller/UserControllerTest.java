package it.bootcamp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.bootcamp.model.UserList;
import it.bootcamp.model.UserResponse;
import it.bootcamp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest
class UserControllerTest {
    public static final String TEST_NAME = "Will";
    public static final String TEST_SURNAME = "Smith";
    public static final String TEST_MIDDLE_NAME = "Bill";
    public static final String TEST_EMAIL = "wsb@gmail.com";
    public static final String CUSTOMER_ROLE = "Customer User";

    public static final int TEST_PAGE_NUMBER = 1;
    public static final int NOT_CORRECT_PAGE_NUMBER = 2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUser() throws Exception {
        UserResponse user = UserResponse.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(CUSTOMER_ROLE)
                .build();

        given(userService.createUser(any(UserResponse.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(user.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(user.getEmail())));
    }

    @Test
    public void getPageUsers() throws Exception {
        UserResponse newUser = UserResponse.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(CUSTOMER_ROLE)
                .build();
        userService.createUser(newUser);

        UserList user = UserList.builder()
                .FIO(TEST_SURNAME + " " + TEST_NAME + " " + TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(CUSTOMER_ROLE)
                .build();

        List<UserList> userList = new ArrayList<>();
        userList.add(user);
        Page<UserList> users = new PageImpl<>(userList);

        given(userService.getAllUsers(TEST_PAGE_NUMBER - 1))
                .willReturn(users);

        mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())))
                .andExpect(jsonPath("$[0].fio",
                        is(TEST_SURNAME + " " + TEST_NAME + " " + TEST_MIDDLE_NAME)))
                .andExpect(jsonPath("$[0].email", is(TEST_EMAIL)))
                .andExpect(jsonPath("$[0].role", is(CUSTOMER_ROLE)));
    }

    @Test
    public void getPageUsersWithNotCorrectPageNumber() throws Exception {
        UserResponse newUser = UserResponse.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_SURNAME)
                .middleName(TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(CUSTOMER_ROLE)
                .build();
        userService.createUser(newUser);

        UserList user = UserList.builder()
                .FIO(TEST_SURNAME + " " + TEST_NAME + " " + TEST_MIDDLE_NAME)
                .email(TEST_EMAIL)
                .role(CUSTOMER_ROLE)
                .build();

        List<UserList> userList = new ArrayList<>();
        userList.add(user);
        Page<UserList> users = new PageImpl<>(userList);

        when(userService.getAllUsers(NOT_CORRECT_PAGE_NUMBER - 1)).getMock();

        mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

}