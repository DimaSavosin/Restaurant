package ru.kpfu.servlets.service;

import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.servlets.Repositories.UserDAO;
import ru.kpfu.servlets.dto.RegisterForm;
import ru.kpfu.servlets.dto.UserDto;
import ru.kpfu.servlets.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserDAO userDAO;
    public UserService(UserDAO database) {
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

    // Аутентификация пользователя
    public UserDto loginUser(String email, String password) {
        try {
            User user = userDAO.getUserByEmail(email);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return mapToDto(user); // Возвращаем DTO вместо модели
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public UserDto getUserById(int id) {
        User user = userDAO.getUserById(id);
        return mapToDto(user);
    }


    public List<UserDto> getAllUsers() throws SQLException {
        List<User> users = userDAO.getAll();
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    public int getUserIdByEmail(String email) {
        try {
            return userDAO.getUserIdByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    private UserDto mapToDto(User user) {
        if (user == null) return null;

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
