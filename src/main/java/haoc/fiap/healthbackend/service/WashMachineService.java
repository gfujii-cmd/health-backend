package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.dto.WashMachineDto;
import haoc.fiap.healthbackend.entity.WashMachine;
import haoc.fiap.healthbackend.mapper.WashMachineMapper;
import haoc.fiap.healthbackend.repository.WashMachineRepository;
import haoc.fiap.healthbackend.resquest.WashMachineInfoRequest;
import jdk.vm.ci.meta.Local;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WashMachineService {

    @Autowired
    private WashMachineRepository washMachineRepository;

    private final WashMachineMapper washMachineMapper;

    public WashMachineDto setWashMachineInfo(WashMachineInfoRequest request) throws Exception {
        Optional<WashMachine> washData = getWashMachineInfo(request.getMachineId());

        if(washData.isPresent()) {
            Integer timeInterval = washData.get()
                    .getHour().getMinuteOfHour() - washData.get().getLastHour().getMinuteOfHour();

            if(timeInterval <= 5) {
                throw new Exception("Tempo não passa de 5 minutos!");
            } else {
                WashMachine entity = WashMachine.builder()
                        .count(washData.get().getCount() + 1)
                        .date(LocalDate.now())
                        .hour(LocalTime.now())
                        .lastHour(washData.get().getHour())
                        .location(request.getLocation())
                        .build();

                washMachineRepository.save(entity);

                return washMachineMapper.washToDto(entity);
            }
        }
        throw new Exception("Maquina não encontrada");
    }

    public Optional<WashMachine> getWashMachineInfo(Integer id){
        return washMachineRepository.findById(id);
    }

}
