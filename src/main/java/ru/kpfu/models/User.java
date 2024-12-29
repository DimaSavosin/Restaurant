package ru.kpfu.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;
    private int id;
    private String role;

}
