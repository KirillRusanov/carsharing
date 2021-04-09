package carsharing.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
@Data
public class Client extends Customer {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "is_verified")
    private boolean is_verified;

    @OneToMany(mappedBy = "customer_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Deal> deals;

    @OneToMany(mappedBy = "customer_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Card> cards;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "specialist_id")
    private Specialist specialist_id;
}