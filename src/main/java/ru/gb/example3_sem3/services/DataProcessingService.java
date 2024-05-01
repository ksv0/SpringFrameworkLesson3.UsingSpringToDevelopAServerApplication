package ru.gb.example3_sem3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.example3_sem3.domain.User;
import ru.gb.example3_sem3.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    @Autowired
    private UserRepository repository;

    public UserRepository getRepository() {
        return repository;
    }

    public void addUser(User user) {
        repository.addUser(user);
    }

    public List<User> sortUsersByAge() {


        return repository.sortUserByAge();
    }

    public List<User> filterUsersByAge(int age) {
        return repository.filterUserByAge(age);
    }

    public double calculateAverageAge(List<User> users) {
        return repository.averageAgeUser();
    }
}