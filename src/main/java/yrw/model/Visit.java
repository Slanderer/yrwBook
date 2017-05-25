package yrw.model;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.primefaces.model.DefaultScheduleEvent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//
//@NamedQueries({
//        @NamedQuery(
//                name = "fetchAllVisits",
//                query = "select v from Visit v"
//        )
//})

@Entity
@Table(name = "visits")
public class Visit extends AbstractEntity implements Serializable {

    private boolean deleted;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private VisitCause cause;

    private double amount;

    @ManyToOne
    @JsonBackReference
    private Patien patien;

    public Visit() {
        this.deleted = false;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Patien getPatien() {
        return patien;
    }

    public void setPatien(Patien patien) {
        this.patien = patien;
    }

    public VisitCause getCause() {
        return cause;
    }

    public void setCause(VisitCause cause) {
        this.cause = cause;
    }
}
