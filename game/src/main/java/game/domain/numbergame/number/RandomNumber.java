package game.domain.numbergame.number;

import java.util.Random;

public abstract class RandomNumber {

    Random random = new Random();
    int rn;

    public abstract int setNum();

    public abstract int getNum();
}
