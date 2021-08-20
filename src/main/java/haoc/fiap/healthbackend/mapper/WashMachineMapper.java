package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.WashMachineDto;
import haoc.fiap.healthbackend.entity.WashMachine;
import lombok.Data;

@Data
public class WashMachineMapper {

    public WashMachineDto washToDto(WashMachine wash){
        return WashMachineDto.builder()
                .location(wash.getLocation())
                .build();
    }
}
