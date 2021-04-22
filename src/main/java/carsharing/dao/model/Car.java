package carsharing.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
@Table(name = "car")
public class Car extends EntityDetails {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "car_type", nullable = false)
    private CarType type;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "car_status", nullable = false)
    private  CarStatus car_status;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "fuel", nullable = false)
    private int fuel;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    private Rate rate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<Deal> deals;
}
