package game.web.radomNumber;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingHomeController {

    @GetMapping("/ranking")
    public String ranking() {
        return "game/ranking";
    }
}
