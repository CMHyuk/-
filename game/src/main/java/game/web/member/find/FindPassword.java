package game.web.member.find;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FindPassword {

    @NotEmpty
    private String inputId;
}
