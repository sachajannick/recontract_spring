package com.recontract.recontract.service;

import java.io.IOException;
import java.util.Optional;

import com.recontract.recontract.exception.BadRequestException;
import com.recontract.recontract.exception.RecordNotFoundException;
import com.recontract.recontract.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recontract.recontract.domain.User;
import com.recontract.recontract.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserById(long userId) {
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
    public void updateUser(String username, String email, String password, String fullName, String location, String headline, byte[] profilePicture, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setUsername(username);
            user.get().setEmail(email);
            user.get().setPassword(password);
            user.get().setFullName(fullName);
            user.get().setLocation(location);
            user.get().setHeadline(headline);
            user.get().setProfilePicture(profilePicture);
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
