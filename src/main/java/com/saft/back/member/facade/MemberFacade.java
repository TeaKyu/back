package com.saft.back.member.facade;

import com.saft.back.member.entity.Member;
import com.saft.back.member.entity.MemberCommend;
import com.saft.back.member.entity.MemberSocial;
import com.saft.back.member.repository.MemberSocialRepository;
import com.saft.back.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;

    public Long registerMember(MemberCommend.RegisterMemberRequest request) {
        return memberService.registerMember(request);
    }

}
