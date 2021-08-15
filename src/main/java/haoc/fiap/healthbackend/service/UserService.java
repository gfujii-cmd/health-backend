package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User registerUser(User user){
        return repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
