package it.bootcamp.mapper;

import it.bootcamp.model.User;
import it.bootcamp.model.UserList;
import it.bootcamp.model.UserResponse;

import static it.bootcamp.Constants.FORMAT_FIO;

public class UserMapper implements Mapper {
    public static UserList mapUserToUserList(User user) {
        return UserList
                .builder()
                .FIO(String.format(FORMAT_FIO,
                        user.getLastName(),
                        user.getFirstName(),
                        user.getMiddleName()))
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }

    public static User mapUserResponseToUser(UserResponse userResponse) {
        return User
                .builder()
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .middleName(userResponse.getMiddleName())
                .email(userResponse.getEmail())
                .build();
    }

    public static UserResponse mapUserToUserResponse(User user) {
        return UserResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }
}
