package com.ngs.openai.service;

import com.ngs.openai.model.ChatMessage;
import com.ngs.openai.model.ChatRequest;
import com.ngs.openai.model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Service
public class OpenAIService {

    private String apiKey;
    private final WebClient webClient;


    public OpenAIService(@Value("${api-url}") String apiUrl,
                         @Value("${spring.ai.openai.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
        this.apiKey = apiKey;
    }
    public Mono<String> ask(String prompt) {
        ChatRequest request = new ChatRequest("gpt-3.5-turbo",
                List.of(new ChatMessage("user", prompt)));

        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(2))
                                .filter(throwable -> throwable instanceof WebClientResponseException.TooManyRequests)
                )
                .map(response -> response.getChoices().get(0).getMessage().getContent());
    }
}
