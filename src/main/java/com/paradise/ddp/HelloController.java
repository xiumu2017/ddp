package com.paradise.ddp;

import com.dingtalk.chatbot.DentalCabotClient;
import com.paradise.ddp.entity.BingResult;
import com.paradise.ddp.entity.Data;
import com.paradise.ddp.entity.PoemEntity;
import com.paradise.ddp.utils.BingImageUtils;
import com.paradise.ddp.utils.Poem2Md;
import com.paradise.ddp.utils.PoemSendUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.paradise.ddp.utils.BingImageUtils.bingResult2Msg;

/**
 * @author dzhang
 */
@RestController
@Slf4j
public class HelloController {

    @Value("${bingImgToken}")
    public String token;
    @Value("${secret}")
    public String secret;

    @RequestMapping("/index")
    public String index() {
        return "Hello,Server is running...";
    }

    @RequestMapping("/push")
    public String push(String param, Integer index) {
        DentalCabotClient client = new DentalCabotClient();
        try {
            PoemEntity poemEntity = PoemSendUtils.getPoem();
            BingResult bingResult = BingImageUtils.getBingImage(String.valueOf(index), "1");
            if (StringUtils.isNotBlank(param)) {
                client.send(token, Poem2Md.poem2Md(poemEntity), secret);
            }
            client.send(token, bingResult2Msg(bingResult.getImages().get(0)), secret);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return "Push fail: " + e.getLocalizedMessage();
        }
        return "Push success!";
    }

    @RequestMapping("/test")
    public String test(String id, Data data) {
        log.info(id);
        log.info(data.toString());
        return data.toString();
    }
}
