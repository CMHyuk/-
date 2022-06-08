package game.domain.numbergame.number;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomNumber {

    Random random = new Random();
    int rn;

    public int setNum() {
        rn = random.nextInt(100)+1; //1~100 난수 생성
        return rn;
    }

    public int getNum() {
        return rn;
    }
}
