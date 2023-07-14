import Entities.Requirement;
import Entities.Testcase;
import jakarta.persistence.*;

import java.util.List;

public class ReqsDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TESTINGAPP");

    public List<Requirement> loadAll(){
        EntityManager em =entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select a from Requirement a order by id");
        return q.getResultList();
    }

    public void saveRequirement(Requirement selectedRequirement){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(selectedRequirement);
        t.commit();
        em.close();

    }
    public Requirement find(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        Requirement tc = em.find(Requirement.class, id);
        em.close();
        return tc;
    }

    public void deleteRequirement(Requirement selectedRequirement) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Requirement toDelete = em.find(Requirement.class, selectedRequirement.getId());
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
