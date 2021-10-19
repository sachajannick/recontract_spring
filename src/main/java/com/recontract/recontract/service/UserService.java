package com.recontract.recontract.service;

public interface UserService {

    void updateUser(String username,
                    String password,
                    Long userId);
    void deleteUserById(Long userId);
}
