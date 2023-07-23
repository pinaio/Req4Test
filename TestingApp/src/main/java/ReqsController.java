import Entities.Requirement;
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
import java.util.Objects;

@Named
@ViewScoped
public class ReqsController implements Serializable {

    private List<Requirement> requirements;
    private Requirement selectedRequirement;

    @Inject
    private TestSystem testSystem;
    @Inject
    private UserController userController;
    private String header = "Anforderungen";

    @PostConstruct
    public void init() {
        this.requirements = testSystem.getReqList();
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

    public void setSelectedRequirement(Requirement selectedRequirement) {
        this.selectedRequirement = selectedRequirement;
    }

    public void openNew() {
        this.selectedRequirement = new Requirement();
        this.selectedRequirement.setId(getNextIndex());
        this.selectedRequirement.setAuthor(userController.getCurrentUser().getUsername());
        this.selectedRequirement.setStatus("Neu");
    }

    public void saveRequirement() {
        if (Objects.equals(this.selectedRequirement.getId(), getNextIndex())) {
            this.selectedRequirement.setId(
                    (getNextIndex())
            );
            testSystem.saveRequirement(this.selectedRequirement);
            this.requirements = testSystem.getReqList();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung hinzugefügt"));
        } else {
            testSystem.saveRequirement(this.selectedRequirement);
            this.requirements = testSystem.getReqList();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung geändert"));

        }

        PrimeFaces.current().executeScript("PF('manageRequirementDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-requirements");
    }

    public void deleteRequirement() {
        List<Testcase> tcList = (List<Testcase>) this.selectedRequirement.getTestcasesById();
        for (Testcase tc : tcList) {
            tc.setRequirementByRequirementId(null);
            tc.setTestrunByTestrunId(null);
            testSystem.saveTestCase(tc);
            testSystem.deleteTestCase(tc);
        }

        testSystem.deleteRequirement(this.selectedRequirement);
        this.selectedRequirement = null;
        this.requirements = testSystem.getReqList();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Anforderung und alle Testfälle gelöscht"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-requirements");
    }

    private Long getNextIndex() {
        Long highestId;
        List<Long> ident = new ArrayList<>();
        if (!requirements.isEmpty()) {
            for (Requirement req : this.requirements) {
                ident.add(req.getId());
            }
            highestId = Collections.max(ident);
        } else {
            highestId = 0L;
        }
        return highestId + 1L;
    }


}
