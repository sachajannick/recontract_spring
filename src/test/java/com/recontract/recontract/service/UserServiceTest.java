package com.recontract.recontract.service;

import com.recontract.recontract.domain.User;
import com.recontract.recontract.exception.BadRequestException;
import com.recontract.recontract.exception.RecordNotFoundException;
import com.recontract.recontract.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    public void updateUserSuccess() {
        // ARRANGE
        Long userId = 1L;
        String username = "Username";
        String newUsername = "newUsername";
        String password = "password";
        String newPassword = "newPassword";

        // ACT
        Optional<User> user = Optional.of(new User());
        user.get().setUserId(userId);
        user.get().setUsername(username);
        user.get().setPassword(password);
        when(userRepository.findById(userId)).thenReturn(user);
        when(passwordEncoder.encode(newPassword)).thenReturn(newPassword);
        userService.updateUser(newUsername,
                newPassword,
                userId);

        // ASSERT
        verify(userRepository).save(userCaptor.capture());
        Assertions.assertEquals(newUsername, userCaptor.getValue().getUsername());
        Assertions.assertEquals(newPassword, userCaptor.getValue().getPassword());
    }

    @Test
    public void updateUserThrowsException() {
        Long userId = 2L;
        String newUsername = "newUsername";
        String newEmail = "newEmail@email.com";
        String newPassword = "newPassword";
        String newFullName = "New First Last";

        Assertions.assertThrows(BadRequestException.class,
                () -> userService.updateUser(newUsername,
                newPassword,
                userId));
    }

    @Test
    public void deleteUserByIdSuccess() {
        // ARRANGE
        Long userId = 1L;
        String username = "username";
        String password = "password";
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);

        // ACT
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        userService.deleteUserById(userId);

        // ASSERT
        verify(userRepository).delete(userCaptor.capture());
        Assertions.assertEquals(userCaptor.getValue(), user);
    }

    @Test
    public void deleteUserByIdThrowsException() {
        Long userId = 2L;

        Assertions.assertThrows(RecordNotFoundException.class,
                () -> userService.deleteUserById(userId));
    }
}
