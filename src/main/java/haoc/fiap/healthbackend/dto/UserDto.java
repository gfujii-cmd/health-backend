package haoc.fiap.healthbackend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String name;

    private String lastName;

    private String email;

    private Long score;

    private WashMachineDto washDataId;

    private JobDto job;
}
