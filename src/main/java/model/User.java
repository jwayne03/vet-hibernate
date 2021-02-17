package model;

import utils.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellidos")
    private String surname;
    @Column(name = "dni")
    private String dni;
    @Column(name = "matricula")
    private String personal_tuition;
    @Column(name = "pass")
    private String password;
    @Column(name = "tipousuario")
    private int userType;
    @Column(name = "ultimoacceso", insertable = false, updatable = false)
    private Date lastaccess;

    public User() {

    }

    public User(int id, String name, String surname, String dni, String personal_tuition, String password, int userType, Date lastaccess, String last_access) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.personal_tuition = personal_tuition;
        this.password = password;
        this.userType = userType;
        this.lastaccess = lastaccess;
    }

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

    public String getPersonal_tuition() {
        return personal_tuition;
    }

    public void setPersonal_tuition(String personal_tuition) {
        this.personal_tuition = personal_tuition;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Date getLastaccess() {
        return lastaccess;
    }

    public void setLastaccess(Date lastaccess) {
        this.lastaccess = lastaccess;
    }
}
