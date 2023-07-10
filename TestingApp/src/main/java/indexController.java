import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class indexController implements Serializable {


    @Inject
    private TestSystem testSystem;


    public TestSystem getTestSystem() {
        return testSystem;
    }

    public void setTestSystem(TestSystem testSystem) {
        this.testSystem = testSystem;
    }
}
