package game.domain.numbergame.number;

import org.springframework.stereotype.Service;

@Service
public class NormalRandomNumber extends RandomNumber {

    @Override
    public int setNum() {
        rn = random.nextInt(100)+1; //1~100 난수 생성
        return rn;
    }
    @Override
    public int getNum() {
        return rn;
    }
}
