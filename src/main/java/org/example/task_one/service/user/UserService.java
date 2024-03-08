package org.example.task_one.service.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.task_one.dto.AuthenticateInfo;
import org.example.task_one.model.User;
import org.example.task_one.repository.UserRepository;
import org.example.task_one.service.JWTService;
import org.example.task_one.utils.enums.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticateInfo auth(User user) throws AccessDeniedException {
        var originalPassword = user.getPassword();
        var repUser = repository.findByLogin(user.getLogin()).orElse(null);
        if (repUser == null) {
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repUser = repository.save(user);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getLogin(), originalPassword)
        );

        return new AuthenticateInfo(jwtService.getToken(repUser.getLogin()));
    }
}
