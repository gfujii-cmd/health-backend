package haoc.fiap.healthbackend.resquest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WashMachineInfoRequest {

    private String userRfId;

    private String machineMacAddress;
}
