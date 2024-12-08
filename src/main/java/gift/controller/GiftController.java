package gift.controller;

import gift.model.GiftRequestForm;
import gift.service.PerplexityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/gift")
public class GiftController {
    private final PerplexityService perplexityService;

    public GiftController(PerplexityService perplexityService) {
        this.perplexityService = perplexityService;
    }

    @GetMapping
    public String getGiftPage(Model model) {
        model.addAttribute("giftForm", new GiftRequestForm());
        return "gift";
    }

    @PostMapping
    public String getRecommendation(@ModelAttribute GiftRequestForm giftForm, Model model) {
        // 프롬프트에 URL 관련 지침 추가
        String prompt = String.format(
                "#명령문\n" +
                        "-%s에게 어울릴만한 선물을 추천해줘\n" +
                        "-생일 선물을 추천해주고 그에 따른 의미도 설명해줘.\n" +
                        "#제약조건\n" +
                        "-%s가지 추천해줘\n" +
                        "-%s과 연관되어서 줄 수 있는 선물을 추천해줘\n" +
                        "#검색 링크 필수 규칙\n" +
                        "1. 각 상품 추천시 반드시 다음 형식으로 작성할 것:\n\n" +
                        "상품명: [정확한 제품명과 모델명 포함]\n" +
                        "검색링크: https://search.shopping.naver.com/search/all?query=[상품명과 동일한 검색어]\n" +
                        "추천이유: [추천 이유 설명]\n\n" +
                        "2. 검색 링크 작성 규칙:\n" +
                        "- 네이버쇼핑 URL만 사용할 것\n" +
                        "- 상품명을 그대로 검색어로 사용할 것\n" +
                        "- URL에 다른 텍스트나 '...' 붙이지 말 것\n" +
                        "- '상품 보러가기' 같은 텍스트 사용하지 말 것\n\n" +
                        "예시:\n" +
                        "상품명: 조말론 우드 세이지 앤 씨 솔트 100ml\n" +
                        "검색링크: https://search.shopping.naver.com/search/all?query=조말론+우드+세이지+앤+씨+솔트+100ml\n" +
                        "추천이유: [추천 이유]\n" +
                        "#입력문\n" +
                        "[대상] = %s살 %s %s",
                giftForm.getTargetName(),
                String.valueOf(giftForm.getItemCount()),
                giftForm.getRelationship(),
                String.valueOf(giftForm.getAge()),
                giftForm.getStatus(),
                giftForm.getTargetName()
        );

        String aiResponse = perplexityService.generateResponse(prompt);

        // URL을 하이퍼링크로 변환
        aiResponse = aiResponse.replaceAll(
                "검색링크: (https://search\\.shopping\\.naver\\.com/search/all\\?query=[^\\n]+)",
                "검색링크: <a href='$1' target='_blank' class='text-blue-500 hover:underline'>이 상품 검색하기</a>"
        );

        model.addAttribute("recommendation", aiResponse);
        model.addAttribute("giftForm", giftForm);

        return "gift";
    }
}