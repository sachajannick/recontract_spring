package com.recontract.recontract.controller;

import com.recontract.recontract.domain.User;
import com.recontract.recontract.dto.dtoUser;
import com.recontract.recontract.service.UserServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('USER')")
    public List<User> findAllUsers() {
        return userServiceImpl.findAllUsers();
    }

    @GetMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public User findUserById(@PathVariable("id") long userId) {
        return userServiceImpl.findUserById(userId);
    }

    @PatchMapping(value="/profile-picture/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> uploadProfilePicture(@PathVariable ("id") long userId, @RequestParam("file") MultipartFile file) throws IOException {
        userServiceImpl.uploadProfilePicture(userId, file);
        return ResponseEntity.ok("Profile picture uploaded");
    }

    @GetMapping(value = "/profile-picture/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable ("id") long userId) {
        var picture = userServiceImpl.getProfilePicture(userId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"picture\"").body(picture);
    }

    @PatchMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateUser(@PathVariable ("id") Long userId, @RequestBody dtoUser dto) {
        userServiceImpl.updateUser(dto.newUsername, dto.newEmail, dto.newPassword, dto.newFullName, dto.newLocation, dto.newHeadline, userId);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping(value = "/delete/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") long userId) {
        userServiceImpl.deleteUserById(userId);
        return ResponseEntity.ok("User deleted with id: " + userId);
    }
}
