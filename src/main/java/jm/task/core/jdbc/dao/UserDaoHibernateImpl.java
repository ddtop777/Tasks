package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


//    @Override
//    public void createUsersTable() {
//        Session session = Util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        try {
//        } catch (Exception e) {
//            transaction.rollback();
//            System.out.println("Ошибка создания таблицы " + e);
//        }
//    }
@Override
public void createUsersTable() {
    Session session = Util.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    try {
        // Создание таблицы "Users"
        String createTableQuery = "CREATE TABLE Users (id INT PRIMARY KEY, " +
                "name VARCHAR(255), age SMALLINT, email VARCHAR(255))";
        session.createSQLQuery(createTableQuery).executeUpdate();

        // Фиксируем изменения в базе данных
        transaction.commit();
        System.out.println("Таблица Users успешно создана.");
    } catch (Exception e) {
        transaction.rollback();
        System.out.println("Ошибка создания таблицы: " + e);
    } finally {
        session.close();
    }
}

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("fail" + e);
        }
    }

    @Override
    public void saveUser(String name, byte age, String email) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = new User();
            user.setName(name);
            user.setAge(age);
            user.setEmail(email);
            session.saveOrUpdate(user);
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка сохранения пользователя " + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession())
        {
        Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при открытии сессии " + e.getMessage());


        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM User");
            users = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("fail" + e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            System.out.println("fail" + e);
        }
    }
}