package gift.model;

import lombok.Data;
import java.util.List;

@Data
public class PerplexityResponse {
    private String id;
    private String model;
    private long created;
    private Usage usage;
    private String object;
    private List<Choice> choices;

    @Data
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
    }

    @Data
    public static class Choice {
        private int index;
        private String finishReason;
        private Message message;
        private Message delta;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
    }
}
