package Entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
public class Testruns {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "Deadline")
    private Date deadline;
    @Basic
    @Column(name = "CreationDate")
    private Date creationDate;
    @Basic
    @Column(name = "Creator")
    private String creator;
    @Basic
    @Column(name = "Tester")
    private String tester;


    @OneToMany(mappedBy = "testrunsByTestrunId")
    private Collection<Testcases> testcasesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Collection<Testcases> getTestcasesById() {
        return testcasesById;
    }

    public void setTestcasesById(Collection<Testcases> testcasesById) {
        this.testcasesById = testcasesById;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }
}
