import Entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {
    private User currentUser;

    private List<User> allUser;

    private String greetString;


    @Inject
    private TestSystem testSystem;


    public UserController() {
    }


    public List<User> getAllUser() {
        this.allUser = testSystem.getUserList();
        return allUser;
    }

    public void setAllUser(List<User> allUser) {
        this.allUser = allUser;
    }

    @PostConstruct
    public void init(){
        this.allUser = testSystem.getUserList();
        this.currentUser = null;


    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        this.greetString = "Hallo " + this.currentUser.getUsername();
    }

    public String getGreetString() {
        return greetString;
    }
    public String logout(){
        this.currentUser = null;
                return "login?faces-redirect=true";
    }

    public boolean isRE(){
        if(this.currentUser.getRole().equals("RE")||this.currentUser.getRole().equals("Admin")){
            return true;
        }else{
            return false;
        }

    }
    public boolean isAdmin(){
        if(this.currentUser.getRole().equals("Admin")){
            return true;
        }else{
            return false;
        }
    }
    public boolean isTM(){
        if(this.currentUser.getRole().equals("TM")||this.currentUser.getRole().equals("Admin")){
            return true;
        }else{
            return false;
        }

    }
    public boolean isTFE(){
        if(this.currentUser.getRole().equals("TFE")||this.currentUser.getRole().equals("Admin")){
            return true;
        }else{
            return false;
        }
    }
    public boolean isTester(){
        if(this.currentUser.getRole().equals("Tester")||this.currentUser.getRole().equals("Admin")){
            return true;
        }else{
            return false;
        }
    }

}
