package com.recontract.recontract.service;

import com.recontract.recontract.domain.User;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User getUserById(Long id);
    User updateUser(User user, Long id);
//    void uploadProfilePicture(Long id, MultipartFile file) throws IOException;
//    byte[] getProfilePicture(Long id);
}
