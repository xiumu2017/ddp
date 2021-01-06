
package com.paradise.ddp.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Paradise
 */
@Getter
@Setter
@ToString
public class BingResult {
    private List<BingImage> images;
    private String tooltips;

}
