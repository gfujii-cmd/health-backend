package haoc.fiap.healthbackend.entity;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "JOBS")
public class Job {

    @Id
    @Column(name = "JOB_ID")
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Nullable
    @Column(name = "ENTRY_HOUR")
    private Integer entryHour;

    @Nullable
    @Column(name = "EXIT_HOUR")
    private Integer exitHour;

}
