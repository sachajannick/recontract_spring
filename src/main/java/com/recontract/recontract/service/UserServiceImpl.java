package com.recontract.recontract.service;

import java.io.IOException;

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
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public User updateUser(User user, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setFullName(user.getFullName());
        existingUser.setLocation(user.getLocation());
        existingUser.setHeadline(user.getHeadline());
//        existingUser.setProfilePicture(user.getProfilePicture());

        userRepository.save(existingUser);
        return existingUser;
    }

//    @Override
//    public void uploadProfilePicture(Long id, MultipartFile file) throws IOException {
//        var optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setProfilePicture(file.getBytes());
//            userRepository.save(user);
//        } else {
//            throw new RuntimeException();
//        }
//    }

//    @Override
//    public byte[] getProfilePicture(Long id) {
//        var optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            return user.getProfilePicture();
//        } else {
//            throw new ResourceNotFoundException("User", "Id", id);
//        }
//    }
}