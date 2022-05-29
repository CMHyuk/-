package numbergame.number;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class InputNumber {

    @NotNull
    @Range(min = 1, max = 100)
    private Integer input;

}
