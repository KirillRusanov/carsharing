package carsharing.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "specialist")
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialist_id", cascade = CascadeType.ALL)
    private List<Customer> customers;
}