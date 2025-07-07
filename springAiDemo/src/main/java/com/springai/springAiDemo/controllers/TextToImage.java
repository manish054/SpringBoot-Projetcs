package com.springai.springAiDemo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextToImage {

    public final ImageModel imageModel;

    public TextToImage(ImageModel imageModel){
        this.imageModel = imageModel;
    }

    @GetMapping("/image/{prompt}")
    public String getImage(@PathVariable String prompt){
       ImageResponse response = imageModel.call(new ImagePrompt(prompt, OpenAiImageOptions.builder()
                                                                    .withN(1)
                                                                    .width(1024)
                                                                    .height(1024)
                                                                    .quality("hd")
                                                                    .build()));  
        return response.getResult().getOutput().getUrl();
    }
}
