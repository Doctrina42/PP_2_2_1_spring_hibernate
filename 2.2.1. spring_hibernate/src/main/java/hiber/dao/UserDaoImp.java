package hiber.dao;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public List<User> getUserByCarDetails(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User where car.model=:model1 and car.series=:seria1");
      query.setParameter("model1", model);
      query.setParameter("seria1", series);

      return query.getResultList();
   }
}