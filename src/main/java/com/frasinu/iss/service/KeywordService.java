package com.frasinu.iss.service;

import com.frasinu.iss.persistance.model.Keyword;
import com.frasinu.iss.persistance.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjz on 5/20/2017.
 */
@Service
public class KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;

    public List<Keyword> findProposalForKeywords(List<String> keywords) {
        return keywordRepository.findProposalForKeywords(keywords);
    }
}
