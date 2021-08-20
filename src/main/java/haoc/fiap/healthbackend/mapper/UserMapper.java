package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class UserMapper {

    private JobMapper jobMapper;

    private WashMachineMapper washMachineMapper;

    public UserDto userToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .job(jobMapper.jobToDto(user.getJob()))
                .lastName(user.getLastName())
                .name(user.getName())
                .score(user.getScore())
                .washDataId(washMachineMapper.washToDto(user.getWashDataId()))
                .build();
    }
}
