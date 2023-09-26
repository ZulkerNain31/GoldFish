package com.goldFish.entity;

import com.goldFish.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_Registration")
@SuppressWarnings(value = "Unchecked")
public class UserRegistration implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_Name",  nullable = false)
    private String firstName;


    @Column(name = "last_Name",  nullable = false)
    private String lastName;

    @Column(name = "date_of_birth",  nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "email",  nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password",  nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",  nullable = false)
    private Role role;

    @Column(name = "contact",  nullable = false, unique = true, length = 10)
    private Long contact;

    @Column(name = "enabled",  nullable = false)
    private Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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


//    @OneToMany(targetEntity = Book.class, mappedBy = "studentRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Book> book = new HashSet<>(); //Hashset because duplicate book are not to be assigned

}
