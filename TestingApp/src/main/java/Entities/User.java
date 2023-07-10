package Entities;

import jakarta.persistence.*;

import java.util.Collection;

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
    @Column(name = "Role")
    private String role;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @OneToMany(mappedBy = "userById")
    private Collection<Testrun> testrunsById;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Testrun> getTestrunsById() {
        return testrunsById;
    }

    public void setTestrunsById(Collection<Testrun> testrunsById) {
        this.testrunsById = testrunsById;
    }
}
