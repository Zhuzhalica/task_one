package org.example.task_one.controller;

import lombok.RequiredArgsConstructor;
import org.example.task_one.dto.AuthenticateInfo;
import org.example.task_one.dto.CreateUserDto;
import org.example.task_one.mapper.UserMapper;
import org.example.task_one.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService service;
    private final UserMapper mapper = UserMapper.INSTANCE;

    @PostMapping
    public AuthenticateInfo auth(@RequestBody CreateUserDto request) throws AccessDeniedException {
        var user = mapper.toUserEntity(request);

        return service.auth(user);
    }
}
