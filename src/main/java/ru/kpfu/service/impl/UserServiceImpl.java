package ru.kpfu.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.Repositories.UserDAO;
import ru.kpfu.dto.mapper.UserMapper;
import ru.kpfu.dto.userDTO.RegisterForm;
import ru.kpfu.dto.userDTO.UserDTO;
import ru.kpfu.models.User;
import ru.kpfu.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO database) {
        this.userDAO = database;
    }

    public boolean registerUser(RegisterForm registerForm) {
        String email = registerForm.getEmail();
        if (userDAO.isEmailValid(email)) {
            return false;
        }
        String hashedPassword = BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt());

        User user = User.builder()
                .name(registerForm.getName())
                .email(email)
                .password(hashedPassword)
                .build();

        userDAO.save(user);

        return true;
    }


    public UserDTO loginUser(String email, String password) {
        try {
            User user = userDAO.getUserByEmail(email);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return UserMapper.toDto(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<UserDTO> getUserById(int id) {
        Optional<User> userOptional = userDAO.getUserById(id);
        return userOptional.map(UserMapper::toDto);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.getAll();
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public int getUserIdByEmail(String email) {
        try {
            return userDAO.getUserIdByEmail(email);
        } catch (SQLException e) {
            return -1;
        }
    }
}
