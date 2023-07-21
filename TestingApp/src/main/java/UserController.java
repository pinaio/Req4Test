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


    @Inject
    private TestSystem testSystem;


    public UserController() {
    }

    public List<User> getAllUser() {
        return allUser;
    }

    public void setAllUser(List<User> allUser) {
        this.allUser = allUser;
    }

    @PostConstruct
    public void init(){
        this.allUser = testSystem.getUserList();
        // Dies muss noch weg und durch das richtige ersetzt werden
        this.currentUser = testSystem.getUserList().get(0);


    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }



}
