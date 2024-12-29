package ru.kpfu.service;

import ru.kpfu.dto.userDTO.RegisterForm;
import ru.kpfu.dto.userDTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
     boolean registerUser(RegisterForm registerForm);
    UserDTO loginUser(String email, String password);
    Optional<UserDTO> getUserById(int id);
    List<UserDTO> getAllUsers() ;
    int getUserIdByEmail(String email);


}
