
package com.paradise.ddp.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * @author Paradise
 */
@Getter
@Setter
public class Data {
    private String id;
    private String content;
    private String popularity;
    private String[] matchTags;
    private String recommendedReason;
    private String cacheAt;
    private Origin origin;

    @Override
    public String toString() {
        origin = origin == null ? new Origin() : origin;
        return "Data{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", popularity='" + popularity + '\'' +
                ", matchTags=" + Arrays.toString(matchTags) +
                ", recommendedReason='" + recommendedReason + '\'' +
                ", cacheAt='" + cacheAt + '\'' +
                ", origin=" + origin.toString() +
                '}';
    }
}
