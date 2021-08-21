package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.mapper.UserMapper;
import haoc.fiap.healthbackend.response.BaseResponse;
import haoc.fiap.healthbackend.response.ErrorResponse;
import haoc.fiap.healthbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    private static UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public BaseResponse registerUser(@RequestBody User user) {
        String userEmail = user.getEmail();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

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
            user.setScore(0L);
            User newUser = userService.registerUser(user);
            if(newUser != null){
                return BaseResponse.<UserDto>builder()
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
