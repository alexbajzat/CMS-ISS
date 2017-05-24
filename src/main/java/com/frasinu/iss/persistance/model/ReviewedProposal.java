package com.frasinu.iss.persistance.model;

import javax.persistence.*;

/**
 * Created by bjz on 5/21/2017.
 */
@Entity
@Table(name = "reviewed_proposal",
        uniqueConstraints = @UniqueConstraint(columnNames = {"reviewer", "proposal"})
)
public class ReviewedProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "reviewer")
    private Reviewer reviewer;
    @ManyToOne
    @JoinColumn(name = "proposal")
    private Proposal proposal;

    @Column(name = "review")
    private String review;

    ReviewedProposal(){}

    public ReviewedProposal(Reviewer reviewer, Proposal proposal, String review) {
        this.reviewer = reviewer;
        this.proposal = proposal;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public String getReview() {
        return review;
    }
}
