package game.web.radomNumber.normal;

import game.domain.numbergame.number.NormalRandomNumber;
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
public class NormalRandomNumberController {

    private final NormalRandomNumber normalRandomNumber;

    List<Integer> rank = new ArrayList<>();
    int cnt = 1;
    @GetMapping("/normal-number-game")
    public String numberGame(@ModelAttribute NormalInputNumber normalInputNumber) {
        int rn = normalRandomNumber.setNum();
        log.info("숫자 맞추기 게임 사이트 접속");
        log.info("rn 생성={}", rn);
        log.info("cnt={}", cnt);

        return "game/normal/normal-number";
    }

    @PostMapping("/normal-number-game")
    public String confirmationNumber(@Validated @ModelAttribute NormalInputNumber normalInputNumber, BindingResult bindingResult, Model model) {
        int rn = normalRandomNumber.getNum();

        log.info("input={}", normalInputNumber.getInput());
        log.info("cnt={}", cnt);

        //검증 실패시 다시 입력 창
        if(bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "game/normal/normal-number";
        }

        model.addAttribute("cnt", cnt);

        if(normalInputNumber.getInput() > rn) {
            cnt++;
            bindingResult.addError(new FieldError("inputNumber", "input", "틀렸습니다 다운!"));
            return "game/normal/normal-number";
        }

        if(normalInputNumber.getInput() < rn) {
            cnt++;
            bindingResult.addError(new FieldError("inputNumber", "input", "틀렸습니다 업!"));
            return "game/normal/normal-number";
        }

        if(normalInputNumber.getInput() == rn){
            //숫자 맞추면 시도 횟수 초기화
            rank.add(cnt);
            cnt = 1;
            log.info("rank={}", rank);
            return "redirect:/normal-ranking";
        }

        return "game/normal/normal-number";
    }

    @GetMapping("/normal-ranking")
    public String rank(Model model) {
        Collections.sort(rank);
        model.addAttribute("rank", rank);
        log.info("rank={}", rank);
        return "game/normal/normal-ranking";
    }

}
