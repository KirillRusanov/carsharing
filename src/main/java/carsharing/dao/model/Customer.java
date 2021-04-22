package carsharing.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
public class Customer extends EntityDetails implements UserDetails {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "passport_number", unique = true)
    private String passport_number;

    @Column(name = "license_number", unique = true)
    private String license_number;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phone_number;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name ="password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Car> cars;

    @Column(name = "is_verified")
    private boolean is_verified;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Deal> deals;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Card> cards;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialist_id")
    private Specialist specialist;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_role",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
