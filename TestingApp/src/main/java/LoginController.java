import Entities.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named
@RequestScoped
public class LoginController implements Serializable {

    @Inject
    TestSystem testSystem;
    @Inject UserController userController;

    private User loginUser = new User();

    private  String username;
    private String password;

    public LoginController() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        loginUser = loginUser;
    }

    public TestSystem getTestSystem() {
        return testSystem;
    }

    public void postValidateUsername(ComponentSystemEvent e)throws AbortProcessingException {
        UIInput temp = (UIInput) e.getComponent();
        this.username =(String) temp.getValue();
        System.out.println("JETZT wurde POST VALIDIERT");

    }

    public void  validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        List<User> userList = userController.getAllUser();
        if(userList.isEmpty()){
            throw new ValidatorException((new FacesMessage("Keine Nutzer")));
        }else{
            for(User u : userList){
                User temp = new User(this.username, (String) value);
                if(u.equals(temp)){
                    if(u.getPassword().equals(temp.getPassword())){
                        this.loginUser = u;
                        System.out.println("Eingeloggt");
                        return;
                    }else{

                        throw new ValidatorException((new FacesMessage("Passwort falsch.")));
                    }
                }
            }throw new ValidatorException((new FacesMessage("Nutzer gibt es nicht.")));
        }


    }
    public String login(){
        userController.setCurrentUser(loginUser);
        return "index?faces-redirect=true";

    }
    public String adminLogin(){
        testSystem.createTestData();
        List<User> userList = userController.getAllUser();
        User temp = new User("Alfons Admin","password");
        for(User u : userList){
            if(u.equals(temp)){
                if(u.getPassword().equals(temp.getPassword())){
                    this.loginUser = u;
                    System.out.println("Eingeloggt");

                    userController.setCurrentUser(loginUser);
                    return "index?faces-redirect=true";

                }

             }

        }return null;

    }
}
