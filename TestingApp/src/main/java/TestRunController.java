import Entities.Testcase;
import Entities.Testrun;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

@Named
@ViewScoped
public class TestRunController implements Serializable {

    private List<Testrun> testRuns;
    private Testrun selectedTestRun = new Testrun();

    private List<Testcase> testcases = new ArrayList<Testcase>();
    private List<Testcase> unasignedTC = new ArrayList<Testcase>();
    private List<Testcase> asignedTC = new ArrayList<Testcase>();

    private DualListModel<Testcase> dlTestcases;




    @Inject
    private TestSystem testSystem;
    private String header ="Testläufe im Überblick";

    @PostConstruct
    public void init(){
        this.testRuns = testSystem.getTestRunList();
        this.testcases = testSystem.getTestCaseList();
        for(Testcase tc : this.testcases){
            if(tc.getTestrunByTestrunId() == null){
                this.unasignedTC.add(tc);
            }
        }
        dlTestcases = new DualListModel<>(unasignedTC,asignedTC);

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
        for(Testcase tc : this.testcases){
            if(tc.getTestrunByTestrunId() == selectedTestRun){
                this.asignedTC.add(tc);
            }
        }

    }

    public DualListModel<Testcase> getDlTestcases() {
        return dlTestcases;
    }

    public void setDlTestcases(DualListModel<Testcase> dlTestcases) {
        this.dlTestcases = dlTestcases;
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

    private Long getNextIndex(){
        Long highestId;
        List<Long> ident = new ArrayList<>() ;
        if ( !testRuns.isEmpty()){
            for (Testrun tr : this.testRuns){
                ident.add(tr.getId());
            }
            highestId = Collections.max(ident);
        }else{
            highestId = 0L;
        }
        return highestId+1;
    }




}
