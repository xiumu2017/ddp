package com.dingtalk.chatbot.message;

/**
 * @author dustin
 * @date 2017/3/19
 */
public class ActionCardAction {
    private String title;
    private String actionUrl;

    public ActionCardAction(String text, String actionUrl) {
        this.title = text;
        this.actionUrl = actionUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
}
