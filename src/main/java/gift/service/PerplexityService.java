package gift.service;

import gift.model.PerplexityRequest;
import gift.model.PerplexityResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // 요청 메시지 설정 - 프롬프트 수정
        PerplexityRequest request = new PerplexityRequest();
        List<PerplexityRequest.Message> messages = new ArrayList<>();

        // 시스템 메시지에 URL 형식에 대한 지침 추가
        PerplexityRequest.Message systemMessage = new PerplexityRequest.Message();
        systemMessage.setRole("system");
        systemMessage.setContent(
                "당신은 선물 추천 전문가입니다. 각 추천 상품에 대해 다음 사항을 반드시 포함해주세요:\n" +
                        "1. 상품명과 설명\n" +
                        "2. 추천 이유\n" +
                        "3. 실제 구매 가능한 전체 URL (예: https://www.coupang.com/vp/products/...)\n" +
                        "링크가 없는 경우 '공식 홈페이지에서 구매 가능합니다'로 표시해주세요."
        );
        messages.add(systemMessage);

        // 사용자 메시지 추가
        PerplexityRequest.Message userMsg = new PerplexityRequest.Message();
        userMsg.setRole("user");
        userMsg.setContent(userMessage);
        messages.add(userMsg);

        request.setMessages(messages);

        // API 호출
        HttpEntity<PerplexityRequest> entity = new HttpEntity<>(request, headers);

        try {
            PerplexityResponse response = restTemplate.postForObject(
                    API_URL,
                    entity,
                    PerplexityResponse.class
            );

            if (response != null && !response.getChoices().isEmpty()) {
                String responseText = response.getChoices().get(0).getMessage().getContent();
                // URL을 하이퍼링크로 변환
                responseText = responseText.replaceAll(
                        "https://[^\\s\n]+",
                        "<a href='$0' target='_blank' class='text-blue-500 hover:underline'>상품 보러가기</a>"
                );
                return responseText;
            }

            return "죄송합니다. 응답을 생성하는데 문제가 발생했습니다.";

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}