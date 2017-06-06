package com.frasinu.iss.persistance.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bjz on 5/19/2017.
 */
@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    private String value;


    @Fetch(FetchMode.SELECT)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "proposal_topic",
            joinColumns = {@JoinColumn(name = "topic_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "proposal_id", nullable = false, updatable = false)})
    private List<Proposal> proposals;

    public Topic() {
    }

    public Topic(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    @Override
    public String toString() {
        return  value;
    }
}

