package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.dto.TopDto;
import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.response.BaseResponse;
import haoc.fiap.healthbackend.response.TokenResponse;
import haoc.fiap.healthbackend.resquest.LoginRequest;
import haoc.fiap.healthbackend.resquest.UserRequest;
import haoc.fiap.healthbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserDto>> createUser(@RequestBody UserRequest request)
            throws Exception {
        return ResponseEntity.ok(BaseResponse.<UserDto>builder()
                        .response(userService.registerUser(request))
                        .httpCode(200)
                        .message("Usuário criado com sucesso")
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authUser(@RequestBody LoginRequest request) {
        this.authenticate(request);
        return ResponseEntity.ok(TokenResponse.builder().token(this.userService.getToken(request)).build());
    }

    @GetMapping("/{email}")
    public ResponseEntity<BaseResponse<UserDto>> getUserByEmail(@PathVariable("email") String email) {
        UserDto user = userService.findByEmail(email);
        return ResponseEntity.ok(BaseResponse.<UserDto>builder()
                .message("OK")
                .response(user)
                .httpCode(HttpStatus.OK.value())
                .build());
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

    @GetMapping("/{id}/score")
    public ResponseEntity<BaseResponse<Integer>> getUserScore(@PathVariable("id") Integer id) throws Exception{
        Integer score = userService.getUserScore(id);

        return ResponseEntity.ok(BaseResponse.<Integer>builder()
                        .response(score)
                        .message("Score retornado com sucesso")
                        .httpCode(200)
                .build());
    }

    @GetMapping("/top")
    public ResponseEntity<BaseResponse<List<TopDto>>> getTopList() throws Exception {
        return ResponseEntity.ok(BaseResponse.<List<TopDto>>builder()
                        .httpCode(200)
                        .message("OK")
                        .response(userService.getTopList())
                .build());
    }

    @PutMapping("/{email}/set-rfid/{rfid}")
    public ResponseEntity<BaseResponse<UserDto>> setRfid(@PathVariable("email") String email,
                                                         @PathVariable("rfid") String rfid) throws Exception {
        return ResponseEntity.ok(BaseResponse.<UserDto>builder()
                        .httpCode(200)
                        .message("Cadastrado com sucesso")
                        .response(userService.setRfid(email, rfid))
                .build());
    }

}
