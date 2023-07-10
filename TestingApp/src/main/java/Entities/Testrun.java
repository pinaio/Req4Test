package Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Collection;

@Entity
public class Testrun {
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

    @Basic
    @Column(name = "Status")
    private String status;


    @OneToMany(mappedBy = "testrunsByTestrunId")
    private Collection<Testcase> testcasesById;

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
        this.creationDate =creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Collection<Testcase> getTestcasesById() {
        return testcasesById;
    }

    public void setTestcasesById(Collection<Testcase> testcasesById) {
        this.testcasesById = testcasesById;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
