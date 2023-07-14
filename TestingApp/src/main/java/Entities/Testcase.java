package Entities;

import jakarta.persistence.*;

@Entity
public class Testcase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;

    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "ExpectedResult")
    private String expectedResult;
    @Basic
    @Column(name = "FailOrPass")
    private String failOrPass;
    @Basic
    @Column(name = "ObservedResult")
    private String observedResult;
    @Basic
    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "Testrun_id", referencedColumnName = "ID")
    private Testrun testrunByTestrunId;
    @ManyToOne
    @JoinColumn(name = "requirement_id", referencedColumnName = "ID")
    private Requirement requirementByRequirementId;


    public Testcase() {
    }

    public Testcase(Long id, String description, String expectedResult, String failOrPass, String observedResult, String title, Testrun testrunByTestrunId, Requirement requirementByRequirementId) {
        this.id = id;
        this.description = description;
        this.expectedResult = expectedResult;
        this.failOrPass = failOrPass;
        this.observedResult = observedResult;
        this.title = title;
        this.testrunByTestrunId = testrunByTestrunId;
        this.requirementByRequirementId = requirementByRequirementId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getFailOrPass() {
        return failOrPass;
    }

    public void setFailOrPass(String failOrPass) {
        this.failOrPass = failOrPass;
    }

    public String getObservedResult() {
        return observedResult;
    }

    public void setObservedResult(String observedResult) {
        this.observedResult = observedResult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Testrun getTestrunByTestrunId() {
        return testrunByTestrunId;
    }

    public void setTestrunByTestrunId(Testrun testrunByTestrunId) {
        this.testrunByTestrunId = testrunByTestrunId;
    }

    public Requirement getRequirementByRequirementId() {
        return requirementByRequirementId;
    }

    public void setRequirementByRequirementId(Requirement requirementByRequirementId) {
        this.requirementByRequirementId = requirementByRequirementId;
    }
}
