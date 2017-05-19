package com.frasinu.iss.service.service_requests.proposal;

/**
 * Created by bjz on 5/18/2017.
 */
public class CreateProposalForAuthorRequest {
    private Integer idAuthor;
    private Integer idProposal;

    public CreateProposalForAuthorRequest(Integer idAuthor, Integer idProposal) {
        this.idAuthor = idAuthor;
        this.idProposal = idProposal;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public Integer getIdProposal() {
        return idProposal;
    }
}
