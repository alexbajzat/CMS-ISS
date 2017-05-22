package com.frasinu.iss.persistance.model;

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
    @Column(name = "value", unique = true)
    private String value;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "proposal_keyword",
            joinColumns = {@JoinColumn(name = "proposal_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "keyword_id", nullable = false, updatable = false)})
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
}
