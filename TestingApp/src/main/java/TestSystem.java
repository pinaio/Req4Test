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

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TESTINGAPP");
    public TestSystem() {


    }

    public String getName() {
        return name;
    }

    public ArrayList<Requirement> getReqList() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select a from Requirement a order by id");
        this.reqList = q.getResultList();
        em.close();
        return (ArrayList<Requirement>) this.reqList;
    }


    public int sayAmount(){
        return this.reqList.size();

    }
}
