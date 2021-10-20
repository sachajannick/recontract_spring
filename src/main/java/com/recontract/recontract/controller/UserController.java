package com.recontract.recontract.controller;

import com.recontract.recontract.dto.dtoUser;
import com.recontract.recontract.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PatchMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateUser(@PathVariable ("id") long id, @RequestBody dtoUser dto) {
        userServiceImpl.updateUser(dto.username,
                dto.password,
                id);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping(value = "/delete/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") long id) {
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.ok("User deleted with id: " + id);
    }
}
