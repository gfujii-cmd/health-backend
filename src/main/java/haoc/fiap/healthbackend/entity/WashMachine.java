package haoc.fiap.healthbackend.entity;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "WASH_MACHINES")
public class WashMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WASH_ID")
    private Integer id;

    @NotNull
    @Column(name = "LOCATION")
    private String location;

    @Nullable
    @Column(name = "DATE")
    private String date;

    @Nullable
    @Column(name = "HOUR")
    private Integer hour;

    @Nullable
    @Column(name = "MINUTE")
    private Integer minute;

    @Nullable
    @Column(name = "LAST_HOUR")
    private Integer lastHour;

    @Nullable
    @Column(name = "LAST_MINUTE")
    private Integer lastMinute;

    @Nullable
    @Column(name = "COUNT")
    private Integer count;

    @Nullable
    @Column(name = "LAST_USER")
    private String lastUserMail;

}
