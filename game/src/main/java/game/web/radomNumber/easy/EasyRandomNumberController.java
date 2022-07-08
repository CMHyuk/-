package game.web.radomNumber.easy;

import game.domain.numbergame.number.EasyRandomNumber;
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
public class EasyRandomNumberController {

    private final EasyRandomNumber easyRandomNumber;
    List<Integer> rank = new ArrayList<>();
    int cnt = 1;
    @GetMapping("/easy-number-game")
    public String numberGame(@ModelAttribute EasyInputNumber easyInputNumber) {
        int rn = easyRandomNumber.setNum();
        log.info("숫자 맞추기 게임 사이트 접속");
        log.info("rn 생성={}", rn);
        log.info("cnt={}", cnt);

        return "game/easy/easy-number";
    }

    @PostMapping("/easy-number-game")
    public String confirmationNumber(@Validated @ModelAttribute EasyInputNumber easyInputNumber, BindingResult bindingResult, Model model) {
        int rn = easyRandomNumber.getNum();

        log.info("input={}", easyInputNumber.getInput());
        log.info("cnt={}", cnt);

        //검증 실패시 다시 입력 창
        if(bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "game/easy/easy-number";
        }

        model.addAttribute("cnt", cnt);

        if(easyInputNumber.getInput() > rn) {
            cnt++;
            bindingResult.addError(new FieldError("inputNumber", "input", "틀렸습니다 다운!"));
        } else if(easyInputNumber.getInput() < rn) {
            cnt++;
            bindingResult.addError(new FieldError("inputNumber", "input", "틀렸습니다 업!"));
        } else {
            //숫자 맞추면 시도 횟수 초기화
            rank.add(cnt);
            cnt = 1;
            log.info("rank={}", rank);
            return "game/easy/easy-result";
        }

        return "game//easy/easy-number";
    }

    @GetMapping("/easy-ranking")
    public String rank(Model model) {
        Collections.sort(rank);
        model.addAttribute("rank", rank);
        log.info("rank={}", rank);
        return "game/easy/easy-ranking";
    }
}
