import Entities.Testcase;
import Entities.Testrun;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.primefaces.component.cache.UICacheBase.PropertyKeys.key;


@ViewScoped
@Named
public class RunTestRunController implements Serializable {

    @Inject
    private TestSystem testSystem;
    private String header = "Test durchführen";
    private Testrun selectedTestRun;
    private List<Testcase> testcases;

    private String status;

    public RunTestRunController() {
    }

    @PostConstruct
    public void init(){}


    public Testrun getSelectedTestRun() {
        return selectedTestRun;
    }

    public void setSelectedTestRun(Testrun selectedTestRun) {
        this.selectedTestRun = selectedTestRun;
    }

    public List<Testcase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<Testcase> testcases) {
        this.testcases = testcases;
    }

    public String getHeader() {
        return header;
    }

    public void saveTestRun(){
        this.status = "Abgeschlossen";
        for(Testcase tc:this.selectedTestRun.getTestcasesById()){
            if(tc.getFailOrPass().equals( "pass")){
                continue;
            } else if (tc.getFailOrPass().equals( "fail")) {
                status ="Defekt entdeckt";

            } else{
                status = "Offen";
                break;
            }
        }
        this.selectedTestRun.setStatus(status);
        testSystem.saveTestRun(this.selectedTestRun);

    }



    public String goBack(){
        return "testRunView.xhtml";
    }
}
