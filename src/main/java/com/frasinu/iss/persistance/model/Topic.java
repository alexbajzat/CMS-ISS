package com.frasinu.iss.persistance.model;

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

    @Column(name = "value", unique = true)
    private String value;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "proposal_topic",
            joinColumns = {@JoinColumn(name = "proposal_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "topic_id", nullable = false, updatable = false)})
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
    public String toString(){
        return value;
    }
}

