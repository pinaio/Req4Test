import Entities.User;
import jakarta.persistence.*;

import java.util.List;

public class UserDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TESTINGAPP");

    public List<User> loadAll(){
        EntityManager em =entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select a from User a order by id");
        return q.getResultList();
    }

    public void saveUser(User selectedUser){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(selectedUser);
        t.commit();
        em.close();

    }

    public void deleteUser(User selectedUser) {
        EntityManager em = entityManagerFactory.createEntityManager();
        User toDelete = em.find(User.class, selectedUser.getId());
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


