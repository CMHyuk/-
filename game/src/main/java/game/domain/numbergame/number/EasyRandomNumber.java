package game.domain.numbergame.number;

import org.springframework.stereotype.Service;
@Service
public class EasyRandomNumber extends RandomNumber{

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
