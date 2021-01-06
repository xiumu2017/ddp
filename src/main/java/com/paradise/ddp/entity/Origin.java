
package com.paradise.ddp.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * 古诗词 接口返回
 *
 * @author Paradise
 */
@Getter
@Setter
public class Origin {
    private String title;
    private String dynasty;
    private String author;
    private String[] content;
    private String translate;

    public String getContentStr() {
        StringBuilder builder = new StringBuilder();
        if (content != null) {
            for (String str : content) {
                builder.append(str);
            }
        }
        return builder.toString();
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
