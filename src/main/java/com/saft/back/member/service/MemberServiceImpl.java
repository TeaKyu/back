package com.saft.back.member.service;

import com.saft.back.member.entity.Member;
import com.saft.back.member.entity.MemberCommend;
import com.saft.back.member.entity.MemberSocial;
import com.saft.back.member.repository.MemberRepository;
import com.saft.back.member.repository.MemberSocialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MemberSocialRepository memberSocialRepository;

    @Override
    @Transactional
    public Long registerMember(MemberCommend.RegisterMemberRequest command) {
        Member member = memberRepository.save(command.toEntity());
        MemberSocial mm = command.getMemberSocial().toEntity(member);
        memberSocialRepository.save(mm);
        return member.getId();
    }
}
