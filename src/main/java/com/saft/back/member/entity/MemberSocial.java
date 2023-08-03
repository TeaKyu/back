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
@ToString(of = {"id", "socialType", "email", "member"})
@SequenceGenerator(
        name = "MEMBER_SOCIAL_SEQ_GENERATOR",
        sequenceName = "MEMBER_SOCIAL_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class MemberSocial  extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SOCIAL_SEQ_GENERATOR")
    @Column(name = "id")
    private Long id;

    //@Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    //@Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @Getter
    @RequiredArgsConstructor
    public enum SocialType {
        GOOGLE("구글"),
        FACEBOOK("패이스북"),
        APPLE("애플");

        private final String retuenSocial;
    }


    @Builder
    public MemberSocial(String socialType, String email, Member member) {
        //if (memberId == null) throw new InvalidParamException("MemberSocial.memberId");
        if (!StringUtils.hasText(socialType)) throw new InvalidParamException("MemberSocial.socialTxt");
        if (!StringUtils.hasText(email)) throw new InvalidParamException("MemberSocial.email");

        if("google".equals(socialType)){
            this.socialType = SocialType.GOOGLE;
        }else if("faceBook".equals(socialType)){
            this.socialType = SocialType.FACEBOOK;
        } else if ("apple".equals(socialType)) {
            this.socialType = SocialType.APPLE;
        }
        this.email = email;

        this.member = member;
    }

}
