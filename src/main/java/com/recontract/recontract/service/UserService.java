package com.recontract.recontract.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    void uploadProfilePicture(Long id, MultipartFile file) throws IOException;
    byte[] getProfilePicture(Long id);
}
