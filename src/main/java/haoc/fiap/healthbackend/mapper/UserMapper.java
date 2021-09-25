package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.dto.WashMachineDto;
import haoc.fiap.healthbackend.entity.Badge;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.resquest.UserRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                .job(JobMapper.jobToDto(user.getJob()))
                .lastName(user.getLastName())
                .name(user.getName())
                .score(user.getScore())
                .washData(new ArrayList<>())
                .createdAt(DateTime.now().toString())
                .updatedAt(DateTime.now().toString())
                .count(user.getCount())
                .level(user.getBadge().getLevel())
                .build();
    }

    public static User toUser(UserRequest request, Badge badge) {
        return User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .job(request.getJob())
                .score(0L)
                .entryHour(0)
                .exitHour(0)
                .createdAt(DateTime.now().toString())
                .updatedAt(DateTime.now().toString())
                .count(0)
                .badge(badge)
                .build();
    }
}
