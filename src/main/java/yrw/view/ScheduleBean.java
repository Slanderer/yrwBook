package yrw.view;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import yrw.ejb.ActionsEjb;
import yrw.model.Patien;
import yrw.model.Visit;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ivarsamoulis
 * on 24/5/2017.
 */
@Named
@ViewScoped
public class ScheduleBean implements Serializable {

    @Inject
    private ActionsEjb actionsEjb;

    private ScheduleModel eventModel;

    private DefaultScheduleEvent event = new DefaultScheduleEvent();

    private List<Visit> visits;
    private List<Patien> patiens;

    @PostConstruct
    public void init() {
        patiens = actionsEjb.fetchAll(Patien.class);
        visits = actionsEjb.fetchAll(Visit.class);

        eventModel = new DefaultScheduleModel();
        for (Visit v : visits) {
            event = new DefaultScheduleEvent(v.getPatien().getLastName() + " " + v.getPatien().getFirstName(),
                    v.getDate(), v.getDate(), v);
            eventModel.addEvent(event);
        }
        event = new DefaultScheduleEvent(null, null, null, new Visit());

    }

    public void setTime(Visit visit, String field, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(event.getStartDate());
        if (field.equals("hh")) {
            cal.set(Calendar.HOUR_OF_DAY, value);
        } else {
            cal.set(Calendar.MINUTE, value);
        }
        event.getStartDate().setTime(cal.getTimeInMillis());
        event.getEndDate().setTime(cal.getTimeInMillis());

        visit.setDate(cal.getTime());
    }


    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            event.setTitle(((Visit) event.getData()).getPatien().getLastName()+" "+((Visit) event.getData()).getPatien().getFirstName());
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }
        actionsEjb.updateEntity((Visit)event.getData());
        event = new DefaultScheduleEvent(null, null, null, new Visit());
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (DefaultScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject(), new Visit());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Patien> getPatiens() {
        return patiens;
    }

    public void setPatiens(List<Patien> patiens) {
        this.patiens = patiens;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(DefaultScheduleEvent event) {
        this.event = event;
    }
}
