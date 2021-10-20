package com.recontract.recontract.service;

import java.util.Optional;
import com.recontract.recontract.exception.BadRequestException;
import com.recontract.recontract.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.recontract.recontract.domain.User;
import com.recontract.recontract.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updateUser(String username,
                           String password,
                           long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            user.get().setUsername(username);
            user.get().setPassword(passwordEncoder.encode(password));
            userRepository.save(user.get());
        } else {
            throw new BadRequestException();
        }
    }

    @Override
    public void deleteUserById(long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new RecordNotFoundException();
        }
    }
}
