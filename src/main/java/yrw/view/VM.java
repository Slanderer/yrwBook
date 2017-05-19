//package yrw.view;
//
//import org.primefaces.context.RequestContext;
//import org.primefaces.event.ScheduleEntryMoveEvent;
//import org.primefaces.event.ScheduleEntryResizeEvent;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.model.DefaultScheduleModel;
//import org.primefaces.model.ScheduleModel;
//import yrw.ejb.ActionsEjb;
//import yrw.model.CauseEnum;
//import yrw.model.Patien;
//import yrw.model.Visit;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.faces.event.ActionEvent;
//import javax.inject.Inject;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@ManagedBean(name = "vm")
//@ViewScoped
//
//public class VM implements Serializable {
//
////    @ManagedProperty("#{dbService}")
////    private DbService dbService;
//
//    @Inject
//    private ActionsEjb actionsEjb;
//
//
//    String centerPanel = "/patiens.xhtml";
//
//    private List<Patien> patiens;
//    private List<Visit> visits;
//
//    private Patien selectedPatien;
//    private Patien newPatien;
//
//    private Visit selectedVisit;
//    private Visit newVisit;
//
//    private ScheduleModel eventModel;
//    private Visit event = new Visit();
//
//    String test = "/patiens.xhtml";
//
//    @PostConstruct
//    private void koukou() {
//
//        patiens = actionsEjb.fetchAll(Patien.class);
//        newPatien = new Patien();
//        newVisit = new Visit();
//        eventModel = new DefaultScheduleModel();
//        visits = actionsEjb.fetchAll(Visit.class);
////        for (Visit v : visits) {
////            v.setStartDate(v.getDate());
////            v.setEndDate(v.getDate());
////            v.setTitle(v.getPatien().getLastName());
////            v.setId(v.getVisit_id() + "");
////            eventModel.addEvent(v);
////        }
//    }
//
//    public void changeCenterPanel(int page) {
//        if (page == 1) {
//            centerPanel = "/patiens.xhtml";
//        } else {
//            centerPanel = "/calendar.xhtml";
//        }
//        RequestContext.getCurrentInstance().update("go");
//        System.out.print(centerPanel);
//        //
//    }
//
//    public void handleSave() {
//        actionsEjb.updateEntity(selectedPatien);
//    }
//
//
//
//    public void deleteVisit() {
//        if (selectedVisit != null && !selectedVisit.isDeleted()) {
//            selectedVisit.setDeleted(true);
//            actionsEjb.deleteEntity(selectedVisit);
//            //visits.remove(selectedVisit);
//        }
//    }
//
//    public void addPatien(ActionEvent actionEvent) {
//        if (newPatien != null) {
//            actionsEjb.createEntity(newPatien);
//            patiens.add(newPatien);
//        }
//        RequestContext.getCurrentInstance().execute("PF('addPatienDialog').hide()");
//    }
//
//    public void addVisit(ActionEvent actionEvent) {
//
////        if (newVisit != null) {
////            newVisit.setPatien(selectedPatien);
////            dbService.saveVisit(newVisit);
////            selectedPatien = dbService.getPatienWithID(selectedPatien.getId());
////            newVisit = new Visit();
////
////        }
//
//        RequestContext.getCurrentInstance().execute("PF('addVisitDialog').hide()");
//
//    }
//
//    public void addEvent(ActionEvent actionEvent) {
////        if (event.getId() == null) {
////
////            event.setTitle(event.getPatien().getLastName());
////            event.setDate(event.getStartDate());
////
////            eventModel.addEvent(event);
////            dbService.addVisit(event);
////        } else {//edit event
////            eventModel.updateEvent(event);
////            dbService.saveVisit(event);
////        }
//
//        event = new Visit();
//    }
//
//    public void onEventSelect(SelectEvent selectEvent) {
//        event = (Visit) selectEvent.getObject();
//    }
//
//    public void onDateSelect(SelectEvent selectEvent) {
//        System.out.println(selectEvent.getObject());
////        event = new Visit("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
//    }
//
//    public void onEventMove(ScheduleEntryMoveEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//
//        //addMessage(message);
//    }
//
//    public void onEventResize(ScheduleEntryResizeEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//
//        //addMessage(message);
//    }
//
//    /*  This is for using targeted dialogs. If you just need to show a message without giving preference to any
//     custom dialog, then you can do it this way:
//
//     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Message body");
//     RequestContext.getCurrentInstance().showMessageInDialog(message);*/
//
//    /*========================================================GETTERS && SSETTERS========================================================*/
//
//
//    public String getCenterPanel() {
//        return centerPanel;
//    }
//
//    public void setCenterPanel(String centerPanel) {
//        this.centerPanel = centerPanel;
//    }
//
//    public List<Patien> getPatiens() {
//        return patiens;
//    }
//
//    public void setPatiens(ArrayList<Patien> patiens) {
//        this.patiens = patiens;
//    }
//
//    public Patien getSelectedPatien() {
//        return selectedPatien;
//    }
//
//    public void setSelectedPatien(Patien selectedPatien) {
//        this.selectedPatien = selectedPatien;
//    }
//
//    public String getTest() {
//        return test;
//    }
//
//    public void setTest(String test) {
//        this.test = test;
//    }
//
//    public Patien getNewPatien() {
//        return newPatien;
//    }
//
//    public void setNewPatien(Patien newPatien) {
//        this.newPatien = newPatien;
//    }
//
//    public ScheduleModel getEventModel() {
//        return eventModel;
//    }
//
//    public void setEventModel(ScheduleModel eventModel) {
//        this.eventModel = eventModel;
//    }
//
//    public Visit getEvent() {
//        return event;
//    }
//
//    public void setEvent(Visit event) {
//        this.event = event;
//    }
//
//    public List<Visit> getVisits() {
//        return visits;
//    }
//
//    public void setVisits(List<Visit> visits) {
//        this.visits = visits;
//    }
//
//    public Visit getSelectedVisit() {
//        return selectedVisit;
//    }
//
//    public void setSelectedVisit(Visit selectedVisit) {
//        this.selectedVisit = selectedVisit;
//    }
//
//    public Visit getNewVisit() {
//        return newVisit;
//    }
//
//    public void setNewVisit(Visit newVisit) {
//        this.newVisit = newVisit;
//    }
//
//    //ENUM GETTER
//    public CauseEnum[] getCauses() {
//        return CauseEnum.values();
//    }
//
//}
