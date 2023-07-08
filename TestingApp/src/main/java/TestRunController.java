import Entities.Requirement;
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
public class TestRunController implements Serializable {

    private List<Requirement> requirements;
    private Requirement selectedRequirement;


    @Inject
    private TestSystem testSystem;
    private String header ="Anforderungen im Überblick";

    @PostConstruct
    public void init(){
        this.requirements = testSystem.getReqList();
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
    public List<Requirement> getRequirements() {
        return requirements;
    }

    public Requirement getSelectedRequirement() {
        return selectedRequirement;
    }

    public void setSelectedRequirement(Requirement selectedRequirement){
        this.selectedRequirement = selectedRequirement;
    }



    public void openNew() {
        this.selectedRequirement = new Requirement();
    }
    public void saveRequirement() {
        if (this.selectedRequirement.getId() == null) {
            this.selectedRequirement.setId(
                   String.valueOf(getNextIndex())
            );
            testSystem.saveRequirement(this.selectedRequirement);
            this.requirements = testSystem.getReqList();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung hinzugefügt"));
        }
        else {
            testSystem.saveRequirement(this.selectedRequirement);
            this.requirements = testSystem.getReqList();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung geändert"));

        }

        PrimeFaces.current().executeScript("PF('manageRequirementDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-requirements");
    }
    public void deleteRequirement() {
//        HIER MUSS DIE DAO LÖSCHEN UND AKTUALISIEREN
        testSystem.deleteRequirement(this.selectedRequirement);
        this.selectedRequirement = null;
        this.requirements = testSystem.getReqList();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung gelöscht"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-requirements");
    }

    private int getNextIndex(){
        int highestId;
        List<Integer> ident = new ArrayList<>() ;
        if ( !requirements.isEmpty()){
            for (Requirement req : this.requirements){
                ident.add(Integer.parseInt((req.getId())));
            }
            highestId = Collections.max(ident);
        }else{
            highestId = 0;
        }
        return highestId+1;
    }




}
