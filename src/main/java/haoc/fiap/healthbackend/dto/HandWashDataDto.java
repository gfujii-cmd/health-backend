package haoc.fiap.healthbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HandWashDataDto {

    private Integer countMonday;

    private Integer countTuesday;

    private Integer countWednesday;

    private Integer countThursday;

    private Integer countFriday;

    private Integer countSaturday;

    private Integer countSunday;

    private String month;
}
