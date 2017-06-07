package com.frasinu.iss.persistance.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bjz on 5/19/2017.
 */
@Entity
@Table(name = "keyword")
public class Keyword {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;
    @Column(name = "value")
    private String value;




    @Fetch(FetchMode.SELECT)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "proposal_keyword",
            joinColumns = {@JoinColumn(name = "keyword_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "proposal_id", nullable = false, updatable = false)})
    private List<Keyword> proposals;

    public Keyword() {
    }

    public Keyword(String value) {
        this.id = id;
        this.value = value;
    }


    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public List<Keyword> getProposals() {
        return proposals;
    }

    @Override
    public String toString() {
        return  value;
    }
}
