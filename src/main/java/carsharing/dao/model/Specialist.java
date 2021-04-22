package carsharing.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "specialist")
public class Specialist extends EntityDetails {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialist")
    private List<Customer> clients;
}
