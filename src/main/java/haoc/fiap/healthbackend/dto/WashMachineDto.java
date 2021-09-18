package haoc.fiap.healthbackend.dto;

import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;


@Data
@Builder
public class WashMachineDto {
    private String location;
    private String date;
    private Integer count;
    private Integer hour;
    private Integer minute;
    private Integer lastHour;
    private Integer lastMinute;
}
