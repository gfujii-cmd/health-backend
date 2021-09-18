package haoc.fiap.healthbackend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDto {
    private Integer jobId;
    private String name;
}
