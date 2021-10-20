package com.recontract.recontract.service;

public interface UserService {

    void updateUser(String username,
                    String password,
                    long id);
    void deleteUserById(long id);
}
