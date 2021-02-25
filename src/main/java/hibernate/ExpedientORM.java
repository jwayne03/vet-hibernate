package hibernate;

import model.Expedient;

import java.util.Date;
import java.util.List;

public class ExpedientORM {

    private final Hibernate hibernate;
//    private final Expedient expedient;

    public ExpedientORM() {
        this.hibernate = new Hibernate();
//        this.expedient = new Expedient();
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
        System.out.println();
        this.hibernate.getSession().save(expedient);
        this.hibernate.getTransaction().commit();
    }

    public void deleteExpedient() {

    }
}
