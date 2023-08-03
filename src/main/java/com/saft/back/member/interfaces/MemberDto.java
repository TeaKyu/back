package com.saft.back.member.interfaces;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class MemberDto {
    @Getter @Setter @ToString
    public static class RegisterMemberRequest{
        @NotNull
        private String username;
        @NotNull
        private String nickname;
        @NotNull
        private String gender;

        private RegisterMemberSocialRequest memberSocial;

    }

    @Getter @Setter @ToString
    public static class RegisterMemberSocialRequest{
        @NotNull
        private String socialType;
        @NotNull
        private String email;
    }

}
