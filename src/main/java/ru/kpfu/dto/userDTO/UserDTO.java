package ru.kpfu.dto.userDTO;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String role;
}

