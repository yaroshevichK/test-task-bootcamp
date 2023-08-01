package it.bootcamp.service;

import it.bootcamp.mapper.UserMapper;
import it.bootcamp.model.Role;
import it.bootcamp.model.User;
import it.bootcamp.model.UserList;
import it.bootcamp.model.UserResponse;
import it.bootcamp.repository.RoleRepository;
import it.bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static it.bootcamp.Constants.DEFAULT_PAGE_SIZE;
import static it.bootcamp.Constants.SORT_BY_EMAIL;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Page<UserList> getAllUsers(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, DEFAULT_PAGE_SIZE,
                Sort.Direction.ASC, SORT_BY_EMAIL);
        Page<User> pageUsers = userRepository.findAll(pageable);

        List<UserList> users = pageUsers.getContent().stream()
                .map(UserMapper::mapUserToUserList).toList();

        return new PageImpl<>(
                users,
                pageable,
                pageUsers.getTotalElements());
    }

    @Override
    public UserResponse createUser(UserResponse response) {
        Role role = roleRepository.findRoleByName(response.getRole());
        User user = UserMapper.mapUserResponseToUser(response);
        user.setRole(role);
        User newUser = userRepository.save(user);
        return Optional.ofNullable(newUser)
                .map(UserMapper::mapUserToUserResponse)
                .orElse(null);
    }

    @Override
    public UserResponse findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return Optional.ofNullable(user)
                .map(UserMapper::mapUserToUserResponse)
                .orElse(null);
    }
}
