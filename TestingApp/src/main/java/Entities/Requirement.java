package Entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Requirement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;
    @Basic
    @Column(name = "Author")
    private String author;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Status")
    private String status;
    @OneToMany(mappedBy = "requirementByRequirementId", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Collection<Testcase> testcasesById;

    public Requirement() {
    }

    public Requirement(Long id, String author, String description, String name, String status) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Testcase> getTestcasesById() {
        return testcasesById;
    }

    public void setTestcasesById(Collection<Testcase> testcasesById) {
        this.testcasesById = testcasesById;
    }
}
