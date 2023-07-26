package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.model.core.ID;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS user2");
            query.executeUpdate();

            transaction.commit();
        }catch (Exception e){
            System.out.println("fail" + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.saveOrUpdate(user);

            transaction.commit();

            System.out.println(name + " was saved to table successfully");
        } catch (Exception e) {
            System.out.println("User cannot saved:\n" + e);

        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
//            Query query = session.createSQLQuery("ALTER TABLE user2 DROP COLUMN id");
//            query.executeUpdate();
            User user = session.get(User.class, id);
            session.delete(user);

            transaction.commit();
        }catch (Exception e){
            System.out.println("fail" + e);
        }
    }



    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).getResultList();




            transaction.commit();
        }catch (Exception e){
            System.out.println("fail" + e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();


            transaction.commit();
        }catch (Exception e){
            System.out.println("fail" + e);
        }

    }
}
