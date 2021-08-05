package haoc.fiap.healthbackend.entity;

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
    private Long id;

    @Column(name = "")
    private String name;

    @Column(name = "")
    private String lastName;

    @Column(name = "")
    private String email;

    @Column(name = "")
    private String password;

    @Column(name = "")
    private Long score;

    @ManyToOne
    @Column(name = "")
    private Long washDataId;

    @ManyToOne
    @Column(name = "")
    private JobDto job;

}
