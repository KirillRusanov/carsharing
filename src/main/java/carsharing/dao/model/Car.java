package carsharing.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "car_type", nullable = false)
    private CarType car_type;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "car_status", nullable = false)
    private  CarStatus status;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "fuel", nullable = false)
    private int fuel;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_id")
    private Rate rate_id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car_id", cascade = CascadeType.ALL)
    private List<Deal> deals;
}
