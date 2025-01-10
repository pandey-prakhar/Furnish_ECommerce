package org.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userservice.models.Role;
import org.example.userservice.models.User;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;

    public static UserDto from(User user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRoles(user.getRoles());
        return dto;
    }
}
