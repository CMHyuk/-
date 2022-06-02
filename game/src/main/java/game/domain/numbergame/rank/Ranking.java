package game.domain.numbergame.rank;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
public class Ranking {

    List<Integer> rank = new ArrayList<>();
    private int count;

    public int getCount() {
        return count;
    }

    public int setCount() {
        return count = 1;
    }

    public void plus() {
        count++;
    }

    public void add(int count) {
        rank.add(count);
    }

    public void clear() {
        count = 1;
    }

    public void sort() {
        Collections.sort(rank);
    }

}
