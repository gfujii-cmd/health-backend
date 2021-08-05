package haoc.fiap.healthbackend.response;

import haoc.fiap.healthbackend.dto.UserDto;
import lombok.Data;

@Data
public class UserResponse {
    private Long code;
    private UserDto response;
    private String error;
}
