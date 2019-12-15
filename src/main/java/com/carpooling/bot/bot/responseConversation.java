package com.carpooling.bot.bot;

import java.util.List;

public class responseConversation {
    int conversation;
    List<String> options;
    List<String> responses;

    public responseConversation(int conversation, List<String> options, List<String> responses) {
        this.conversation = conversation;
        this.options = options;
        this.responses = responses;
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

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }
}
