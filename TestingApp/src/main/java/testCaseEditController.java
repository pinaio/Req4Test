import Entities.Requirement;
import Entities.Testcase;
import Entities.Testrun;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;


import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("tce")
@RequestScoped
public class testCaseEditController  implements Serializable {

    @Inject
    private TestSystem testSystem;
    private Testcase selectedTestCase;





    private List<Testrun> testruns = new ArrayList<Testrun>();
    private List<Requirement> requirements = new ArrayList<Requirement>();

    private String header = "Testfall bearbeiten";





    public testCaseEditController() {
    }

    @PostConstruct
    public void init(){
        this.testruns = testSystem.getTestRunList();
        this.requirements= testSystem.getReqList();


    }

    public void setSelectedTestCase(Testcase selectedTestCase) {
        this.selectedTestCase = selectedTestCase;
    }

    public Testcase getSelectedTestCase() {
        return selectedTestCase;
    }

    public List<Testrun> getTestruns() {
        return testruns;
    }

    public void setTestruns(List<Testrun> testruns) {
        this.testruns = testruns;
    }


    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public String getHeader() {
        return header;
    }

    public String saveTestCase() {
            System.out.println(this.selectedTestCase.getTestrunByTestrunId().getTitle());
            System.out.println(this.selectedTestCase.getDescription());
            System.out.println(this.selectedTestCase.getRequirementByRequirementId().getDescription());

            testSystem.saveTestCase(this.selectedTestCase);


        return "testCasesEdit.xhtml?faces-redirect=true";
    }








}
