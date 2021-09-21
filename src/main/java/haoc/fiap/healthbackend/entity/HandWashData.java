package haoc.fiap.healthbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "HAND_WASH_DATA")
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HandWashData implements Serializable {

    private static final long serialVersionUID = 310702915706565426L;

    @Id
    @Column(name = "USER_ID")
    private Integer userId;

    @OneToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "COUNT_MONDAY")
    private Integer countMonday;

    @Column(name = "COUNT_TUESDAY")
    private Integer countTuesday;

    @Column(name = "COUNT_WEDNESDAY")
    private Integer countWednesday;

    @Column(name = "COUNT_THURSDAY")
    private Integer countThursday;

    @Column(name = "COUNT_FRIDAY")
    private Integer countFriday;

    @Column(name = "COUNT_SATURDAY")
    private Integer countSaturday;

    @Column(name = "COUNT_SUNDAY")
    private Integer countSunday;

    @Column(name = "MONTH")
    private String month;
}
