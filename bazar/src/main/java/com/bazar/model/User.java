package com.bazar.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NotEmpty
    @Column(nullable = false)
    private String firstName;
    private String lastName;
//    @Email(message = "{error invalid email}")
    @Column(nullable = false, unique = true)
//    @NotEmpty
    private String email;

    private String password;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "Id")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "Id")})

    private List<Role> roles;

    public User(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
    public User(){

    }
}