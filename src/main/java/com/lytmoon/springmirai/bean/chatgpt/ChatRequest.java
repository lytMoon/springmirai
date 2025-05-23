package com.lytmoon.springmirai.bean.chatgpt;


import java.util.List;

public class ChatRequest {
    private String model;
    private List<MessageSend> messages;
    private boolean safe_mode;
    private int max_tokens;

    public ChatRequest(String model, List<MessageSend> messages, boolean safe_mode, int max_tokens) {
        this.model = model;
        this.messages = messages;
        this.safe_mode = safe_mode;
        this.max_tokens = max_tokens;
    }

    // Getters and setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<MessageSend> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageSend> messages) {
        this.messages = messages;
    }

    public boolean isSafe_mode() {
        return safe_mode;
    }

    public void setSafe_mode(boolean safe_mode) {
        this.safe_mode = safe_mode;
    }

    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }
}

class MessageSend {
    private String role;
    private String content;

    public MessageSend(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getters and setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
