package com.ngs.openai.controller;

import com.ngs.openai.service.OpenAIService;
import com.ngs.openai.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
public class OpenAIController {
    private final OpenAIService openAIService;

    @Autowired
    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping
    public Mono<String> chat(@RequestBody String prompt) {
        return openAIService.ask(prompt);
    }

}