package org.example.task_one.service.user;

import lombok.RequiredArgsConstructor;
import org.example.task_one.exceptions.custom.EntityNotFoundException;
import org.example.task_one.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        var user = userRepository.findByLogin(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователя с таким email не существует."));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), true, true, true,
                true, List.of(user.getRole()));
    }
}