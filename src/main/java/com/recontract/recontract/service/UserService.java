package com.recontract.recontract.service;

import com.recontract.recontract.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User getUserById(long userId);
    void uploadProfilePicture(Long userId, MultipartFile file) throws IOException;
    byte[] getProfilePicture(Long userId);
    void deleteUserById(Long userId);
}
