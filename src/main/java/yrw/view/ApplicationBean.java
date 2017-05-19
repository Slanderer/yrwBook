package yrw.view;

import yrw.ejb.ActionsEjb;
import yrw.model.VisitCause;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivarsamoulis
 * on 18/5/2017.
 */
@Named
@ApplicationScoped
public class ApplicationBean implements Serializable {

    @Inject
    private ActionsEjb actionsEjb;

    private List<VisitCause> visitCauses = new ArrayList<>();

    @PostConstruct
    public void init() {
        visitCauses = actionsEjb.fetchAll(VisitCause.class);
    }

    public List<VisitCause> getVisitCauses() {
        return visitCauses;
    }

    public void setVisitCauses(List<VisitCause> visitCauses) {
        this.visitCauses = visitCauses;
    }
}
