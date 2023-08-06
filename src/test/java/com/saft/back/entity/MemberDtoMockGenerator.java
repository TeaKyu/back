package com.saft.back.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saft.back.member.interfaces.MemberDto;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MemberDtoMockGenerator {

    static MemberDto.RegisterMemberRequest memberRequset(){
        MemberDto.RegisterMemberSocialRequest socialRequest= new MemberDto.RegisterMemberSocialRequest();
        socialRequest.setSocialType("google");
        socialRequest.setEmail("aaa@naver.com");

        MemberDto.RegisterMemberRequest memberRequest = new MemberDto.RegisterMemberRequest();
        memberRequest.setGender("m");
        memberRequest.setNickname("nick");
        memberRequest.setUsername("name");
        memberRequest.setMemberSocial(socialRequest);

        return memberRequest;
    }


    static MultiValueMap<String, String> convert(ObjectMapper objectMapper, Object dto) { // (2)
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {}); // (3)
            params.setAll(map); // (4)

            return params;
        } catch (Exception e) {
            System.out.println("Url Parameter 변환중 오류가 발생했습니다. requestDto={}"+ dto + e);
            //log.error("Url Parameter 변환중 오류가 발생했습니다. requestDto={}", dto, e);
            throw new IllegalStateException("Url Parameter 변환중 오류가 발생했습니다.");
        }
    }
}
