package haoc.fiap.healthbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BADGES")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BADGE_ID")
    private Integer id;

    @Column(name = "BADGE_DESCRIPTION")
    private String description;

    @Column(name = "BADGE_LEVEL")
    private Integer level;
}
