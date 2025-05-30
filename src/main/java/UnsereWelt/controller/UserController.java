package UnsereWelt.controller;

import UnsereWelt.dto.LoginDto;
import UnsereWelt.dto.UserDto;
import UnsereWelt.dto.UserRegistrationDto;
import UnsereWelt.entity.User;
import UnsereWelt.repository.UserRepository;
import UnsereWelt.security.filter.JwtService;
import UnsereWelt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173") // Erlaube React-Zugriff
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(), loginDto.getPassword()
                    )
            );
            // Benutzer laden
            User user = userRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));

            // ✅ Token erzeugen
            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(token); // Token an Frontend zurückgeben
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login fehlgeschlagen");
        }
    }

    // ✅ User registrieren
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistrationDto registrationDto) {
        try {
            UserDto registeredUser = userService.registerUser(registrationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // ✅ Admin registrieren
    @PostMapping("/register-admin")
    public ResponseEntity<UserDto> registerAdmin(@RequestBody @Valid UserRegistrationDto registrationDto) {
        UserDto adminUser = userService.registerAdmin(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminUser);
    }

    // ✅ Benutzer anhand der E-Mail abrufen
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
