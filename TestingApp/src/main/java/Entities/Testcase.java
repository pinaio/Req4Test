package Entities;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

@Entity
@Named
@RequestScoped
public class Testcase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "ExpectedResult")
    private String expectedResult;
    @Basic
    @Column(name = "ObservedResult")
    private String observedResult;
    @Basic
    @Column(name = "FailOrPass")
    private String failOrPass;
    @Basic
    @Column(name="Title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "Testrun_id", referencedColumnName = "ID")
    private Testrun testrunsByTestrunId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getObservedResult() {
        return observedResult;
    }

    public void setObservedResult(String observedResult) {
        this.observedResult = observedResult;
    }

    public String getFailOrPass() {
        return failOrPass;
    }

    public void setFailOrPass(String failOrPass) {
        this.failOrPass = failOrPass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Testrun getTestrunsByTestrunId() {
        return testrunsByTestrunId;
    }

    public void setTestrunsByTestrunId(Testrun testrunsByTestrunId) {
        this.testrunsByTestrunId = testrunsByTestrunId;
    }
}
