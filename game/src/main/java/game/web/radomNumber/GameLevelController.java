package game.web.radomNumber;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameLevelController {

    @GetMapping("/level")
    public String gameLevel() {
        return "game/level";
    }
}
