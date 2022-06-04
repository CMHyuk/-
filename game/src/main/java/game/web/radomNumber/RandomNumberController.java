package game.web.radomNumber;

import game.domain.numbergame.number.InputNumber;
import game.domain.numbergame.number.RandomNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RandomNumberController {

    private final RandomNumber randomNumber;

    List<Integer> rank = new ArrayList<>();
    int cnt = 1;
    @GetMapping("/number-game")
    public String numberGame(@ModelAttribute InputNumber inputNumber) {
        int rn = randomNumber.setNum();
        log.info("숫자 맞추기 게임 사이트 접속");
        log.info("rn 생성={}", rn);
        log.info("cnt={}", cnt);

        return "game/number";
    }

    @PostMapping("/number-game")
    public String confirmationNumber(@Validated @ModelAttribute InputNumber inputNumber, BindingResult bindingResult, Model model) {
        int rn = randomNumber.getNum();

        log.info("input={}", inputNumber.getInput());
        log.info("cnt={}", cnt);

        //검증 실패시 다시 입력 창
        if(bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "game/number";
        }

        model.addAttribute("cnt", cnt);

        if(inputNumber.getInput() > rn) {
            cnt++;
            bindingResult.addError(new FieldError("inputNumber", "input", "틀렸습니다 다운!"));
        } else if(inputNumber.getInput() < rn) {
            cnt++;
            bindingResult.addError(new FieldError("inputNumber", "input", "틀렸습니다 업!"));
        } else {
            //숫자 맞추면 시도 횟수 초기화
            rank.add(cnt);
            cnt = 1;
            log.info("rank={}", rank);
            return "game/correctResult";
        }

        return "game/number";
    }

    @GetMapping("/number-game-ranking")
    public String rank(Model model) {
        Collections.sort(rank);
        model.addAttribute("rank", rank);
        log.info("rank={}", rank);
        return "game/ranking";
    }
}
