package game.domain.numbergame.number;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class EasyRandomNumber implements RandomNumber {
    Random random = new Random();
    int rn;

    @Override
    public int setNum() {
        rn = random.nextInt(10)+1; //1~10 난수 생성
        return rn;
    }
    @Override
    public int getNum() {
        return rn;
    }
}
