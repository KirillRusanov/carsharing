package carsharing.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "rate")
public class Rate extends EntityDetails {

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RateType type;

    @Column(name = "cost", nullable = false)
    private long cost;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rate")
    private List<Car> cars;
}
