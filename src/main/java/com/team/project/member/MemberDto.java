package com.team.project.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class JoinMemberForm {
        @NotBlank(message = "아이디를 입력해주세요.")
        private String userId;
        @NotBlank(message = "닉네임을 입력해주세요.")
        private String nickname;
        @NotBlank(message = "이메일 주소를 입력해주세요.")
        @Size(max = 25, message = "다시 입력해주세요")
        private String email;
        @NotBlank(message = "패스워드를 입력해주세요.")
        @Size(min = 10, max = 12, message = "0자~12자리의 영문(대소문자)+숫자+특수문자 중 2종류 이상을 조합하여 사용할 수 있습니다.")
        private String pass;
        @NotBlank(message = "패스워드를 입력해주세요")
        @Size(min = 10, max = 12, message = "비밀번호 확인을 위해 다시 한 번 입력해 주세요.")
        private String passCheck;

    }
}
