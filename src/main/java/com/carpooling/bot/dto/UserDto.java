package com.carpooling.bot.dto;

import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.domain.CpUser;

public class UserDto {
    private Integer user_id;
    private CpPerson person_id;
    private String bot_user_id;
    private String chat_user_id;
    private Integer conversation_id;
    private Integer subconversation_id;
    private String last_message_sent;
    private String last_message_received;

    public UserDto() {

    }

    public UserDto(CpUser cpUser){
        this.user_id = cpUser.getUserId();
        this.person_id = cpUser.getPersonId();
        this.bot_user_id = cpUser.getBotUserId();
        this.chat_user_id = cpUser.getChatUserId();
        this.conversation_id = cpUser.getConversationId();
        this.subconversation_id = cpUser.getSubconversationId();
        this.last_message_received = cpUser.getLastMessageReceived();
        this.last_message_sent = cpUser.getLastMessageSent();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public CpPerson getPerson_id() {
        return person_id;
    }

    public void setPerson_id(CpPerson person_id) {
        this.person_id = person_id;
    }

    public String getBot_user_id() {
        return bot_user_id;
    }

    public void setBot_user_id(String bot_user_id) {
        this.bot_user_id = bot_user_id;
    }

    public String getChat_user_id() {
        return chat_user_id;
    }

    public void setChat_user_id(String chat_user_id) {
        this.chat_user_id = chat_user_id;
    }

    public Integer getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(Integer conversation_id) {
        this.conversation_id = conversation_id;
    }

    public Integer getSubconversation_id() {
        return subconversation_id;
    }

    public void setSubconversation_id(Integer subconversation_id) {
        this.subconversation_id = subconversation_id;
    }

    public String getLast_message_sent() {
        return last_message_sent;
    }

    public void setLast_message_sent(String last_message_sent) {
        this.last_message_sent = last_message_sent;
    }

    public String getLast_message_received() {
        return last_message_received;
    }

    public void setLast_message_received(String last_message_received) {
        this.last_message_received = last_message_received;
    }
}
