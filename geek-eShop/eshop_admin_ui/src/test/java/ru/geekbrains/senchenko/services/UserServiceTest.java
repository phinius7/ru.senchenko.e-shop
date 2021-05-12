package ru.geekbrains.senchenko.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.geekbrains.senchenko.controllers.repr.UserRepr;
import ru.geekbrains.senchenko.entities.User;
import ru.geekbrains.senchenko.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    public void init() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    public void testGetUsername() {
        User expectedUser = new User();
        expectedUser.setId(1L);
        expectedUser.setUserName("testUsername");
        expectedUser.setPassword("testPassword");

        when(userRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedUser));

        Optional<UserRepr> userTest = userService.findById(1L);

        assertNotNull(userTest);
        assertEquals(expectedUser.getId(), userTest.get().getId());
        assertEquals(expectedUser.getUserName(), userTest.get().getUserName());
    }
}
