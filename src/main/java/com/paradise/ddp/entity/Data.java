
package com.paradise.ddp.entity;

import java.util.Arrays;

public class Data {
    private String id;
    private String content;
    private String popularity;
    private String[] matchTags;
    private String recommendedReason;
    private String cacheAt;
    private Origin origin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String[] getMatchTags() {
        return matchTags;
    }

    public void setMatchTags(String[] matchTags) {
        this.matchTags = matchTags;
    }

    public String getRecommendedReason() {
        return recommendedReason;
    }

    public void setRecommendedReason(String recommendedReason) {
        this.recommendedReason = recommendedReason;
    }

    public String getCacheAt() {
        return cacheAt;
    }

    public void setCacheAt(String cacheAt) {
        this.cacheAt = cacheAt;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
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
