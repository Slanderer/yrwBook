package yrw.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ivarsamoulis
 * on 26/5/2017.
 */
@Entity
@Table(name = "documents")
public class Document extends AbstractEntity implements Serializable {

    private String fileName;

    private String description;

    private String contentType;

    @Lob
    @Basic(fetch= FetchType.LAZY)
    private byte[] filedata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Patien patien;


    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public Patien getPatien() {
        return patien;
    }

    public void setPatien(Patien patien) {
        this.patien = patien;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
