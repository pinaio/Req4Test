import Entities.Requirement;
import Entities.Testrun;
import Entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ApplicationScoped
public class TestSystem implements Serializable {

    private String name = "TestingApp 0.1 alpha";
    private List reqList =new ArrayList<Requirement>();
    private List testRunList =new ArrayList<Testrun>();

    private List userList = new ArrayList<User>();

    private ReqsDAO reqsDao = new ReqsDAO();
    private TestRunDAO testRunDao = new TestRunDAO();

    private UserDAO userDao = new UserDAO();


    public TestSystem() {
    }

    public String getName() {
        return name;
    }

    public ArrayList<Requirement> getReqList() {

        this.reqList = reqsDao.loadAll();

        return (ArrayList<Requirement>) this.reqList;
    }

    public ArrayList<Testrun> getTestRunList(){
        this.testRunList = testRunDao.loadAll();
        return (ArrayList<Testrun>) this.testRunList;
    }

    public ArrayList<User> getUserList(){
    this.userList = userDao.loadAll();
    return (ArrayList<User>) this.userList;
}



    public void saveRequirement(Requirement selectedRequirement){
            reqsDao.saveRequirement(selectedRequirement);
    }
    public void saveTestRun(Testrun selectedTestRun){
        testRunDao.saveTestRun(selectedTestRun);
    }

    public void saveUser(User selectedUser) {userDao.saveUser(selectedUser);}

    public void deleteRequirement(Requirement selectedRequirement){
        reqsDao.deleteRequirement(selectedRequirement);
    }
    public void deleteTestRun(Testrun selectedTestRun){
        testRunDao.deleteTestRun(selectedTestRun);
    }

    public void deleteUser(User selectedUser){
        userDao.deleteUser(selectedUser);
    }

    public int sayAmount(){
        return this.testRunList.size();

    }

    public  void createTestData(){
        createTestRequirements();
        createTestTestRuns();
        createTestUser();
    }
    private   void createTestRequirements(){
        List<Requirement> testReqList = new ArrayList<Requirement>();
        testReqList.add(new Requirement(1,"Person A","Dies ist das Erste Requirement","Erstes Requirement","Neu"));
        testReqList.add(new Requirement(2,"Person B","Dies ist das 2. Requirement","Zweites Requirement","Neu"));
        testReqList.add(new Requirement(3,"Person C","Dies ist das 3. Requirement","Drittes Requirement","Neu"));
        testReqList.add(new Requirement(4,"Person D","Dies ist das 4. Requirement","Viertes Requirement","Neu"));

        for(Requirement req : testReqList){
            saveRequirement(req);
        }
    }
    private   void createTestTestRuns(){
        List<Testrun> testTrList = new ArrayList<Testrun>();
        testTrList.add(new Testrun(1 ,new Date(),"Peter Testmanager","Neu","Erster Testlauf" ));
        testTrList.add(new Testrun(2 ,new Date(),"Peter Testmanager","Neu","2. Testlauf" ));
        testTrList.add(new Testrun(3 ,new Date(),"Peter Testmanager","Neu","3. Testlauf" ));
        testTrList.add(new Testrun(4 ,new Date(),"Peter Testmanager","Neu","4. Testlauf" ));


        for(Testrun tr : testTrList){
            saveTestRun(tr);
        }}
        private void createTestUser(){
            List<User> tUser = new ArrayList<User>();
            tUser.add(new User("UserA","Alfons", "Admin","password","Admin",1));
            tUser.add(new User("UserB","Theo", "Tester","password","Tester",2));
            tUser.add(new User("UserC","Margret", "Managerin","password","Testmanagerin",3));
            tUser.add(new User("UserA","Fallo", "Testfallersteller","password","Testfallersteller",4));



            for(User u : tUser){
                saveUser(u);
            }


    }



}
