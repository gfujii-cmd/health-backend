package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.mapper.UserMapper;
import haoc.fiap.healthbackend.response.BaseResponse;
import haoc.fiap.healthbackend.response.ErrorResponse;
import haoc.fiap.healthbackend.response.TokenResponse;
import haoc.fiap.healthbackend.resquest.LoginRequest;
import haoc.fiap.healthbackend.resquest.UserRequest;
import haoc.fiap.healthbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> createUser(@RequestBody UserRequest request) {
        userService.registerUser(request);
        return authUser(LoginRequest.builder().email(request.getEmail()).password(request.getPassword()).build());
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authUser(@RequestBody LoginRequest request) {
        this.authenticate(request);
        return ResponseEntity.ok(TokenResponse.builder().token(this.userService.getToken(request)).build());
    }

    private void authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

}
