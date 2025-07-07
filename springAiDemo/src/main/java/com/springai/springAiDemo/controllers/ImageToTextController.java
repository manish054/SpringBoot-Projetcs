package com.springai.springAiDemo.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageToTextController {

    private final ChatModel chatModel;

    public ImageToTextController(ChatModel chatModel){
        this.chatModel = chatModel;
    }

    @GetMapping("/imagetotext")
    public String description(){
      String observation =  ChatClient.create(chatModel)
                    .prompt()
                    .user(usrSpcs -> usrSpcs.text("Explain what you can see in this image")
                                            .media(MimeTypeUtils.IMAGE_JPEG, new ClassPathResource("images\\ProfileImage.jpeg")))
                    .call()
                    .content();
     
        return observation;
    }
}
