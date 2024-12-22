package ru.kpfu.servlets.dto.userDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
    private String name;
    private String email;
    private String password;
}
