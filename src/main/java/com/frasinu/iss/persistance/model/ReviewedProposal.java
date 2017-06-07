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

    @Column(name = "recommendation")
    private String recommendation;

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

    public String getRecommendation() {return recommendation;}

    public void setReview(String review) {this.review = review;}

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
