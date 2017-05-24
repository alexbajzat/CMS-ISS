package com.frasinu.iss.persistance.model;

/**
 * Created by bjz on 5/21/2017.
 */
public class SteeringCommitteeMemberBuilder {
    private Integer id;
    private String rank;
    private ConferenceEdition conferenceEdition;
    private User user;

    public SteeringCommitteeMemberBuilder() {
    }

    public SteeringCommitteeMember build() {
        return new SteeringCommitteeMember(id,rank,user,conferenceEdition);
    }

    public SteeringCommitteeMemberBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public SteeringCommitteeMemberBuilder setAffiliation(String rank) {
        this.rank = rank;
        return this;
    }


    public SteeringCommitteeMemberBuilder setConferenceEdition(ConferenceEdition conferenceEdition) {
        this.conferenceEdition = conferenceEdition;
        return this;

    }

    public SteeringCommitteeMemberBuilder setUser(User user) {
        this.user = user;
        return this;

    }
}

