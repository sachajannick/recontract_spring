package com.recontract.recontract.controller;

import com.recontract.recontract.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.recontract.recontract.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/users")
public class UserController {

    // NO EXCEPTIONS HERE -> SHOULD BE IN SERVICE LAYER

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PatchMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> banaan() {
        return ResponseEntity.ok("LEUK");
    }

//    @PatchMapping
//
//    @PostMapping("/{id}/profile-picture")
//    public void uploadProfilePicture(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
//        try {
//            userService.uploadProfilePicture(id, file);
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }
// add getmapping - profilepicture
}
