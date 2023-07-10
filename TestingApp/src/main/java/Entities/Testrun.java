package Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Testrun {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "CreationDate")
    private Timestamp creationDate;
    @Basic
    @Column(name = "Deadline")
    private Timestamp deadline;
    @Basic
    @Column(name = "Creator")
    private String creator;
    @Basic
    @Column(name = "Status")
    private String status;
    @Basic
    @Column(name = "Title")
    private String title;
    @OneToMany(mappedBy = "testrunByTestrunId")
    private Collection<Testcase> testcasesById;
    @ManyToOne
    @JoinColumn(name = "Tester", referencedColumnName = "ID")
    private User userById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Testcase> getTestcasesById() {
        return testcasesById;
    }

    public void setTestcasesById(Collection<Testcase> testcasesById) {
        this.testcasesById = testcasesById;
    }

    public User getUserByTester() {
        return userById;
    }

    public void setUserByTester(User userById) {
        this.userById = userById;
    }
}
