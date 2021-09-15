package haoc.fiap.healthbackend.entity;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
}
