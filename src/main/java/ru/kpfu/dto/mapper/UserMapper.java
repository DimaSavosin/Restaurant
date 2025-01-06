package ru.kpfu.dto.mapper;


import ru.kpfu.dto.userDTO.UserDTO;
import ru.kpfu.models.User;

public class UserMapper {

    public static UserDTO toDto(User user) {


        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {


        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .build();
    }
}

