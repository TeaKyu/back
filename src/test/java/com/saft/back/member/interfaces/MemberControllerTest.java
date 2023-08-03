package com.saft.back.member.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saft.back.entity.MemberDtoMockGenerator;
import com.saft.back.member.entity.Member;
import com.saft.back.member.facade.MemberFacade;
import com.saft.back.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@AutoConfigureMockMvc
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)

 */
@WebMvcTest(
        controllers = {
                MemberController.class
        })
public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @MockBean
    private MemberFacade memberFacade;

    @MockBean
    private MemberDto MemberDto;

    @MockBean
    private MemberDtoMapper memberDtoMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MockMvc mockMvc;

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
                .andDo(MockMvcResultHandlers.print());

    }
}