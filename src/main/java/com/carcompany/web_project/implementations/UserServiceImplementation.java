package com.carcompany.web_project.implementations;

import com.carcompany.web_project.models.User;
import com.carcompany.web_project.repos.UserRepository;
import com.carcompany.web_project.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean exists(User user) {
        return exists(user.getUsername());
    }

    @Override
    public boolean exists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User edit(String username, String password) {
        Optional<User> optUser = userRepository.findById(username);
        if (!optUser.isPresent())
            return null;
        User user = optUser.get();
        user.setPassword(password);
        user.setUsername(username);
        return userRepository.save(user);
    }

    @Override
    public User delete(String username) {
        Optional<User> optUser = userRepository.findById(username);
        if (!optUser.isPresent())
            return null;
        userRepository.delete(optUser.get());
        return optUser.get();
    }

    @Override
    public User get(String username) {
        Optional<User> optUser = userRepository.findById(username);
        return optUser.orElse(null);
    }
}
