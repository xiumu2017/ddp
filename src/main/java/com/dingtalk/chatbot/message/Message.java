package com.dingtalk.chatbot.message;

/**
 *
 * @author dustin
 * @date 2017/3/17
 */
public interface Message {

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    String toJsonString();
}
