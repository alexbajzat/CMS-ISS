package com.frasinu.iss.validator;

import com.frasinu.iss.exception.InexistentException;
import com.frasinu.iss.persistance.model.Proposal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.io.File;

/**
 * Created by Florin on 6/6/2017.
 */
@Component
public class ProposalValidator implements Validator<Proposal>{
    @Value("${files.root}")
    private String root;

    public void validate(Proposal obj) throws InexistentException {
        File f = new File(root+"\\"+obj.getFullPaper());

        if(!f.exists())
            throw new InexistentException("File not found!");
    }
}
