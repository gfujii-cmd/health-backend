package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.BadgeDto;
import haoc.fiap.healthbackend.entity.Badge;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BadgeMapper {

    public static BadgeDto badgeToDto(Badge badge){
        return BadgeDto.builder()
                .level(badge.getLevel())
                .description(badge.getDescription())
                .build();
    }

    public static List<BadgeDto> badgeDtoToDtoList(List<Badge> badges){
        return badges.stream()
                .map(BadgeMapper::badgeToDto)
                .collect(Collectors.toList());
    }
}
