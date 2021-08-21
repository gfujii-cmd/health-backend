package haoc.fiap.healthbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "WASH_MACHINES")
public class WashMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WASH_ID")
    private Integer id;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "COUNT")
    private Integer count;
}
