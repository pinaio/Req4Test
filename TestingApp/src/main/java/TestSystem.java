import Entities.Requirement;
import Entities.Testrun;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class TestSystem implements Serializable {

    private String name = "TestingApp 0.1 alpha";
    private List reqList =new ArrayList<Requirement>();
    private List testRunList =new ArrayList<Testrun>();

    private ReqsDAO reqsDao = new ReqsDAO();
    private TestRunDAO testRunDao = new TestRunDAO();


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


    public void saveRequirement(Requirement selectedRequirement){
            reqsDao.saveRequirement(selectedRequirement);
    }
    public void saveTestRun(Testrun selectedTestRun){
        testRunDao.saveTestRun(selectedTestRun);
    }

    public void deleteRequirement(Requirement selectedRequirement){
        reqsDao.deleteRequirement(selectedRequirement);
    }
    public void deleteTestRun(Testrun selectedTestRun){
        testRunDao.deleteTestRun(selectedTestRun);
    }

    public int sayAmount(){
        return this.testRunList.size();

    }
}
