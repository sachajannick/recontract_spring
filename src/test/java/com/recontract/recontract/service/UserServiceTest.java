package com.recontract.recontract.service;

import com.recontract.recontract.domain.User;
import com.recontract.recontract.exception.BadRequestException;
import com.recontract.recontract.exception.RecordNotFoundException;
import com.recontract.recontract.exception.UserNotFoundException;
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
    public void findUserByIdSuccess() {
        // ARRANGE
        Long userId = 0L;

        // ACT
        User user = new User();
        user.setUserId(userId);

        // ASSERT
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        User user2 = userService.findUserById(userId);
        Assertions.assertEquals(user, user2);
    }

    @Test
    public void findUserByIdThrowsException() {
        Long userId = 0L;

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findUserById(userId));
    }

    @Test
    public void updateUserSuccess() {
        // ARRANGE
        Long userId = 0L;
        String username = "Username";
        String newUsername = "newUsername";
        String email = "email@email.com";
        String newEmail = "newEmail@email.com";
        String password = "password";
        String newPassword = "newPassword";
        String fullName = "First Last";
        String newFullName = "New First Last";
        String location = "Utrecht";
        String newLocation = "Laren";
        String headline = "Passionate Back-end Developer";
        String newHeadline = "New Passionate Back-end Developer";
        byte[] profilePicture = { (byte)0xba, (byte)0x8a, 0x0d,
                0x45, 0x25, (byte)0xad, (byte)0xd0, 0x12, (byte)0x98, (byte)0xa8, 0x08, 0x00,
                0x36, 0x1b, 0x11, 0x03 };
        byte[] newProfilePicture = { (byte)0xba, (byte)0x8a, 0x0d,
                0x45, 0x25, (byte)0xad, (byte)0xd0, 0x11, (byte)0x98, (byte)0xa8, 0x08, 0x00,
                0x36, 0x1b, 0x11, 0x03 };

        // ACT
        Optional<User> user = Optional.of(new User());
        user.get().setUserId(userId);
        user.get().setUsername(username);
        user.get().setEmail(email);
        user.get().setPassword(password);
        user.get().setFullName(fullName);
        user.get().setLocation(location);
        user.get().setHeadline(headline);
        user.get().setProfilePicture(profilePicture);

        // ASSERT
        when(userRepository.findById(userId)).thenReturn(user);
        when(passwordEncoder.encode(newPassword)).thenReturn(newPassword);
        userService.updateUser(newUsername, newEmail, newPassword, newFullName, newLocation, newHeadline, newProfilePicture, userId);
        verify(userRepository).save(userCaptor.capture());
        Assertions.assertEquals(newUsername, userCaptor.getValue().getUsername());
        Assertions.assertEquals(newEmail, userCaptor.getValue().getEmail());
        Assertions.assertEquals(newPassword, userCaptor.getValue().getPassword());
        Assertions.assertEquals(newFullName, userCaptor.getValue().getFullName());
        Assertions.assertEquals(newLocation, userCaptor.getValue().getLocation());
        Assertions.assertEquals(newHeadline, userCaptor.getValue().getHeadline());
        Assertions.assertEquals(newProfilePicture, userCaptor.getValue().getProfilePicture());
    }

    @Test
    public void updateUserThrowsException() {
        Long userId = 0L;
        String newUsername = "newUsername";
        String newEmail = "newEmail@email.com";
        String newPassword = "newPassword";
        String newFullName = "New First Last";
        String newLocation = "Laren";
        String newHeadline = "New Passionate Back-end Developer";
        byte[] newProfilePicture = { (byte)0xba, (byte)0x8a, 0x0d,
                0x45, 0x25, (byte)0xad, (byte)0xd0, 0x11, (byte)0x98, (byte)0xa8, 0x08, 0x00,
                0x36, 0x1b, 0x11, 0x03 };

        Assertions.assertThrows(BadRequestException.class, () -> userService.updateUser(newUsername, newEmail, newPassword, newFullName, newLocation, newHeadline, newProfilePicture, userId));
    }

    @Test
    public void deleteUserByIdSuccess() {
        // ARRANGE
        Long userId = 0L;
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
        Long userId = 0L;

        Assertions.assertThrows(RecordNotFoundException.class, () -> userService.deleteUserById(userId));
    }
}
