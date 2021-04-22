package carsharing.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Data
@Table(name = "card")
public class Card extends EntityDetails {

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "term", nullable = false)
    private Date term;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
