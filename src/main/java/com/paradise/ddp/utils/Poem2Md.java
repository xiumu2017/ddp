package com.paradise.ddp.utils;

import com.dingtalk.chatbot.message.MarkdownMessage;
import com.dingtalk.chatbot.message.Message;
import com.paradise.ddp.entity.Origin;
import com.paradise.ddp.entity.PoemEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author dzhang
 */
public class Poem2Md {
    public static Message poem2Md(PoemEntity poemEntity) {
        MarkdownMessage message = new MarkdownMessage();
        message.setTitle(poemEntity.getData().getContent());

        Origin origin = poemEntity.getData().getOrigin();
        message.add(MarkdownMessage.getHeaderText(1, origin.getTitle()));
        message.add(MarkdownMessage.getHeaderText(3, origin.getAuthor() + " " + origin.getDynasty()));
        message.add(MarkdownMessage.getBoldText(Arrays.toString(origin.getContent())));
        if (StringUtils.isNotEmpty(origin.getTranslate())) {
            message.add("> " + origin.getTranslate());
        } else {
            message.add(MarkdownMessage.getBoldText("暂无翻译~"));
        }
        message.add("  ");
//        message.add("![Paradise](http://s.cn.bing.net/az/hprichbg/rb/SchoolGirls_ZH-CN10666418108_1920x1080.jpg)");
        return message;
    }
}
