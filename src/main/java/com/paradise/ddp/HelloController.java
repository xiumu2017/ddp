package com.paradise.ddp;

import com.dingtalk.chatbot.DentalCabotClient;
import com.paradise.ddp.entity.BingResult;
import com.paradise.ddp.entity.Data;
import com.paradise.ddp.entity.PoemEntity;
import com.paradise.ddp.utils.BingImageUtils;
import com.paradise.ddp.utils.Poem2Md;
import com.paradise.ddp.utils.PoemSendUtils;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping("/index")
    public String index() {
        return "Hello,Server is running...";
    }

    @RequestMapping("/push")
    public String push() {
        DentalCabotClient client = new DentalCabotClient();
        String token = "ae6476d73f64ddd5e96daf17d9acbedf7c0ea24e8eef1e4e7d468b564618d58c";
        try {
            PoemEntity poemEntity = PoemSendUtils.getPoem();
            BingResult bingResult = BingImageUtils.getBingImage(String.valueOf(0), "1");
            client.send(token, Poem2Md.poem2Md(poemEntity));
            client.send(token, bingResult2Msg(bingResult.getImages().get(0)));
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
