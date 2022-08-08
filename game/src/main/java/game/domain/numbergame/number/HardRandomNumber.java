package game.domain.numbergame.number;

import org.springframework.stereotype.Service;
@Service
public class HardRandomNumber extends RandomNumber {

    @Override
    public int setNum() {
        rn = random.nextInt(1000)+1; //1~1000 난수 생성
        return rn;
    }
    @Override
    public int getNum() {
        return rn;
    }
}
