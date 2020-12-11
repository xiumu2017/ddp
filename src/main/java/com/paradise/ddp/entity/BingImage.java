
package com.paradise.ddp.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Paradise
 */
@Getter
@Setter
public class BingImage {

    private String url;
    private String urlbase;
    private String copyright;
    private String copyrightlink;
    private String title;
    private String startdate;
    private String fullstartdate;
    private String enddate;
    private String hsh;

    @Override
    public String toString() {
        return "BingImage{" +
                "url='" + url + '\'' +
                ", urlbase='" + urlbase + '\'' +
                ", copyright='" + copyright + '\'' +
                ", copyrightlink='" + copyrightlink + '\'' +
                ", title='" + title + '\'' +
                ", startdate='" + startdate + '\'' +
                ", fullstartdate='" + fullstartdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", hsh='" + hsh + '\'' +
                '}';
    }

}
