package com.saft.back.member.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saft.back.entity.MemberDtoMockGenerator;
import com.saft.back.member.facade.MemberFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(
        controllers = {
                MemberController.class
        })
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
class ttttTest {

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

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/member/insertMember")
                        //.params(s)
                        //.accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(
                        document("post-create", // 5
                        requestFields( // 6
                                fieldWithPath("title").description("Post 제목"), // 7
                                fieldWithPath("content").description("Post 내용").optional() // 8
                        )
                )
        ;

    }
}