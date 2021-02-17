package hibernate;

import model.User;

import java.util.List;

public class PersonORM {

    private final Hibernate hibernate;

    public PersonORM() {
        this.hibernate = new Hibernate();
    }

    public List<User> selectAllPeople() {
        return this.hibernate.getSession()
                .createQuery("FROM User", User.class)
                .getResultList();
    }
}
