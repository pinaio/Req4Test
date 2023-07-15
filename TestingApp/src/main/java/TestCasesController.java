import Entities.Requirement;
import Entities.Testcase;
import Entities.Testrun;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.event.SelectEvent;
import org.primefaces.util.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class TestCasesController implements Serializable {

    private List<Testcase> testcases;
    private Long selectedReqIndex;

    private Long selectedTrIndex;
    
    private List<Testcase> filteredTestCases;
    private Testcase selectedTestCase = new Testcase();

    private List<Testrun> testruns = new ArrayList<Testrun>();
    private List<Requirement> requirements = new ArrayList<Requirement>();
    
    private List<String> reqStrings = new ArrayList<String>();
    private List<String> trStrings = new ArrayList<String>();
    private String selectedReqString;

    private String selectedTestRunString;


    @Inject
    private TestSystem testSystem;
    private String header ="Testfälle";

//    SYSTEM und DAO Implementieren
    @PostConstruct
    public void init(){
        this.testcases = testSystem.getTestCaseList();
        this.testruns = testSystem.getTestRunList();
        this.requirements= testSystem.getReqList();

        for(Requirement req: this.requirements){
            reqStrings.add(req.getName());
        }

        for (Testrun tr: this.testruns){
            trStrings.add(tr.getTitle());
        }


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


    public Long getSelectedReqIndex() {
        return selectedReqIndex;
    }

    public void setSelectedReqIndex(Long selectedReqIndex) {
        this.selectedReqIndex = selectedReqIndex;
    }

    public void setSelectedTrIndex(Long selectedTrIndex) {
        this.selectedTrIndex = selectedTrIndex;
    }

    public List<String> getReqStrings() {
        return reqStrings;
    }

    public void setReqStrings(List<String> reqStrings) {
        this.reqStrings = reqStrings;
    }

    public List<String> getTrStrings() {
        return trStrings;
    }

    public void setTrStrings(List<String> trStrings) {
        this.trStrings = trStrings;
    }

    public String getSelectedReqString() {
        return selectedReqString;
    }

    public Long getSelectedTrIndex() {
        return selectedTrIndex;
    }

    public String getSelectedTestRunString() {
        return selectedTestRunString;
    }

    public void setSelectedReqString(String selectedReqString) {
        this.selectedReqString = selectedReqString;
        this.selectedReqIndex = (long) reqStrings.indexOf(this.selectedReqString);
        selectedTestCase.setRequirementByRequirementId(
                testSystem.findRequirement(this.selectedReqIndex+1)
        );
    }

    public void setSelectedTestRunString(String selectedTestRunString){
        this.selectedTestRunString = selectedTestRunString;
        this.selectedTrIndex = (long) this.trStrings.indexOf(this.selectedTestRunString);
        selectedTestCase.setTestrunByTestrunId(
                testSystem.findTestRun(this.selectedTrIndex+1)
        );

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

//    Setzt neben dem Selected Testcase auch dessen selectetRequirement String
    public void setSelectedTestCase(Testcase selectedTestCase) {
        this.selectedTestCase = selectedTestCase;
        this.selectedReqString = this.selectedTestCase.getRequirementByRequirementId().getName();
        if(this.selectedTestCase.getTestrunByTestrunId() == null){
            this.selectedTestRunString = null;
        }else{
            this.selectedTestRunString = this.selectedTestCase.getTestrunByTestrunId().getTitle();
        }

    }

    public void openNew() {
        this.selectedTestCase = new Testcase();
        this.selectedReqString = null;
        this.selectedTestRunString = null;
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

    public void saveTestCase() {
        if (this.selectedTestCase == null) {
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

    private Long getNextIndex(){
        Long highestId;
        List<Long> ident = new ArrayList<>() ;
        if ( !testcases.isEmpty()){
            for (Testcase tc : this.testcases){
                ident.add(tc.getId());
            }
            highestId = Collections.max(ident);
        }else{
            highestId = 0L;
        }
        return highestId+1L;
    }

    public void openEdit(Testcase tc){
        testSystem.setTcToEditOrRun(tc);

        PrimeFaces.current().dialog().openDynamic("testCaseEdit");
        System.out.println("Wurde geklickt");

    }

    public void onEdited(SelectEvent event){
        Testcase tc = (Testcase) event.getObject();
        this.selectedTestCase = tc;
        saveTestCase();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-testCases");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Testlauf geändert"));
    }


}
