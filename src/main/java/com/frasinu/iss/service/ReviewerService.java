package com.frasinu.iss.service;

import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.persistance.model.ReviewedProposal;
import com.frasinu.iss.persistance.model.Reviewer;
import com.frasinu.iss.persistance.repository.ReviewedProposalRepository;
import com.frasinu.iss.persistance.repository.ReviewerRepository;
import com.frasinu.iss.service.service_requests.reviewer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjz on 5/18/2017.
 */
@Service
public class ReviewerService {
    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private ReviewedProposalRepository reviewedProposalRepository;

    public Reviewer addReviewer(CreateReviewerRequest createReviewerRequest) {
        Reviewer reviewer = Reviewer.builder()
                .setAffiliation(createReviewerRequest.getAffiliation())
                .setEmail(createReviewerRequest.getEmail())
                .setWebpage(createReviewerRequest.getWebpage())
                .setUser(createReviewerRequest.getUser())
                .setConferenceEdition((createReviewerRequest.getConferenceEdition()))
                .build();

        return reviewerRepository.save(reviewer);
    }

    public Reviewer updateReviewer(UpdateReviewerRequest updateReviewerRequest) throws InexistentException {

        Reviewer reviewer = Reviewer.builder()
                .setId(updateReviewerRequest.getIdOfReviewerToUpdate())
                .setAffiliation(updateReviewerRequest.getAffiliation())
                .setEmail(updateReviewerRequest.getEmail())
                .setWebpage(updateReviewerRequest.getWebpage())
                .setUser(updateReviewerRequest.getUser())
                .setConferenceEdition((updateReviewerRequest.getConferenceEdition()))
                .build();


        if (reviewerRepository.findOne(updateReviewerRequest.getIdOfReviewerToUpdate()) == null) {
            throw new InexistentException("No such Reviewer!");
        }
        return reviewerRepository.save(reviewer);
    }

    public List<Reviewer> getAllReviewers() {
        return null;
    }

    public Reviewer findByUserAndEditionId(FindByUserAndEditionIdRequest findByUserAndEditionIdRequest){
        return reviewerRepository.findByUserAndEditionId(findByUserAndEditionIdRequest.getIdUser(),findByUserAndEditionIdRequest.getIdEdition());
    }

    public Reviewer findById(FindReviewerByIdRequest findByUserIdRequest){
        return reviewerRepository.findOne(findByUserIdRequest.getId());
    }

    public List<Reviewer> getAllReviewersForEdition(FindReviewersByEditionRequest findReviewersByEditionRequest){
        return reviewerRepository.findByEditionId(findReviewersByEditionRequest.getIdEdition());
    }

    public boolean assignPaperToReviewer(AssignPaperToReviewerRequest assignPaperToReviewerRequest){
        int idReviewer = assignPaperToReviewerRequest.getIdReviewer();
        int idPaper = assignPaperToReviewerRequest.getIdPaper();
        ReviewedProposal reviewedProposal = reviewedProposalRepository.findByReviewerAndProposal(idReviewer, idPaper);
        if (reviewedProposal != null)
            return false;
        String result = assignPaperToReviewerRequest.getResult();
        reviewedProposalRepository.addReviewPropposal(idReviewer, idPaper, result);
        return true;
    }

    public boolean makeReview(MakeReviewRequest makeReviewRequest){
        int idReviewer = makeReviewRequest.getIdReviewer();
        int idPaper = makeReviewRequest.getIdPaper();
        ReviewedProposal reviewedProposal = reviewedProposalRepository.findByReviewerAndProposal(idReviewer, idPaper);
        if (reviewedProposal == null)
            return false;

        reviewedProposal.setReview(makeReviewRequest.getResult().getName());
        reviewedProposal.setRecommendation(makeReviewRequest.getRecommendation());
        reviewedProposalRepository.save(reviewedProposal);
        return true;
    }

    public List<ReviewedProposal> getReviewdProposals(GetAllReviewedProposalsRequest getAllReviewedProposalsRequest){
        return reviewedProposalRepository.findAllReviewedProposals(getAllReviewedProposalsRequest.getIdReviewer());
    }

    public ReviewedProposal getReviewedProposal(GetReviewedProposalRequest getReviewedProposalRequest){
        return reviewedProposalRepository.findByReviewerAndProposal(getReviewedProposalRequest.getIdReviewer(), getReviewedProposalRequest.getIdPaper());
    }

    public void deleteReviewer(Integer id) {
        reviewerRepository.delete(id);
    }
}
