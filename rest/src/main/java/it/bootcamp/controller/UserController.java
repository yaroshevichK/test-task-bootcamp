package it.bootcamp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.bootcamp.exeption.NotCorrectPageException;
import it.bootcamp.exeption.NotExistsInDBException;
import it.bootcamp.exeption.NotUniqueException;
import it.bootcamp.model.UserList;
import it.bootcamp.model.UserResponse;
import it.bootcamp.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static it.bootcamp.Constants.ATTR_PAGE_NUMBER;
import static it.bootcamp.Constants.DEFAULT_PAGE_NUMBER;
import static it.bootcamp.Constants.LOG_EMAIL_NOT_UNIQUE;
import static it.bootcamp.Constants.LOG_NEW_USER;
import static it.bootcamp.Constants.LOG_SAVE_USER;
import static it.bootcamp.Constants.LOG_USER_BY_EMAIL;
import static it.bootcamp.Constants.MESSAGE_BAD_PAGE_NUMBER;
import static it.bootcamp.Constants.MESSAGE_EMPTY_USERS_LIST;
import static it.bootcamp.Constants.NAME_API;
import static it.bootcamp.Constants.NAME_API_LIST_USERS;
import static it.bootcamp.Constants.NAME_API_NEW_USER;
import static it.bootcamp.Constants.REST_API;
import static it.bootcamp.Constants.REST_USERS;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = REST_API, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = NAME_API)
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final IUserService userService;


    @GetMapping(value = REST_USERS)
    @Operation(description = NAME_API_LIST_USERS)
    public ResponseEntity<List<UserList>> getUsers(
            @RequestParam(value = ATTR_PAGE_NUMBER,
                    defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNumber
    ) {
        Page<UserList> pageUsers = userService.getAllUsers(pageNumber - 1);
        if (pageNumber > pageUsers.getTotalPages()) {
            throw new NotCorrectPageException(
                    String.format(MESSAGE_BAD_PAGE_NUMBER, pageUsers.getTotalPages()));
        }

        if (pageUsers.getContent().isEmpty()) {
            throw new NotExistsInDBException(MESSAGE_EMPTY_USERS_LIST);
        }

        return new ResponseEntity<>(pageUsers.getContent(), HttpStatus.OK);
    }

    @PostMapping(value = REST_USERS, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = NAME_API_NEW_USER)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserResponse user) {
        UserResponse userByEmail = userService.findUserByEmail(user.getEmail());
        logger.info(LOG_NEW_USER, user);
        logger.info(LOG_USER_BY_EMAIL, userByEmail);
        if (userByEmail != null) {
            throw new NotUniqueException(String.format(LOG_EMAIL_NOT_UNIQUE, userByEmail.getEmail()));
        }
        UserResponse newUser = userService.createUser(user);
        logger.info(LOG_SAVE_USER, user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
