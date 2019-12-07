package com.carpooling.bot.bot;

import java.util.List;

public class responseConversation {
    int conversation;
    List<String> options;

    public responseConversation(int conversation, List<String> options) {
        this.conversation = conversation;
        this.options = options;
    }

    public int getConversation() {
        return conversation;
    }

    public void setConversation(int conversation) {
        this.conversation = conversation;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
