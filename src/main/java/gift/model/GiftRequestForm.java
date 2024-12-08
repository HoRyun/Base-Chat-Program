package gift.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GiftRequestForm {
    private Long giftId;
    private String targetName;
    private int age;
    private String relationship;
    private String status;
    private int itemCount;           // 추천 받을 선물 개수 추가
    private String requestMessage;    // AI에게 보낸 프롬프트
    private String aiResponse;        // AI의 응답
    private LocalDateTime timestamp;
}