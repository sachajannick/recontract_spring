package com.recontract.recontract.service;

import com.recontract.recontract.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User findUserById(Long userId);
    void uploadProfilePicture(Long userId, MultipartFile file) throws IOException;
    byte[] getProfilePicture(Long userId);
    void updateUser(String newUsername, String newEmail, String newPassword, String newFullName, String newLocation, String newHeadline, byte[] newProfilePicture, Long userId);
    void deleteUserById(Long userId);
}
