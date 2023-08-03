package com.saft.back.member.entity;

import com.saft.back.member.interfaces.MemberDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class MemberCommend {
    @Getter @Setter
    @ToString
    public static class RegisterMemberRequest{
        private String username;
        private String nickname;
        private String gender;
        private RegisterMemberSocialRequest memberSocial;

        public Member toEntity(){
            return Member.builder()
                    .username(username)
                    .nickname(nickname)
                    .gender(gender)
                    //.memberSocial(memberSocial.toEntity())
                    .build();
        }

    }

    @Getter @Setter @ToString
    public static class RegisterMemberSocialRequest{
        private String socialType;
        private String email;

        public MemberSocial toEntity(Member member){
            return MemberSocial.builder()
                    .socialType(socialType)
                    .email(email)
                    .member(member)
                    .build();
        }
    }

}
