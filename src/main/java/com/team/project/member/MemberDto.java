package com.team.project.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class JoinMemberForm {
        private String userId;
        private String nickname;
        private String email;
        private String pass;
    }
}
