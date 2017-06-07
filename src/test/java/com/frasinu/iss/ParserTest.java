package com.frasinu.iss;

import com.frasinu.iss.utils.parser.DocumentParser;
import com.frasinu.iss.utils.parser.PaperHolder;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Betty on 6/7/2017.
 */
public class ParserTest extends BaseTestClass {

    @Test
    public void test() throws IOException{
        File file = new File("C:\\baza\\test.docx");
        InputStream is=new FileInputStream(file);
        PaperHolder paper=DocumentParser.parseDocx(is);

        assert(paper.getTitle().equals("TestTitle"));
        assert(paper.getContent().contains("TestContent-lala"));

    }

}
