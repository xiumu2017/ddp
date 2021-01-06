
package com.paradise.ddp.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Paradise
 */
@Getter
@Setter
public class BingResult {
    private List<BingImage> images;
    private String tooltips;

}
