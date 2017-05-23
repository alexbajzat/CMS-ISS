package com.frasinu.iss.utils.parser;

import com.frasinu.iss.exception.ParserException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Florin on 5/20/2017.
 */
public class DocumentParser {
    public static PaperHolder parseDocx(InputStream inputStream) {

        XWPFDocument docx = null;
        try {
            docx = new XWPFDocument(inputStream);
        } catch (IOException e) {
            throw new ParserException("Parser erorr:"+e);
        }
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        List<XWPFParagraph> text = docx.getParagraphs();


        Iterator<XWPFParagraph> it = text.iterator();
        String title = it.next().getParagraphText();
        StringBuilder content = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        while (it.hasNext()) {
            //line separator is appended only for new paragraphs because string builder wipes new lines
            content.append(it.next().getParagraphText() + lineSeparator);
        }
        return new PaperHolder(title, content.toString());
    }
}
