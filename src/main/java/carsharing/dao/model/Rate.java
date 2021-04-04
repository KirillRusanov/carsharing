package carsharing.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RateType type;

    @Column(name = "cost", nullable = false)
    private long cost;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rate_id", cascade = CascadeType.ALL)
    private List<Deal> deals;
}