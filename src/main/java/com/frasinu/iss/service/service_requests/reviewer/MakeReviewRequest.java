package com.frasinu.iss.service.service_requests.reviewer;

import com.frasinu.iss.persistance.model.Result;

/**
 * Created by Andrei on 07-Jun-17.
 */
public class MakeReviewRequest {
    private int idReviewer;
    private int idPaper;
    private Result result;
    private String recommendation;


    public MakeReviewRequest(int idReviewer, int idPaper, Result result, String recommendation) {
        this.idReviewer = idReviewer;
        this.idPaper = idPaper;
        this.result = result;
        this.recommendation = recommendation;
    }

    public int getIdReviewer() {
        return idReviewer;
    }

    public int getIdPaper() {
        return idPaper;
    }

    public Result getResult() {
        return result;
    }

    public String getRecommendation() {
        return recommendation;
    }
}
