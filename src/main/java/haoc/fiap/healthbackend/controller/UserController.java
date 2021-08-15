package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.mapper.UserMapper;
import haoc.fiap.healthbackend.response.BaseResponse;
import haoc.fiap.healthbackend.response.ErrorResponse;
import haoc.fiap.healthbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public BaseResponse registerUser(@RequestBody User user) {
        String userEmail = user.getEmail();
        // encoding password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if(userEmail != null && !"".equals(userEmail)){
            User dataUser = userService.findByEmail(userEmail);
            if(dataUser != null){
                return BaseResponse.builder()
                        .httpCode(409)
                        .message("User with email " + userEmail + " already exists")
                        .response(null)
                        .build();
            }
        }
        try {
            User newUser = userService.registerUser(user);
            if(newUser != null){
                return BaseResponse.builder()
                        .httpCode(200)
                        .response(userMapper.userToDto(newUser))
                        .message("User " + newUser.getName() + " created with success!")
                        .build();
            }
        } catch (Error e) {
            ErrorResponse error = ErrorResponse.builder()
                    .error(e.getMessage())
                    .httpCode(500)
                    .message(e.getLocalizedMessage())
                    .build();

            return BaseResponse.builder()
                    .httpCode(error.getHttpCode())
                    .message("Um erro ocorreu")
                    .response(error)
                    .build();
        }
        return null;
    }
}