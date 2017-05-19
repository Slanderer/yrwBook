package yrw.model;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public class AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    @PrePersist
    private void onCreate() {
        this.setCreationDate(new Date());
    }

    @PreUpdate
    private void onUpdate() {
        this.setModificationDate(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;
        return (id == that.id && id != 0L);
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
