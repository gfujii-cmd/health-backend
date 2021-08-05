package haoc.fiap.healthbackend.resquest;

import lombok.Data;

@Data
public class UserRequest {

    private String name;

    private String lastName;

    private String password;

    private String email;

    private Long jobId;
}
