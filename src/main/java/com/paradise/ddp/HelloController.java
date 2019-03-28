package com.paradise.ddp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dzhang
 */
@RestController
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        return "Hello,Server is running...";
    }
}
