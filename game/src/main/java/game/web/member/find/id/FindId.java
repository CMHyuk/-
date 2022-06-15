package game.web.member.find.id;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FindId {

    @NotEmpty
    private String inputName;

    @NotEmpty
    private String inputBirth;

}
