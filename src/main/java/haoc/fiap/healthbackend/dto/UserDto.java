package haoc.fiap.healthbackend.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String name;

    private String lastName;

    private String email;

    private Long score;

    private List<WashMachineDto> washData;

    private JobDto job;
}
