package com.springai.springAiDemo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedBackSentimentAnalysis {

    private final ChatClient chatClient;

    public FeedBackSentimentAnalysis(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/sentiment/{feedback}")
    public String getSentiment(@PathVariable String feedback){
        String prompt = String.format("""
            Analyze the sentiment of the following text and respond with only
            one word: POSITIVE, NEGATIVE OR NEUTRAL.
            Also provide a sentiment score between -1 and 1 where:
            -1 is most NEGATIVE
            0 is NEUTRAL
            1 is most POSITIVE
            
            Format the response as: SENTIMENT_TYPE|SCORE
            
            Text to analyze: %s""", feedback);

        String response = chatClient.prompt(prompt).call()
                            .content();
        
        return response;
    }
}
