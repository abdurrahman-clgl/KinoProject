package UnsereWelt.service;

import UnsereWelt.dto.UserDto;
import UnsereWelt.dto.UserRegistrationDto;
import UnsereWelt.entity.User;
import UnsereWelt.enums.Role;
import UnsereWelt.mapper.UserMapper;
import UnsereWelt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto registerUser(UserRegistrationDto registrationDto) {
        Optional<User> existingUser = userRepository.findByEmail(registrationDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email is already registered.");
        }

        User user = userMapper.fromRegistrationDto(registrationDto);
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDto);
    }
}
