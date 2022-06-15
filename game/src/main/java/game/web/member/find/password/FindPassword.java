package game.web.member.find.password;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FindPassword {

    @NotEmpty
    private String inputId;
}
