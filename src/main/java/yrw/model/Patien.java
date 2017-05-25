package yrw.model;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedQueries({
    @NamedQuery(
            name = "fetchAllPatiens",
            query = "select p from Patien p"
    )})
@NamedQuery(
        name = "getPatienWithId",
        query = "select p from Patien p where p.id = :id"
)

@Entity
@Table(name = "patiens")
public class Patien extends AbstractEntity implements Serializable {

    private boolean deleted;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    private String city;
    private String street;
    private int tk;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patien")
    @JsonManagedReference
    private List<Visit> visits = new ArrayList<>();

    public Patien() {

        this.deleted = false;

    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getTk() {
        return tk;
    }

    public void setTk(int tk) {
        this.tk = tk;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

}
