package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        session.createSQLQuery("create  table if not exists users ( " +
                " id serial not null ," +
                " name varchar(50) not null, " +
                " lastName varchar(50) not null ," +
                " age int  not null )").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Tabe is created sucessfully...");

    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        session.createSQLQuery("drop table if exists users").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Table is drob sucessfully");


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("User is saved sucessfully");

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("Id is removed sucessfully");

    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
        List<User> userList = session.createQuery(" from User").getResultList();
        session.getTransaction().commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession().openSession();
        session.beginTransaction();
     session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
}
