package com.saft.back.member.interfaces;

import com.saft.back.common.response.CommonResponse;
import com.saft.back.member.facade.MemberFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberFacade memberFacade;
    private final MemberDtoMapper memberDtoMapper;

    @PostMapping("/insertMember")
    public CommonResponse MemberSave(@RequestBody @Validated MemberDto.RegisterMemberRequest request){
        log.info("== START MemberSave ===");
        var memberCommend = memberDtoMapper.of(request);
        Long memberId = memberFacade.registerMember(memberCommend);
        log.info("memberId :: " + memberId);
        log.info("== END MemberSave ===");
        return CommonResponse.success("ok");
    }


}
