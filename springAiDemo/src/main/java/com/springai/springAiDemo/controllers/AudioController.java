package com.springai.springAiDemo.controllers;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest.AudioParameters.AudioResponseFormat;
import org.springframework.ai.openai.api.OpenAiAudioApi.SpeechRequest;
import org.springframework.ai.openai.api.OpenAiAudioApi.TranscriptResponseFormat;
import org.springframework.ai.openai.api.OpenAiAudioApi.TtsModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AudioController {
    private final OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;
    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;
    public AudioController(OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel,
    OpenAiAudioSpeechModel openAiAudioSpeechModel){
        this.openAiAudioTranscriptionModel = openAiAudioTranscriptionModel;
        this.openAiAudioSpeechModel = openAiAudioSpeechModel;
    }

    @GetMapping("/audio-to-text")
    public String audioTranscript (){
        OpenAiAudioTranscriptionOptions options =
            OpenAiAudioTranscriptionOptions.builder()
            .language("en")
            .responseFormat(TranscriptResponseFormat.TEXT)
            .temperature(0.5f)
            .build();

        AudioTranscriptionPrompt prompt = 
            new AudioTranscriptionPrompt(new ClassPathResource("\\audio\\Recording.m4a"), options);

        return openAiAudioTranscriptionModel.call(prompt)
                                    .getResult()
                                    .getOutput();
    }

    @GetMapping("/text-to-speech/{resource}")
    public ResponseEntity<Resource> textToSpeech(@PathVariable String resource){
        OpenAiAudioSpeechOptions options
            = OpenAiAudioSpeechOptions.builder()
                .model(TtsModel.TTS_1.getValue())
                .responseFormat(SpeechRequest.AudioResponseFormat.MP3)
                .voice(SpeechRequest.Voice.ALLOY)
                .speed(1.0f)
                .build();

        SpeechPrompt speechPrompt = 
                new SpeechPrompt(resource, options);

        SpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);

        byte[] output = response.getResult().getOutput();

        ByteArrayResource byteArrayResource = 
            new ByteArrayResource(output);

            //to download the file
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(byteArrayResource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition.attachment()
                                            .filename("Whatever.mp3")
                                            .build().toString())
                .body(byteArrayResource);
                
    }
}
