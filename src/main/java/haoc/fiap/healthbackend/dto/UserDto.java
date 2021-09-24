package haoc.fiap.healthbackend.dto;

import lombok.*;
import org.joda.time.DateTime;

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

    private String createdAt;

    private String updatedAt;

    private Integer count;

    private Integer entryHour;

    private Integer exitHour;
}
