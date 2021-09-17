package haoc.fiap.healthbackend.entity;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "USERS")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "SCORE")
    private Long score;

    @Nullable
    @Column(name = "ENTRY_HOUR")
    private Integer entryHour;

    @Nullable
    @Column(name = "EXIT_HOUR")
    private Integer exitHour;

    @Nullable
    @Column(name = "COUNT")
    private Integer count;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @Nullable
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "WASH_ID")
    private WashMachine washMachine;

    @Nullable
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "BADGE_ID")
    private Badge badge;

    @NotNull
    @Column(name = "CREATED_AT")
    private String createdAt;

    @Nullable
    @Column(name = "UPDATED_AT")
    private String updatedAt;
}
