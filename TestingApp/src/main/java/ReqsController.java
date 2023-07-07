import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
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
public class ReqsController implements Serializable {

    private List<Requirement> requirements;
    private Requirement selectedRequirement;
    private List<Requirement> selectedRequirements;

    @Inject
    private TestSystem testSystem;
    private String header ="Anforderungen im Überblick";

    @PostConstruct
    public void init(){
        this.requirements = testSystem.getReqList();
        System.out.println("Neu Geladen");
    }

    public ReqsController() {
    }

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
        if(this.selectedRequirement != null){
            System.out.println(this.selectedRequirement.getDescription());
        }
    }

    public void setSelectedRequirements(List<Requirement> selectedRequirements) {
        this.selectedRequirements = selectedRequirements;
    }


    public List<Requirement> getSelectedRequirements() {
        return selectedRequirements;
    }

    public void openNew() {
        this.selectedRequirement = new Requirement();
    }
    public void saveRequirement() {
        if (this.selectedRequirement.getId() == null) {
            this.selectedRequirement.setId(
                   String.valueOf(getNextIndex())
            );
            this.requirements.add(this.selectedRequirement);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung hinzugefügt"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung geändert"));
        }

        PrimeFaces.current().executeScript("PF('manageRequirementDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-requirements");
    }
    public void deleteRequirement() {
        this.requirements.remove(this.selectedRequirement);
        this.selectedRequirement = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung gelöscht"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-requirements");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedRequirements()) {
            int size = this.selectedRequirements.size();
            return size > 1 ? size + " Anforderungen ausgewählt" : "1 Anforderung Ausgewählt";
        }

        return "Delete";
    }

    public boolean hasSelectedRequirements() {
        if (this.selectedRequirements == null ){
        System.out.println("IST LEER");}
        else{
            System.out.println(this.selectedRequirements.size());
            }
        return this.selectedRequirements != null && !this.selectedRequirements.isEmpty();
    }

    public void deleteSelectedRequirements() {
        this.requirements.removeAll(this.selectedRequirements);
        this.selectedRequirements = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderungen gelöscht"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-requirements");
        PrimeFaces.current().executeScript("PF('dtRequirements').clearFilters()");
        PrimeFaces.current().executeScript("PF('dtRequirements').update()");
    }

    private int getNextIndex(){
        int highestId;
        List<Integer> ident = new ArrayList<>() ;
        for (Requirement req : this.requirements){
            ident.add(Integer.parseInt((req.getId())));
        }
        highestId = Collections.max(ident);
        return highestId+1;
    }




}
