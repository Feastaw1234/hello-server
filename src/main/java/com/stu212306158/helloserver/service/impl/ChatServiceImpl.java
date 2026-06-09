package com.stu212306158.helloserver.service.impl;


import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.stu212306158.helloserver.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("你是专业友好的中文助手，回答简洁明了")
                .defaultOptions(DashScopeChatOptions.builder().withTopP(0.7).build())
                .build();
    }

    @Override
    public String chat(String message) {
        return chatClient.prompt(message).call().content();
    }
}