package com.saft.back.member.entity;

import com.saft.back.common.exception.InvalidParamException;
import com.saft.back.common.model.AbstractEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "nickname", "gender", "memberSocialList"})
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class Member extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<MemberSocial> memberSocialList = new ArrayList<>();

    @Getter
    @RequiredArgsConstructor
    public enum Gender {
        MEN("남자"),
        WOMEN("여자");

        private final String retunGender;
    }

    @Builder
    public Member(String username, String nickname, String gender, MemberSocial memberSocial) {
        if (!StringUtils.hasText(username)) throw new InvalidParamException("Member.username");
        if (!StringUtils.hasText(nickname)) throw new InvalidParamException("Member.nickname");
        if (!StringUtils.hasText(gender)) throw new InvalidParamException("Member.gneder");
        //if (memberSocial != null) throw new InvalidParamException("Member.memberSocial") ;

        this.username = username;
        this.nickname = nickname;

        if("M".equals(gender.toUpperCase())){
            this.gender = Gender.MEN;
        }else if(("W").equals(gender.toUpperCase())){
            this.gender = Gender.WOMEN;
        }
        this.memberSocialList.add(memberSocial);
    }

    public void addMemberSocial(MemberSocial memberSocial) {
        this.memberSocialList.add(memberSocial) ;
    }
}
