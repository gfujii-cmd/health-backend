package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.WashMachineDto;
import haoc.fiap.healthbackend.entity.WashMachine;
import haoc.fiap.healthbackend.resquest.WashMachineInfoRequest;
import lombok.Data;

import java.util.Date;

@Data
public class WashMachineMapper {

    public WashMachineDto washToDto(WashMachine wash){
        return WashMachineDto.builder()
                .location(wash.getLocation())
                .date(wash.getDate())
                .count(wash.getCount())
                .hour(wash.getHour())
                .lastHour(wash.getLastHour())
                .build();
    }
}
