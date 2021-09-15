package haoc.fiap.healthbackend.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "BADGES")
public class Badge {

    @Id
    @Column(name = "BADGE_ID")
    private Integer id;

    @Column(name = "DESCRIPTION")
    private String description;
}
