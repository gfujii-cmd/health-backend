package haoc.fiap.healthbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WashMachineDto {
    private String location;
}
