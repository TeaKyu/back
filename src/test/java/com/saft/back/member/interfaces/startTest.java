package com.saft.back.member.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saft.back.RestDocsConfiguration;
import com.saft.back.entity.MemberDtoMockGenerator;
import com.saft.back.member.facade.MemberFacade;
import com.saft.back.member.service.MemberService;
import com.saft.back.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

//@AutoConfigureMockMvc
//@Transactional
@WebMvcTest(
        controllers = {
                MemberController.class
        })
@AutoConfigureMockMvc
@AutoConfigureRestDocs // restdocs 활성화 해주는 어노테이션
@Import(RestDocsConfiguration.class) // 이전에 작성한 suffix관련 bean 사용
public class startTest {

    @InjectMocks
    private MemberController memberController;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberFacade memberFacade;

    @MockBean
    private MemberDto MemberDto;

    @MockBean
    private MemberDtoMapper memberDtoMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void memberSave() throws Exception {
        // response field 설명 명세
        FieldDescriptor[] reviews = getReviewFieldDescriptors();

        MemberDto.RegisterMemberRequest memberRequest = MemberDtoMockGenerator.memberRequset();

        //MultiValueMap<String, String> convert = MemberDtoMockGenerator.convert(objectMapper, memberRequest);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/member/insertMember")
                        //.params(s)
                        //.accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andDo(MockMvcRestDocumentation.document("/members"),
                            // 응답에 field값에 대해 명세한다.
                            // 현재는 따로 만들어서 했지만, PayloadDocumentation.fieldWithPath().description(),~~
                            // 로 진행해도 된다.
                            PayloadDocumentation.responseFields(reviews),
                            // 요청 헤더의 명세를 한다.
                            HeaderDocumentation.requestHeaders(HeaderDocumentation.headerWithName(HttpHeaders.AUTHORIZATION).description("hasdf")),
                            // 응답 헤더의 명세를 한다.
                            HeaderDocumentation.responseHeaders(HeaderDocumentation.headerWithName("hihi").description("헤더"))));



                    )

                ;

    }
    private FieldDescriptor[] getReviewFieldDescriptors() {
        return new FieldDescriptor[]{
                fieldWithPath("[]name").description("이름"),
                fieldWithPath("[]age").description("나이")
        };
    }
}