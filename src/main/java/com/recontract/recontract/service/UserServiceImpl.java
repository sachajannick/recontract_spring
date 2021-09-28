package com.recontract.recontract.service;

import java.io.IOException;
import java.util.Optional;

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
    public void uploadProfilePicture(Long id, MultipartFile file) throws IOException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setProfilePicture(file.getBytes());
            userRepository.save(user.get());
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public byte[] getProfilePicture(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getProfilePicture();
        } else {
            throw new RuntimeException();
        }
    }
}
