package com.frasinu.iss.persistance.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created by bjz on 5/9/2017.
 */
@Entity
@Table(name = "proposal")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "abstract")
    private String abstractPaper;
    @Column(name = "full_paper")
    private String fullPaper;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "author_proposal",
            joinColumns = {@JoinColumn(name = "proposal_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "author_id", nullable = false, updatable = false)})
    private List<Author> authors;


    Proposal() {
    }

    public static ProposalBuilder builder() {
        return new ProposalBuilder();
    }

    Proposal(Integer id, String title, String abstractPaper, String fullPaper, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.abstractPaper = abstractPaper;
        this.fullPaper = fullPaper;
        this.authors = authors;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractPaper() {
        return abstractPaper;
    }

    public String getFullPaper() {
        return fullPaper;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
