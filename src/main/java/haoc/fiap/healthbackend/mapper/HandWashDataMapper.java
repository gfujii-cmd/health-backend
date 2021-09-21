package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.HandWashDataDto;
import haoc.fiap.healthbackend.entity.HandWashData;

public class HandWashDataMapper {

    public static HandWashDataDto toDto(HandWashData handWashData){
        return HandWashDataDto.builder()
                .countMonday(handWashData.getCountMonday())
                .countFriday(handWashData.getCountFriday())
                .countSaturday(handWashData.getCountSaturday())
                .countSunday(handWashData.getCountSunday())
                .countThursday(handWashData.getCountThursday())
                .countTuesday(handWashData.getCountTuesday())
                .countWednesday(handWashData.getCountWednesday())
                .month(handWashData.getMonth())
                .build();
    }
}
