package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expedientes")
public class Expedient {

    @Id
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellidos")
    private String surname;
    @Column(name = "dni")
    private String dni;
    @Column(name = "nmascotas")
    private int numberOfPets;
    @Column(name = "fechaalta")
    private String date;
    @Column(name = "cp")
    private String postalCode;
    @Column(name = "telefono")
    private int phone;
    @Column(name = "usuarioalta")
    private int id_user_up;

    public Expedient() {

    }

    public Expedient(int id, String name, String surname, String dni, int numberOfPets, String date, String postalCode,
                     int phone, int id_user_up) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.numberOfPets = numberOfPets;
        this.date = date;
        this.postalCode = postalCode;
        this.phone = phone;
        this.id_user_up = id_user_up;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getNumberOfPets() {
        return numberOfPets;
    }

    public void setNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId_user_up() {
        return id_user_up;
    }

    public void setId_user_up(int id_user_up) {
        this.id_user_up = id_user_up;
    }

    @Override
    public String toString() {
        return "Expedient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dni='" + dni + '\'' +
                ", numberOfPets=" + numberOfPets +
                ", date='" + date + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone=" + phone +
                ", id_user_up=" + id_user_up +
                '}';
    }
}
