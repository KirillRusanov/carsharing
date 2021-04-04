package carsharing.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "passportNumber", nullable = false)
    private String passportNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner_id", cascade = CascadeType.ALL)
    private List<Car> cars;
}
