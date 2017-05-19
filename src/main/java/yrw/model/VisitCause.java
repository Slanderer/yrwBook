package yrw.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ivarsamoulis
 * on 18/5/2017.
 */
@Entity
@Table(name = "causes")
public class VisitCause extends AbstractEntity implements Serializable {

    private String code;

    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
