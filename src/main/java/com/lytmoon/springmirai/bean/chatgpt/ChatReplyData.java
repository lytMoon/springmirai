package com.lytmoon.springmirai.bean.chatgpt;




import java.util.List;

public class ChatReplyData {
    private List<Choice> choices;
    private Integer created;
    private String id;
    private String model;
    private String object;
    private String system_fingerprint;
    private Usage usage;
    private String viewHolder;
    private String userAsk;

    // Getters and setters
    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getSystem_fingerprint() {
        return system_fingerprint;
    }

    public void setSystem_fingerprint(String system_fingerprint) {
        this.system_fingerprint = system_fingerprint;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(String viewHolder) {
        this.viewHolder = viewHolder;
    }

    public String getUserAsk() {
        return userAsk;
    }

    public void setUserAsk(String userAsk) {
        this.userAsk = userAsk;
    }
}

class Choice {
    private String finish_reason;
    private int index;
    private Object logprobs;
    private Message message;

    // Getters and setters
    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Object logprobs) {
        this.logprobs = logprobs;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

class Message {
    private String content;
    private String role;

    // Getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


class Usage {
    private int adjust_total;
    private int completion_tokens;
    private int final_total;
    private int pre_token_count;
    private int pre_total;
    private int prompt_tokens;
    private int total_tokens;

    // Getters and setters
    public int getAdjust_total() {
        return adjust_total;
    }

    public void setAdjust_total(int adjust_total) {
        this.adjust_total = adjust_total;
    }

    public int getCompletion_tokens() {
        return completion_tokens;
    }

    public void setCompletion_tokens(int completion_tokens) {
        this.completion_tokens = completion_tokens;
    }

    public int getFinal_total() {
        return final_total;
    }

    public void setFinal_total(int final_total) {
        this.final_total = final_total;
    }

    public int getPre_token_count() {
        return pre_token_count;
    }

    public void setPre_token_count(int pre_token_count) {
        this.pre_token_count = pre_token_count;
    }

    public int getPre_total() {
        return pre_total;
    }

    public void setPre_total(int pre_total) {
        this.pre_total = pre_total;
    }

    public int getPrompt_tokens() {
        return prompt_tokens;
    }

    public void setPrompt_tokens(int prompt_tokens) {
        this.prompt_tokens = prompt_tokens;
    }

    public int getTotal_tokens() {
        return total_tokens;
    }

    public void setTotal_tokens(int total_tokens) {
        this.total_tokens = total_tokens;
    }
}