package carsharing.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends EntityDetails implements UserDetails {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "passport_number", unique = true)
    private String passportNumber;

    @Column(name = "license_number", unique = true)
    private String licenseNumber;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "balance")
    private BigDecimal balance;

    @JsonIgnore
    @Column(name ="password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Car> cars;

    @Column(name = "is_verified")
    private boolean isVerified;

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
