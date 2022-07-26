package game.domain.numbergame.number.service;

import game.domain.numbergame.number.RandomNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameService {

    private final RandomNumber randomNumber;

    public boolean big(RandomNumber randomNumber, int inputNumber) {
        if (randomNumber.getNum() > inputNumber) {
            return true;
        }
        return false;
    }

    public boolean same(RandomNumber randomNumber, int inputNumber) {
        if (randomNumber.getNum() == inputNumber) {
            return true;
        }
        return false;
    }

    public boolean less(RandomNumber randomNumber, int inputNumber) {
        if (randomNumber.getNum() < inputNumber) {
            return true;
        }
        return false;
    }
}
