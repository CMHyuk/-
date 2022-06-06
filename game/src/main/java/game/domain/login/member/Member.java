package game.domain.login.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class Member {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]{8,12}$")
    private String loginId;

    @NotEmpty
    private String name;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z]).{8,16}$")
    private String password;
}
