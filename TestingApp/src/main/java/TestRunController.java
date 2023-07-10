import Entities.Testrun;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class TestRunController implements Serializable {

    private List<Testrun> testRuns;
    private Testrun selectedTestRun = new Testrun();


    @Inject
    private TestSystem testSystem;
    private String header ="Testläufe im Überblick";

    @PostConstruct
    public void init(){
        this.testRuns = testSystem.getTestRunList();
    }

    public TestRunController() {
    }
//Eventuell kann der getter für das testsystem weg
    public TestSystem getTestSystem() {
        return testSystem;
    }

    public String getHeader() {
        return header;
    }
    public List<Testrun> getTestRuns() {
        return testRuns;
    }

    public Testrun getSelectedTestRun() {
        return selectedTestRun;
    }

    public void setSelectedTestRun(Testrun selectedTestRun){
        this.selectedTestRun = selectedTestRun;
    }



    public void openNew() {
        this.selectedTestRun = new Testrun();
    }
    public void saveTestRun() {
        if (this.selectedTestRun== null) {
            this.selectedTestRun.setId(
                   getNextIndex()

            );
            testSystem.saveTestRun(this.selectedTestRun);
            this.testRuns = testSystem.getTestRunList();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Testlauf hinzugefügt"));
        }
        else {
            testSystem.saveTestRun(this.selectedTestRun);
            this.testRuns = testSystem.getTestRunList();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Testlauf geändert"));

        }

        PrimeFaces.current().executeScript("PF('manageTestrunDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-testruns");
    }
    public void deleteTestRun() {

        testSystem.deleteTestRun(this.selectedTestRun);
        this.selectedTestRun = null;
        this.testRuns = testSystem.getTestRunList();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung gelöscht"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-testruns");
    }

    private int getNextIndex(){
        int highestId;
        List<Integer> ident = new ArrayList<>() ;
        if ( !testRuns.isEmpty()){
            for (Testrun tr : this.testRuns){
                ident.add(tr.getId());
            }
            highestId = Collections.max(ident);
        }else{
            highestId = 0;
        }
        return highestId+1;
    }




}
