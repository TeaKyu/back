package com.saft.back.test;

import com.saft.back.entity.MemberDtoMockGenerator;
import com.saft.back.member.interfaces.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class MemberApiTest extends SpringTestSupport{

    @Test
    public void memberSave() throws Exception {
        MemberDto.RegisterMemberRequest memberRequest = MemberDtoMockGenerator.memberRequset();

        //MultiValueMap<String, String> convert = MemberDtoMockGenerator.convert(objectMapper, memberRequest);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/member/insertMember")
                        //.params(s)
                        //.accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("id").description("Member id")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Member id"),
                                fieldWithPath("email").description("Email"),
                                fieldWithPath("nickname").description("Name")
                        )
                ))
                ;

    }
}
