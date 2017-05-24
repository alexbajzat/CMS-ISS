package com.frasinu.iss.persistance.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cory_ on 23-May-17.
 */
@Entity
@Table(name = "steering_committee_member")
public class SteeringCommitteeMember {
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rank")
    private String rank;

    @ManyToOne()
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "id_conference_edition", nullable = false)
    private ConferenceEdition conferenceEdition;


    public SteeringCommitteeMember() {
    }

    SteeringCommitteeMember(Integer id, String rank,User user,ConferenceEdition conferenceEdition) {
        this.id = id;
        this.rank = rank;
        this.user=user;
        this.conferenceEdition=conferenceEdition;
    }

    public static SteeringCommitteeMemberBuilder builder() {
        return new SteeringCommitteeMemberBuilder();
    }

    public Integer getId() {
        return id;
    }



    public User getUser() {
        return user;
    }

    public String getRank() {
        return rank;
    }


    public ConferenceEdition getConferenceEdition() {
        return conferenceEdition;
    }
}
