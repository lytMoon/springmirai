package com.lytmoon.springmirai.bean.deepseek;

import java.util.List;

public class DeepSeekResponse {

    private String id;
    private List<Choice> choices;
    private long created;
    private String model;
    private String systemFingerprint;
    private String object;
    private Usage usage;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSystemFingerprint() {
        return systemFingerprint;
    }

    public void setSystemFingerprint(String systemFingerprint) {
        this.systemFingerprint = systemFingerprint;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    // Choice class
    public static class Choice {
        private String finishReason;
        private int index;
        private Message message;
        private Logprobs logprobs;

        // Getters and Setters
        public String getFinishReason() {
            return finishReason;
        }

        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public Logprobs getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(Logprobs logprobs) {
            this.logprobs = logprobs;
        }

        // Message class
        public static class Message {
            private String content;
            private String reasoningContent;
            private List<ToolCall> toolCalls;
            private String role;

            // Getters and Setters
            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getReasoningContent() {
                return reasoningContent;
            }

            public void setReasoningContent(String reasoningContent) {
                this.reasoningContent = reasoningContent;
            }

            public List<ToolCall> getToolCalls() {
                return toolCalls;
            }

            public void setToolCalls(List<ToolCall> toolCalls) {
                this.toolCalls = toolCalls;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            // ToolCall class
            public static class ToolCall {
                private String id;
                private String type;
                private Function function;

                // Getters and Setters
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Function getFunction() {
                    return function;
                }

                public void setFunction(Function function) {
                    this.function = function;
                }

                // Function class
                public static class Function {
                    private String name;
                    private String arguments;

                    // Getters and Setters
                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getArguments() {
                        return arguments;
                    }

                    public void setArguments(String arguments) {
                        this.arguments = arguments;
                    }
                }
            }
        }

        // Logprobs class
        public static class Logprobs {
            private List<Token> content;

            // Getters and Setters
            public List<Token> getContent() {
                return content;
            }

            public void setContent(List<Token> content) {
                this.content = content;
            }

            // Token class
            public static class Token {
                private String token;
                private double logprob;
                private List<Integer> bytes;
                private List<TopLogprob> topLogprobs;

                // Getters and Setters
                public String getToken() {
                    return token;
                }

                public void setToken(String token) {
                    this.token = token;
                }

                public double getLogprob() {
                    return logprob;
                }

                public void setLogprob(double logprob) {
                    this.logprob = logprob;
                }

                public List<Integer> getBytes() {
                    return bytes;
                }

                public void setBytes(List<Integer> bytes) {
                    this.bytes = bytes;
                }

                public List<TopLogprob> getTopLogprobs() {
                    return topLogprobs;
                }

                public void setTopLogprobs(List<TopLogprob> topLogprobs) {
                    this.topLogprobs = topLogprobs;
                }

                // TopLogprob class
                public static class TopLogprob {
                    private String token;
                    private double logprob;
                    private List<Integer> bytes;

                    // Getters and Setters
                    public String getToken() {
                        return token;
                    }

                    public void setToken(String token) {
                        this.token = token;
                    }

                    public double getLogprob() {
                        return logprob;
                    }

                    public void setLogprob(double logprob) {
                        this.logprob = logprob;
                    }

                    public List<Integer> getBytes() {
                        return bytes;
                    }

                    public void setBytes(List<Integer> bytes) {
                        this.bytes = bytes;
                    }
                }
            }
        }
    }

    // Usage class
    public static class Usage {
        private int completionTokens;
        private int promptTokens;
        private int promptCacheHitTokens;
        private int promptCacheMissTokens;
        private int totalTokens;
        private CompletionTokensDetails completionTokensDetails;

        // Getters and Setters
        public int getCompletionTokens() {
            return completionTokens;
        }

        public void setCompletionTokens(int completionTokens) {
            this.completionTokens = completionTokens;
        }

        public int getPromptTokens() {
            return promptTokens;
        }

        public void setPromptTokens(int promptTokens) {
            this.promptTokens = promptTokens;
        }

        public int getPromptCacheHitTokens() {
            return promptCacheHitTokens;
        }

        public void setPromptCacheHitTokens(int promptCacheHitTokens) {
            this.promptCacheHitTokens = promptCacheHitTokens;
        }

        public int getPromptCacheMissTokens() {
            return promptCacheMissTokens;
        }

        public void setPromptCacheMissTokens(int promptCacheMissTokens) {
            this.promptCacheMissTokens = promptCacheMissTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }

        public void setTotalTokens(int totalTokens) {
            this.totalTokens = totalTokens;
        }

        public CompletionTokensDetails getCompletionTokensDetails() {
            return completionTokensDetails;
        }

        public void setCompletionTokensDetails(CompletionTokensDetails completionTokensDetails) {
            this.completionTokensDetails = completionTokensDetails;
        }

        // CompletionTokensDetails class
        public static class CompletionTokensDetails {
            private int reasoningTokens;

            // Getters and Setters
            public int getReasoningTokens() {
                return reasoningTokens;
            }

            public void setReasoningTokens(int reasoningTokens) {
                this.reasoningTokens = reasoningTokens;
            }
        }
    }
}
