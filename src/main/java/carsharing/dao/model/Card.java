package carsharing.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card extends EntityDetails {

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "term", nullable = false)
    private LocalDateTime term;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
