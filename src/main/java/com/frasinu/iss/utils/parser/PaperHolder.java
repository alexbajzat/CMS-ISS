package com.frasinu.iss.utils.parser;

/**
 * Created by Andrei on 20-May-17.
 */
public class PaperHolder {
    private String title;
    private String content;

    public PaperHolder(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PaperHolder{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
