package carsharing.dao.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role extends EntityDetails implements GrantedAuthority {

    @Column(name = "name", nullable = false)
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers = new HashSet<>();

    @Override
    public String getAuthority() {
        return getName();
    }
}
