package UnsereWelt.mapper;

import UnsereWelt.dto.user.UserDto;
import UnsereWelt.dto.user.UserRegistrationDto;
import UnsereWelt.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromRegistrationDto(UserRegistrationDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // wird später gehashed
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
