import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;

@Named
@ViewScoped
public class indexController implements Serializable {


    @Inject
    private TestSystem testSystem;
    @Inject
    private UserController userController;


    public TestSystem getTestSystem() {
        return testSystem;
    }

    public void setTestSystem(TestSystem testSystem) {
        this.testSystem = testSystem;
    }


    public UserController getUserController() {
        return userController;
    }
}
