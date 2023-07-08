import Entities.Requirement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class TestSystem implements Serializable {

    private String name = "TestingApp 0.1 alpha";
    private List reqList =new ArrayList<Requirement>();

    private ReqsDAO reqsDao = new ReqsDAO();


    public TestSystem() {


    }

    public String getName() {
        return name;
    }

    public ArrayList<Requirement> getReqList() {

        this.reqList = reqsDao.loadAll();

        return (ArrayList<Requirement>) this.reqList;
    }

    public void saveRequirement(Requirement selectedRequirement){
            reqsDao.saveRequirement(selectedRequirement);
    }
    public void deleteRequirement(Requirement selectedRequirement){
        reqsDao.deleteRequirement(selectedRequirement);
    }

    public int sayAmount(){
        return this.reqList.size();

    }
}
