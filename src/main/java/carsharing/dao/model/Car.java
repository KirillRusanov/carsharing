package carsharing.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "carType", nullable = false)
    private CarType carType;

    @Column(name = "carStatus", nullable = false)
    private  CarStatus status;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "fuel", nullable = false)
    private int fuel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner_id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car_id", cascade = CascadeType.ALL)
    private List<Deal> deals;
}
