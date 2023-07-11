import Entities.Testcase;
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
import java.util.List;

@Named
@ViewScoped
public class TestCasesController implements Serializable {

    private List<Testcase> testcases;
    
    private List<Testcase> filteredTestCases;
    private Testcase selectedTestCase = new Testcase();
    
    


    @Inject
    private TestSystem testSystem;
    private String header ="Testfälle";

//    SYSTEM und DAO Implementieren
    @PostConstruct
    public void init(){
        this.testcases = testSystem.getTestCaseList();
    }

    public TestCasesController() {
    }
//Eventuell kann der getter für das testsystem weg
    public TestSystem getTestSystem() {
        return testSystem;
    }

    public String getHeader() {
        return header;
    }


    public List<Testcase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<Testcase> testcases) {
        this.testcases = testcases;
    }

    public List<Testcase> getFilteredTestCases() {
        return filteredTestCases;
    }

    public void setFilteredTestCases(List<Testcase> filteredTestCases) {
        this.filteredTestCases = filteredTestCases;
    }

    public Testcase getSelectedTestCase() {
        return selectedTestCase;
    }

    public void setSelectedTestCase(Testcase selectedTestCase) {
        this.selectedTestCase = selectedTestCase;
    }

    public void openNew() {
        this.selectedTestCase = new Testcase();
    }
    public void saveTestCase() {
        if (this.selectedTestCase== null) {
            this.selectedTestCase.setId(
                   getNextIndex()

            );
            testSystem.saveTestCase(this.selectedTestCase);
            this.testcases = testSystem.getTestCaseList();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Testlauf hinzugefügt"));
        }
        else {
            testSystem.saveTestCase(this.selectedTestCase);
            this.testcases = testSystem.getTestCaseList();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Testlauf geändert"));

        }

        PrimeFaces.current().executeScript("PF('managetestCaseDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-testCases");
    }
    public void deleteTestCase() {

        testSystem.deleteTestCase(this.selectedTestCase);
        this.selectedTestCase = null;
        this.testcases = testSystem.getTestCaseList();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung gelöscht"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-testCases");
    }

    private int getNextIndex(){
        int highestId;
        List<Integer> ident = new ArrayList<>() ;
        if ( !testcases.isEmpty()){
            for (Testcase tc : this.testcases){
                ident.add(tc.getId());
            }
            highestId = Collections.max(ident);
        }else{
            highestId = 0;
        }
        return highestId+1;
    }




}
