package com.recontract.recontract.service;

import java.io.IOException;
import java.util.Optional;

import com.recontract.recontract.exception.BadRequestException;
import com.recontract.recontract.exception.RecordNotFoundException;
import com.recontract.recontract.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.recontract.recontract.domain.User;
import com.recontract.recontract.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void uploadProfilePicture(Long userId, MultipartFile file) throws IOException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setProfilePicture(file.getBytes());
            userRepository.save(user.get());
        } else {
            throw new BadRequestException();
        }
    }

    @Override
    public byte[] getProfilePicture(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getProfilePicture();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void updateUser(String newUsername, String newEmail, String newPassword, String newFullName, String newLocation, String newHeadline, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setUsername(newUsername);
            user.get().setEmail(newEmail);
            user.get().setPassword(passwordEncoder.encode(newPassword));
            user.get().setFullName(newFullName);
            user.get().setLocation(newLocation);
            user.get().setHeadline(newHeadline);
            userRepository.save(user.get());
        } else {
            throw new BadRequestException();
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new RecordNotFoundException();
        }
    }
}
