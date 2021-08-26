package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.mapper.UserMapper;
import haoc.fiap.healthbackend.repository.UserRepository;
import haoc.fiap.healthbackend.resquest.LoginRequest;
import haoc.fiap.healthbackend.resquest.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import haoc.fiap.healthbackend.jwt.JwtUtil;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtil jwtUtil;

    @Autowired
    private UserRepository repository;

    public User registerUser(UserRequest user){
        return repository.save(UserMapper.toUser(user));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), new ArrayList<>());
    }

    public String getToken(LoginRequest request) {
        return this.jwtUtil.generateToken(repository.findByEmail(request.getEmail()));
    }
}
