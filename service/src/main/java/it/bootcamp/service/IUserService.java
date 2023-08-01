package it.bootcamp.service;

import it.bootcamp.model.UserList;
import it.bootcamp.model.UserResponse;
import org.springframework.data.domain.Page;

public interface IUserService {
    Page<UserList> getAllUsers(int pageNumber);

    UserResponse createUser(UserResponse response);

    UserResponse findUserByEmail(String email);
}
