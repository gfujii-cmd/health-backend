package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.dto.WashMachineDto;
import haoc.fiap.healthbackend.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserMapper {

    private static JobMapper jobMapper;

    private static WashMachineMapper washMachineMapper;

    public static UserDto userToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
//                .job(JobMapper.jobToDto(user.getJob()))
                .lastName(user.getLastName())
                .name(user.getName())
                .score(user.getScore())
                .washData(new ArrayList<>())
                .build();
    }
}
