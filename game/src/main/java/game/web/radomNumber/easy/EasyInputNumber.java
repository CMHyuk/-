package game.web.radomNumber.easy;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class EasyInputNumber {

    @NotNull
    @Range(min = 1, max = 10)
    private Integer input;

}
