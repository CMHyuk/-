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

    private String checkPassword;

    @Pattern(regexp = "^[0-9]{6}", message = "생년월일 6자리를 입력하세요.")
    private String birth;

    @Pattern(regexp = "^[0-9]{11}")
    private String phoneNumber;

    public Member(String loginId, String name, String password, String birth, String phoneNumber) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
    }
}
