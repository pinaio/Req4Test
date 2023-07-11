package Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Testrun {

    public Testrun() {
    }

    public Testrun(int id, Date deadline, String creator, String status, String title) {
        this.id = id;
        this.deadline = deadline;
        this.creator = creator;
        this.status = status;
        this.title = title;
    }

    public Testrun(int id, Date deadline, String creator, String status, String title, User userById) {
        this.id = id;
        this.deadline = deadline;
        this.creator = creator;
        this.status = status;
        this.title = title;
        this.userById = userById;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @CreationTimestamp
    @Column(name = "CreationDate")
    private Timestamp creationDate;
    @Basic
    @Column(name = "Deadline")
    private Date deadline;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
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
