package hibernate;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import utils.UserType;

import java.util.Date;
import java.util.List;

public class Hibernate {

    private final StandardServiceRegistryBuilder serviceRegistryBuilder;
    private final Configuration configuration;
    private final ServiceRegistry serviceRegistry;
    private final SessionFactory sessionFactory;
    private final Session session;
    private final Transaction transaction;

    public Hibernate() {
        this.serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        this.configuration = new Configuration()
                .configure()
                .addAnnotatedClass(User.class);
        this.serviceRegistryBuilder.applySettings(configuration.getProperties());
        this.serviceRegistry = serviceRegistryBuilder.build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        this.session = sessionFactory.openSession();
        this.transaction = this.session.beginTransaction();
    }

    public List<User> selectAllUser() {
        return this.session.createQuery("FROM User ", User.class).getResultList();
    }

    public void registerUser(String name, String surname, String dni, String tuition, String password,
                             int type, Date lastaccess) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setDni(dni);
        user.setPersonal_tuition(tuition);
        user.setPassword(password);
        user.setUserType(type);
        user.setLastaccess(lastaccess);
        session.save(user);
        transaction.commit();
    }

    public StandardServiceRegistryBuilder getServiceRegistryBuilder() {
        return serviceRegistryBuilder;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
