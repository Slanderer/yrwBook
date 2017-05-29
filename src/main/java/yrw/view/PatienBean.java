package yrw.view;


import org.primefaces.event.FileUploadEvent;
import yrw.ejb.ActionsEjb;
import yrw.model.Document;
import yrw.model.Patien;
import yrw.model.Visit;
import yrw.model.VisitCause;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by john
 * on 28/2/2017.
 */

//@ManagedBean(name = "patienBean")
@Named
@ViewScoped
public class PatienBean implements Serializable {

    @Inject
    private ActionsEjb actionsEjb;

    private List<Patien> patiens;
    private Patien selectedPatien;
    private Visit selectedVisit = new Visit();
    private String filter;


    @PostConstruct
    private void init() {
        patiens = actionsEjb.fetchAll(Patien.class);
        filter = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("patienID");
        if (filter != null) {
            selectedPatien = actionsEjb.findById(Patien.class, Long.parseLong(filter));
        }
    }

    public void newPatien() {
        selectedPatien = new Patien();
    }

    public void deletePatien() {
        if (selectedPatien != null && !selectedPatien.isDeleted()) {
            selectedPatien.setDeleted(true);
            actionsEjb.updateEntity(selectedPatien);
            patiens.remove(selectedPatien);
        }
    }

    public void persistPatien() {
        if (selectedPatien != null) {
            Patien p = actionsEjb.updateEntity(selectedPatien);
            if (!patiens.contains(p)) {
                patiens.add(selectedPatien);
            }
        }
    }

    public void newVisit() {
        selectedVisit = new Visit();
        selectedVisit.setPatien(selectedPatien);
    }

    public void persistVisit() {
        if (selectedVisit != null) {
            Visit v = actionsEjb.updateEntity(selectedVisit);
            if (!selectedPatien.getVisits().contains(selectedVisit)) {
                selectedPatien.getVisits().add(v);
            }
        }
    }

    public void deleteVisit() {
        if (selectedVisit != null && !selectedVisit.isDeleted()) {
            selectedVisit.setDeleted(true);
            actionsEjb.updateEntity(selectedVisit);
            selectedPatien.getVisits().remove(selectedPatien);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        Document document= new Document();
        document.setPatien(selectedPatien);
        document.setFiledata(event.getFile().getContents());
        document.setContentType(event.getFile().getContentType());
        document.setFileName(event.getFile().getFileName());

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        actionsEjb.createEntity(document);
    }


    public Patien getSelectedPatien() {
        return selectedPatien;
    }

    public void setSelectedPatien(Patien selectedPatien) {
        this.selectedPatien = selectedPatien;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Patien> getPatiens() {
        return patiens;
    }

    public void setPatiens(List<Patien> patiens) {
        this.patiens = patiens;
    }

    public Visit getSelectedVisit() {
        return selectedVisit;
    }

    public void setSelectedVisit(Visit selectedVisit) {
        this.selectedVisit = selectedVisit;
    }

}
