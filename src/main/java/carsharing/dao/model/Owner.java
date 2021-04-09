package carsharing.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("OWNER")
@Data
public class Owner extends Customer{

    @Column(name = "level", nullable = false)
    private int level;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer_id", cascade = CascadeType.ALL)
    private List<Car> cars;
}
