package com.dingtalk.chatbot.demo;

import com.dingtalk.chatbot.DentalCabotClient;
import com.paradise.ddp.utils.SendResult;
import com.dingtalk.chatbot.message.TextMessage;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by dustin on 2017/3/18.
 */
public class TextMessageTest {

    private DentalCabotClient client = new DentalCabotClient();

    @Test
    public void testSendTextMessage() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        SendResult result = client.send(TestConfig.PARADISE, message);
        System.out.println(result);
    }

    @Test
    public void testSendTextMessageWithAt() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        ArrayList<String> atMobiles = new ArrayList<String>();
        atMobiles.add("15655189198");
        message.setAtMobiles(atMobiles);

        SendResult result = client.send(TestConfig.PARADISE, message);
        System.out.println(result);
    }

    @Test
    public void testSendTextMessageWithAtAll() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        message.setIsAtAll(true);

        SendResult result = client.send(TestConfig.PARADISE, message);
        System.out.println(result);
    }

    @Test
    public void testSendTextMessageWithAtAndAtAll() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        ArrayList<String> atMobiles = new ArrayList<String>();
        atMobiles.add("15655189198");
        message.setAtMobiles(atMobiles);
        message.setIsAtAll(true);

        SendResult result = client.send(TestConfig.PARADISE, message);
        System.out.println(result);
    }
}