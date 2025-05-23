package com.lytmoon.springmirai.bean.deepseek;

import java.util.List;

public class DeepSeekRequest {

    private String model;
    private List<Message> messages;
    private boolean stream;

    // Constructor
    public DeepSeekRequest(String model, List<Message> messages, boolean stream) {
        this.model = model;
        this.messages = messages;
        this.stream = stream;
    }

    // Getters and setters

    public static class Message {
        private String role;
        private String content;

        // Constructor, getters, and setters
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

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
}
