package com.saft.back.member.service;

import com.saft.back.member.entity.Member;
import com.saft.back.member.entity.MemberCommend;

public interface MemberService{
    Long registerMember(MemberCommend.RegisterMemberRequest command);
}
