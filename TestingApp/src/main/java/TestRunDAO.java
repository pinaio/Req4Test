import Entities.Testrun;
import jakarta.persistence.*;

import java.util.List;

public class TestRunDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TESTINGAPP");

    public List<Testrun> loadAll(){
        EntityManager em =entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select a from Testrun a order by id");
        return q.getResultList();
    }

    public void saveTestRun(Testrun selectedTestRun){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(selectedTestRun);
        t.commit();
        em.close();

    }

    public void deleteTestRun(Testrun selectedTestRun) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Testrun toDelete = em.find(Testrun.class, selectedTestRun.getId());
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
