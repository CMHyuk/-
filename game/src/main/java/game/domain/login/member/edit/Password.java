package game.domain.login.member.edit;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class Password {

    @NotEmpty
    private String nowPassword;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z]).{8,16}$")
    private String newPassword;

    @NotEmpty
    private String checkNewPassword;
}
