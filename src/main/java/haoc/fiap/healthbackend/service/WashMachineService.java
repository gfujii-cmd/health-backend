package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.dto.WashMachineDto;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.entity.WashMachine;
import haoc.fiap.healthbackend.mapper.WashMachineMapper;
import haoc.fiap.healthbackend.repository.UserRepository;
import haoc.fiap.healthbackend.repository.WashMachineRepository;
import haoc.fiap.healthbackend.resquest.WashMachineInfoRequest;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WashMachineService {

    @Autowired
    private WashMachineRepository washMachineRepository;

    @Autowired
    private UserRepository userRepository;

    public WashMachineDto setWashMachineInfo(WashMachineInfoRequest request) throws Exception {
        Optional<WashMachine> washData = getWashMachineInfo(request.getMachineId());
        Optional<User> user = userRepository.findById(request.getUserId());

        if(user.isPresent()) {
            if (washData.isPresent()) {
                // Aceitacao de intervalo
                Boolean isIntervalAccepted = isIntervalAccepted(LocalTime.now().getHourOfDay(),
                        LocalTime.now().getMinuteOfHour(), washData.get().getLastHour(),
                        washData.get().getLastMinute(), washData.get().getDate());

                // Checa para ver se é a mesma pessoa que vem no request com o banco
                Boolean isSamePerson = Objects.equals(washData.get().getLastUserMail(), user.get().getEmail());

                if (isSamePerson && !isIntervalAccepted) {
                    throw new Exception("Tempo não passa de 5 minutos para a mesma pessoa!");
                } else {
                    // Criando nova maquina com dados novos
                    WashMachine machineEntity = setNewEntity(washData.get(), user.get().getEmail());

                    // Setando a nova wash machine do usuario
                    user.get().setWashMachine(machineEntity);

                    // Salvando no banco
                    washMachineRepository.save(machineEntity);
                    userRepository.save(user.get());

                    return WashMachineMapper.washToDto(machineEntity);
                }
            }
        }
        throw new Exception("Maquina não encontrada");
    }

    public Optional<WashMachine> getWashMachineInfo(Integer id){
        return washMachineRepository.findById(id);
    }

    public WashMachineDto createWashMachine(String location) throws Exception{
        WashMachine entity = WashMachine.builder()
                .count(0)
                .location(location)
                .hour(LocalTime.now().getHourOfDay())
                .date(LocalDate.now().toString())
                .minute(LocalTime.now().getMinuteOfHour())
                .lastHour(LocalTime.now().getHourOfDay())
                .lastMinute(LocalTime.now().getMinuteOfHour())
                .build();
        try {
            return WashMachineMapper.washToDto(washMachineRepository.save(entity));
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao salvar os dados", e);
        }
    }

    public WashMachine setNewEntity(WashMachine entity, String userMail){
        WashMachine washMachineEntity = entity;
        washMachineEntity.setCount(washMachineEntity.getCount() + 1);
        washMachineEntity.setDate(LocalDate.now().toString());
        washMachineEntity.setHour(LocalTime.now().getHourOfDay());
        washMachineEntity.setMinute(LocalTime.now().getMinuteOfHour());
        washMachineEntity.setLastHour(LocalTime.now().getHourOfDay());
        washMachineEntity.setLastMinute(LocalTime.now().getMinuteOfHour());
        washMachineEntity.setLastUserMail(userMail);

        return washMachineEntity;
    }

    public Boolean isIntervalAccepted(Integer hour, Integer minute, Integer lastHour, Integer lastMinute, String date) {
        Boolean isAfterNowDate = LocalDate.now().isAfter(LocalDate.parse(date));
        if(isAfterNowDate && lastHour > hour) {
            return true;
        } else {
            Integer lastTime = (lastHour * 60) + lastMinute;
            Integer nowTime = (hour * 60) + minute;

            return nowTime - lastTime <= 5 ? false : true;

        }
    }
}
