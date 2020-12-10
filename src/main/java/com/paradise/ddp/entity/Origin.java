
package com.paradise.ddp.entity;

import java.util.Arrays;

/**
 * 古诗词 接口返回
 *
 * @author Paradise
 */
public class Origin {
    private String title;
    private String dynasty;
    private String author;
    private String[] content;
    private String translate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getContent() {
        return content;
    }

    public String getContentStr() {
        StringBuilder builder = new StringBuilder();
        if (content != null) {
            for (String str : content) {
                builder.append(str);
            }
        }
        return builder.toString();
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public String getTranslate() {
        if (translate != null) {
            return translate
                    .replaceAll("\",\"", "")
                    .replaceAll("\\[", "")
                    .replaceAll("]", "");
        }
        return null;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "title='" + title + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", author='" + author + '\'' +
                ", content=" + Arrays.toString(content) +
                ", translate='" + translate + '\'' +
                '}';
    }
}
