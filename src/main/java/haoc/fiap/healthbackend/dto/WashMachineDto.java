package haoc.fiap.healthbackend.dto;

import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;


@Data
@Builder
public class WashMachineDto {
    private String location;
    private LocalDate date;
    private Integer count;
    private LocalTime hour;
    private LocalTime lastHour;
}
