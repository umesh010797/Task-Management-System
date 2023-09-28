package com.musterdekho.tutorial.service;

import com.musterdekho.tutorial.entity.User;
import com.musterdekho.tutorial.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return  repository.findAll();
    }

    @Override
    public User findUser(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public void DeleteUser(Integer id) {
        repository.deleteById(id);

    }
}
