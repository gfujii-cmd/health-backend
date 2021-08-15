package haoc.fiap.healthbackend.entity;

import com.sun.istack.NotNull;
import haoc.fiap.healthbackend.dto.JobDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "")
    private String name;

    @NotNull
    @Column(name = "")
    private String lastName;

    @NotNull
    @Column(name = "")
    private String email;

    @NotNull
    @Column(name = "")
    private String password;

    @NotNull
    @Column(name = "")
    private Long score;

    @NotNull
    @ManyToOne
    @Column(name = "")
    private Long washDataId;

    @NotNull
    @ManyToOne
    @Column(name = "")
    private JobDto job;

}
