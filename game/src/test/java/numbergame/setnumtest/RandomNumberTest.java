package numbergame.setnumtest;

import game.domain.numbergame.number.RandomNumber;
import org.junit.jupiter.api.Test;

public class RandomNumberTest {

    RandomNumber randomNumber = new RandomNumber();

    @Test
    void randomNumTest() {
        int random = randomNumber.setNum();
        System.out.println(random);
    }
}
