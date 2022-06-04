package game.domain.login.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class Member {

    private Long id;

    @Size(min = 8, max = 20)
    private String loginId;

    @NotEmpty
    private String name;

    @Size(min = 8, max = 20)
    private String password;
}
