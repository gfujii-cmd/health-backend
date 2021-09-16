package haoc.fiap.healthbackend.resquest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WashMachineInfoRequest {

    private Integer userId;

    private Integer machineId;

    private String location;
}
