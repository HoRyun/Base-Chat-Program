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
        return "gift";
    }
}