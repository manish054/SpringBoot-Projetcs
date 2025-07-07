package com.springai.springAiDemo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.springAiDemo.model.Achievements;
import com.springai.springAiDemo.model.Player;

@RestController
public class Controllers {
    @GetMapping("/home")
    public String hello(){
        return "Welcome to Spring AI";
    }

    private final ChatClient chatClient;
    public Controllers(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }
    @GetMapping("/chat")
    public String getAnswer(@RequestParam String message){
        return this.chatClient.prompt()
                    .user(message)
                    .call()
                    .content();
    }

    // @GetMapping("/celebs")
    // public String getCelebDetails(@RequestParam String name){
    //     String message = """
    //     List the details of the personality {name} along
    //     with the carrier achievements. Show it in readable format
    //     """;

    //     PromptTemplate promptTemplate = new PromptTemplate(message);

    //     Prompt prompt = promptTemplate.create(Map.of("name", name));

    //     return chatClient.prompt(prompt)
    //                     .call()
    //                     .chatResponse()
    //                     .getResult()
    //                     .getOutput().getText();
                        
                            
    // }

    @Value("classpath:prompts/celepsprompt.st")
    private Resource celebPrompt;

    @GetMapping("/celebs")
    public String getCelebDetails(@RequestParam String name){

        PromptTemplate promptTemplate = new PromptTemplate(celebPrompt);

        Prompt prompt = promptTemplate.create(Map.of("name", name));

        return chatClient.prompt(prompt)
                        .call()
                        .chatResponse()
                        .getResult()
                        .getOutput().getText();
                        
                            
    }

    @Value("classpath:prompts/systemmessage.st")
    private Resource sysMessage;

    @Value("classpath:prompts/sportPormpt.st")
    private Resource sportPrompt;

    @GetMapping("/sport")
    public String getSportsDetails(@RequestParam String name){

        PromptTemplate promptTemplate = new PromptTemplate(sportPrompt);

        Prompt prompt = promptTemplate.create(Map.of("name", name));

        return chatClient.prompt(prompt)
                        .system(sysMessage)
                        .call()
                        .chatResponse()
                        .getResult()
                        .getOutput().getText();
                        
                            
    }

    @GetMapping("/player")
    //for getting the players list with achievements
    public List<Player> getPlayerDetails(@RequestParam String pName){
        BeanOutputConverter<List<Player>> converter = 
                    new BeanOutputConverter<>(new ParameterizedTypeReference<List<Player>>(){});
        String message = """
                Generate a list of career achievements for the sportsperson {sportsPerson}.
                Include the sportsperson name as the key and list achievements as the value for it {format}
                """;

        PromptTemplate template = new PromptTemplate(message);
        Prompt prompt = template.create(Map.of("sportsPerson", pName, "format", converter.getFormat()));

        Generation result = chatClient.prompt(prompt)
                .system(sysMessage)
                .call()
                .chatResponse()
                .getResult();

        return converter.convert(result.getOutput().getText());
    }

    @GetMapping("/achieve/player")
    //for getting the list of achievements
    /*
     * [
  "Scored 300 runs in a Test match against Pakistan in 2004, becoming the first Indian to score a triple century.",
  "Holds the record for the highest individual score in One Day Internationals (ODIs) with 219 runs against West Indies in 2011.",
  "Scored 2 double centuries in Test cricket.",
  "Was part of the Indian team that won the ICC T20 World Cup in 2007.",
  "Was part of the Indian team that won the ICC Cricket World Cup in 2011.",
  "Scored 15,920 runs in ODIs, making him one of the highest run-scorers in the format.",
  "Holds the record for the fastest ODI century, scored in just 52 balls against England in 2014.",
  "Named ICC ODI Player of the Year in 2010.",
  "Recipient of the Arjuna Award in 2002 for his outstanding achievements in cricket.",
  "Awarded the Padma Shri in 2010, India's fourth highest civilian award."
]
     */
    public List<String> getPlayerAchievements(@RequestParam String pName){
        
        String message = """
                Provide the list of the achievements of the player {playerName}
                """;

        PromptTemplate template = new PromptTemplate(message);
        Prompt prompt = template.create(Map.of("playerName", pName));
        return chatClient.prompt(prompt)
                         .call()
                         .entity(new ParameterizedTypeReference<List<String>>() {});
       //entity class provided to avoid the listOutputConverter 
       //return List<Achievement> to get the data in format
       /*
        [
            {"achievement":"abcd"},
            {"achievement":"xyz"},
            {"achievement":"1234"},
            .....
        ]
       */ 
    }
}
