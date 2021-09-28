package com.recontract.recontract.controller;

import com.recontract.recontract.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PatchMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> uploadProfilePicture(@PathVariable ("id") long userId, @RequestParam("file") MultipartFile file) throws IOException {
        userServiceImpl.uploadProfilePicture(userId, file);
        return ResponseEntity.ok("Profile picture uploaded");
    }

//    @PatchMapping
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Object> banaan() {
//        return ResponseEntity.ok("LEUK");
//    }
}
