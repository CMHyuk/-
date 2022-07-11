package game.web.member.remove;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class InputText {

    @NotEmpty(message = "회원 삭제를 입력하세요.")
    private String input;
}
