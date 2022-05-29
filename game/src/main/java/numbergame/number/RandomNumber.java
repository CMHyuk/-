package numbergame.number;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumber {

    Random random = new Random();

    int rn;

    public int setNum() {
        rn = random.nextInt(100)+1; //1~99 난수 생성
        return rn;
    }

    public int getNum() {
        return rn;
    }
}
