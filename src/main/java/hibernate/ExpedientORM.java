package hibernate;

import model.Expedient;

import java.util.Date;
import java.util.List;

public class ExpedientORM {

    private final Hibernate hibernate;

    public ExpedientORM() {
        this.hibernate = new Hibernate();
    }

    public List<Expedient> selectAllExpedients() {
        return this.hibernate.getSession().createQuery("FROM Expedient", Expedient.class).getResultList();
    }

    public void insertExpedient(int id, String name, String surname, String dni, int numberOfPets, Date date, String postalCode,
                                int phone, int id_user_up) {
        this.hibernate.getSessionFactory().openSession().beginTransaction();
        Expedient expedient = new Expedient();
        //expedient.setId(id);
        expedient.setName(name);
        expedient.setSurname(surname);
        expedient.setDni(dni);
        expedient.setNumberOfPets(numberOfPets);
        expedient.setDate(date);
        expedient.setPostalCode(postalCode);
        expedient.setPhone(phone);
        expedient.setId_user_up(id_user_up);
        this.hibernate.getSession().save(expedient);
        this.hibernate.getTransaction().commit();
    }

    public void deleteExpedient() {

    }

    public void updateExpedient(Expedient expedient, int numberOfPets, int phone, String postalCode) {
        expedient.setNumberOfPets(numberOfPets);
        expedient.setPostalCode(postalCode);
        expedient.setPhone(phone);
        this.hibernate.getSession().update(expedient);
        this.hibernate.getTransaction().commit();
    }
}
