package hibernate;

import model.User;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PersonORM {

    private final Hibernate hibernate;

    public PersonORM() {
        this.hibernate = new Hibernate();
    }

    public List<User> selectAllPeople() {
        return this.hibernate.getSession().createQuery("FROM User", User.class).getResultList();
    }

    public void insertNewUser(int id, String name, String surname, String dni, String personal_tuition, String password, int userType) {
        this.hibernate.getSessionFactory().openSession().beginTransaction();
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setDni(dni);
        user.setPersonal_tuition(personal_tuition);
        user.setPassword(password);
        user.setUserType(userType);
        user.setLastaccess(null);
        this.hibernate.getSession().save(user);
        this.hibernate.getTransaction().commit();
    }

    //TODO
    public void deletePerson() {
        System.out.println("borrado");
    }

    public void updateUser(User user, int type, String password) {
        user.setUserType(type);
        user.setPassword(password);
        this.hibernate.getSession().update(user);
        this.hibernate.getTransaction().commit();
    }

    public void updateSessionUser(User user) {
        user.setLastaccess(new Date());
        this.hibernate.getSession().update(user);
        this.hibernate.getTransaction().commit();
    }
}
