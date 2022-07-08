package game.web.radomNumber.hard;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class HardInputNumber {

    @NotNull
    @Range(min = 1, max = 1000)
    private Integer input;

}
