package com.paradise.ddp.utils;

import chatbot.message.MarkdownMessage;
import chatbot.message.Message;
import com.paradise.ddp.entity.Origin;
import com.paradise.ddp.entity.PoemEntity;
import org.apache.commons.lang3.StringUtils;

/**
 * @author dzhang
 */
public class Poem2Md {
    /**
     * 根据古诗词接口返回的实体类生成钉钉md message
     *
     * @param poemEntity 古诗词实体
     * @return Message
     */
    public static Message poem2Md(PoemEntity poemEntity) {
        MarkdownMessage message = new MarkdownMessage();
        message.setTitle(poemEntity.getData().getContent());
        Origin origin = poemEntity.getData().getOrigin();
        message.add(MarkdownMessage.getHeaderText(1, origin.getTitle()));
        message.add(MarkdownMessage.getHeaderText(3, origin.getAuthor() + " " + origin.getDynasty()));
        message.add(MarkdownMessage.getBoldText(origin.getContentStr()));
        if (StringUtils.isNotEmpty(origin.getTranslate())) {
            message.add(MarkdownMessage.getReferenceText(origin.getTranslate()));
        }
        message.add("  ");
        return message;
    }
}
