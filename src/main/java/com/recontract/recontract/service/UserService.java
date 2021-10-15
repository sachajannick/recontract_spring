package com.recontract.recontract.service;

import com.recontract.recontract.domain.User;

public interface UserService {

    User findUserById(Long userId);
    void updateUser(String username,
                    String password,
                    Long userId);
    void deleteUserById(Long userId);
}
