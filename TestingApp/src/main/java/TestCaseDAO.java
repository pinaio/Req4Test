import Entities.Requirement;
import Entities.Testcase;
import jakarta.persistence.*;

import java.util.List;

public class TestCaseDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TESTINGAPP");

    public List<Testcase> loadAll(){
        EntityManager em =entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select a from Testcase a order by id");
        return q.getResultList();
    }

    public void saveTestCase(Testcase selectedTestCase){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(selectedTestCase);
        t.commit();
        em.close();
        System.out.println("Testfall gespeichert");

    }

    public Testcase find(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        Testcase tc = em.find(Testcase.class, id);
        em.close();
        return tc;
    }


    public void deleteTestCase(Testcase selectedTestCase) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Testcase toDelete = em.find(Testcase.class, selectedTestCase.getId());
        EntityTransaction t = em.getTransaction();
        if (toDelete != null) {
            t.begin();
            em.remove(toDelete);
            t.commit();
            em.close();
        } else
            em.close();
    }

}