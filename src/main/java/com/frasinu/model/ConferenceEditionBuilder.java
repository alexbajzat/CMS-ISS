package com.frasinu.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by cory_ on 16-May-17.
 */
public class ConferenceEditionBuilder {
    private Integer id;
    private  String name;
    private  Date conferenceStartDate;
    private  Date conferenceEndDate;
    private  Date abstractsDeadline;
    private  Date fullPapersDeadline;
    private  Date biddingDeadline;
    private  Date evaluationDeadline;

    public ConferenceEditionBuilder() {
    }

    public ConferenceEdition build() {
        return new ConferenceEdition(id, name,conferenceStartDate,conferenceEndDate,abstractsDeadline,fullPapersDeadline,biddingDeadline,evaluationDeadline);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConferenceEditionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ConferenceEditionBuilder setConferenceStartDate(Date conferenceStartDate) {
        this.conferenceStartDate = conferenceStartDate;
        return this;

    }

    public ConferenceEditionBuilder setConferenceEndDate(Date conferenceEndDate) {
        this.conferenceEndDate = conferenceEndDate;
        return this;

    }

    public ConferenceEditionBuilder setAbstractsDeadline(Date abstractsDeadline) {
        this.abstractsDeadline = abstractsDeadline;
        return this;

    }

    public ConferenceEditionBuilder setFullPapersDeadline(Date fullPapersDeadline) {
        this.fullPapersDeadline = fullPapersDeadline;
        return this;

    }

    public ConferenceEditionBuilder setBiddingDeadline(Date biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
        return this;

    }

    public ConferenceEditionBuilder setEvaluationDeadline(Date evaluationDeadline) {
        this.evaluationDeadline = evaluationDeadline;
        return this;

    }
}
