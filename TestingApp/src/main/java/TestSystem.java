import Entities.Requirement;
import Entities.Testcase;
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

    private String name = "Require4Test 0.1";
    private List reqList =new ArrayList<Requirement>();
    private List testRunList =new ArrayList<Testrun>();

    private List testCaseList = new ArrayList<Testcase>();

    private List userList = new ArrayList<User>();

    private ReqsDAO reqsDao = new ReqsDAO();
    private TestRunDAO testRunDao = new TestRunDAO();
    private TestCaseDAO testCaseDao = new TestCaseDAO();

    private UserDAO userDao = new UserDAO();

    private Testcase tcToEditOrRun = new Testcase();


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

    public ArrayList<Testcase> getTestCaseList(){
        this.testCaseList = testCaseDao.loadAll();
        return (ArrayList<Testcase>) this.testCaseList;
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

    public void saveTestCase(Testcase selectedTestCase){testCaseDao.saveTestCase(selectedTestCase);}

    public void saveUser(User selectedUser) {userDao.saveUser(selectedUser);}

    public void deleteRequirement(Requirement selectedRequirement){
        reqsDao.deleteRequirement(selectedRequirement);
    }
    public void deleteTestRun(Testrun selectedTestRun){
        testRunDao.deleteTestRun(selectedTestRun);
    }

    public void deleteTestCase(Testcase selectedTestCase) {testCaseDao.deleteTestCase(selectedTestCase);}

    public void deleteUser(User selectedUser){
        userDao.deleteUser(selectedUser);
    }

    public Testcase findTestCase(Long id){
        return testCaseDao.find(id);

    }
    public Requirement findRequirement(Long id){
        return reqsDao.find(id);

    }
    public Testrun findTestRun(Long id){
        return testRunDao.find(id);

    }
    public User findUser(Long id){
        return userDao.find(id);

    }


    public Testcase getTcToEditOrRun() {
        return tcToEditOrRun;
    }

    public void setTcToEditOrRun(Testcase tcToEditOrRun) {
        this.tcToEditOrRun = tcToEditOrRun;
    }

    public int sayAmount(){
        return this.testRunList.size();

    }

    public  void createTestData(){
        createTestUser();
        getUserList();
        createTestRequirements();
        getReqList();
        createTestTestRuns();
        getTestRunList();
        createTestTestCases();
        getTestCaseList();

    }
    private   void createTestRequirements(){
        List<Requirement> testReqList = new ArrayList<Requirement>();
        testReqList.add(new Requirement(1L,"Person A","Dies ist das Erste Requirement","Erstes Requirement","Neu"));
        testReqList.add(new Requirement(2L,"Person B","Dies ist das 2. Requirement","Zweites Requirement","Neu"));
        testReqList.add(new Requirement(3L,"Person C","Dies ist das 3. Requirement","Drittes Requirement","Neu"));
        testReqList.add(new Requirement(4L,"Person D","Dies ist das 4. Requirement","Viertes Requirement","Neu"));

        for(Requirement req : testReqList){
            saveRequirement(req);
        }
    }
    private   void createTestTestRuns(){
        User ersterUser = (User) userList.get(2);
        List<Testrun> testTrList = new ArrayList<Testrun>();
        testTrList.add(new Testrun(1L ,new Date(),"Peter Testmanager","Neu","Erster Testlauf", ersterUser ));
        testTrList.add(new Testrun(2L ,new Date(),"Peter Testmanager","Neu","2. Testlauf" ));
        testTrList.add(new Testrun(3L ,new Date(),"Peter Testmanager","Neu","3. Testlauf" ));
        testTrList.add(new Testrun(4L ,new Date(),"Peter Testmanager","Neu","4. Testlauf" ));



        for(Testrun tr : testTrList){
            saveTestRun(tr);
        }}
        private void createTestUser(){
            List<User> tUser = new ArrayList<User>();
            tUser.add(new User("Alfons Admin","Alfons", "Admin","password","Admin",1L));
            tUser.add(new User("Theo Tester","Theo", "Tester","password","Tester",2L));
            tUser.add(new User("Margret Managerin","Margret", "Managerin","password","TM",3L));
            tUser.add(new User("Fallo Testfallersteller","Fallo", "Testfallersteller","password","TFE",4L));
            tUser.add(new User("Theresa Re","Theresa", "Tester","password","RE",5L));



            for(User u : tUser){
                saveUser(u);
            }



    }

    private void createTestTestCases(){
        Testrun firstTestrun = (Testrun) this.testRunList.get(0);
        Requirement firstrequirement = (Requirement) this.getReqList().get(0);
        List<Testcase> tcases = new ArrayList<Testcase>();
        tcases.add(new Testcase( 1L, "Ich möchte neue Testfälle anlegen können", "Ich kann Testfälle anlegen","","","Testfälle anlegen",firstTestrun,firstrequirement));
        tcases.add(new Testcase( 2L, "Ich möchte neue Testläufe anlegen können", "Ich kann Testläufe anlegen","pass","","Testläufe anlegen",null,firstrequirement));
        tcases.add(new Testcase( 3L, "Ich möchte neue Anforderungen anlegen können", "Ich kann Anforderungen anlegen","fail","","Anforderungen anlegen",firstTestrun,firstrequirement));

        for(Testcase tc : tcases){
            saveTestCase(tc);
        }
    }



}
