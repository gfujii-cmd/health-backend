package haoc.fiap.healthbackend.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeDto {

    private String description;

    private Integer level;
}
