package Entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    @Basic
    @Column(name = "Username")
    private String username;
    @Basic
    @Column(name = "Firstname")
    private String firstname;
    @Basic
    @Column(name = "Lastname")
    private String lastname;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "Role")
    private String role;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;
    @OneToMany(mappedBy = "userById", cascade = {CascadeType.ALL})
    private Collection<Testrun> testrunsById;


    public User() {
    }

    public User(String username, String firstname, String lastname, String password, String role, Long id) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Testrun> getTestrunsById() {
        return testrunsById;
    }

    public void setTestrunsById(Collection<Testrun> testrunsById) {
        this.testrunsById = testrunsById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
