package com.saft.back.member.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saft.back.entity.MemberDtoMockGenerator;
import com.saft.back.member.facade.MemberFacade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {
                MemberController.class
        })
@AutoConfigureRestDocs//(outputDir = "target/snippets")
@Import(RestDocsAutoConfiguration.class)
public class MemberControllerTest {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private MemberDtoMapper memberDtoMapper;

    @MockBean
    private MemberFacade memberFacade;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MockMvc mockMvc;

    @Test
    public void member_저장() throws Exception {
        MemberDto.RegisterMemberRequest memberRequest = MemberDtoMockGenerator.memberRequset();
        mockMvc.perform(post("/api/v1/member/insertMember")
                        .content(objectMapper.writeValueAsString(memberRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("memberSave",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        responseFields(
                                fieldWithPath("result").description("Description for field1"),
                                fieldWithPath("data").description("Description for field2"),
                                fieldWithPath("message").description("Description for field2"),
                                fieldWithPath("errorCode").description("Description for field2")
                        )
                ));
    }

}