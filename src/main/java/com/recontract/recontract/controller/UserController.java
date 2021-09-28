package com.recontract.recontract.controller;

import com.recontract.recontract.service.UserServiceImpl;
import org.springframework.http.HttpHeaders;
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

    @GetMapping(value = "/picture/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable ("id") long userId) {
        var picture = userServiceImpl.getProfilePicture(userId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"picture\"").body(picture);
    }

//    @PatchMapping
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Object> banaan() {
//        return ResponseEntity.ok("LEUK");
//    }
}
