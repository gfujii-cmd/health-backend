package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.entity.User;
import lombok.Data;

@Data
public class UserMapper {

    public UserDto userToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .job(user.getJob())
                .lastName(user.getLastName())
                .name(user.getName())
                .score(user.getScore())
                .washDataId(user.getWashDataId())
                .build();
    }
}
