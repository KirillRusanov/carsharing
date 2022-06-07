package carsharing.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class Car extends EntityDetails {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "car_type", nullable = false)
    private CarType type;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "car_status", nullable = false)
    private  CarStatus carStatus;

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

    @Column(name = "posX")
    private BigDecimal posX;
    @Column(name = "posY")
    private BigDecimal posY;

}
