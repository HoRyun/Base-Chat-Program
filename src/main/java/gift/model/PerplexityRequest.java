package gift.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class PerplexityRequest {
    private String model;
    private List<Message> messages;
    private int maxTokens = 1024;
    private double temperature = 0.2;
    private double topP = 0.9;
    private boolean returnCitations = true;
    private List<String> searchDomainFilter = List.of("perplexity.ai");
    private boolean returnImages = false;
    private boolean returnRelatedQuestions = false;
    private String searchRecencyFilter = "month";
    private int topK = 0;
    private boolean stream = false;
    private int presencePenalty = 0;
    private int frequencyPenalty = 1;

    @Data
    public static class Message {
        private String role;
        private String content;
    }
}