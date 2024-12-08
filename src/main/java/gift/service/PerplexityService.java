package gift.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PerplexityService {
    @Value("${perplexity.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.perplexity.ai/chat/completions";
    private final RestTemplate restTemplate;

    public PerplexityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateResponse(String userMessage) {
            return "null";
    }
}