package com.saft.back.member.interfaces;

import com.saft.back.member.entity.MemberCommend;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MemberDtoMapper {
    // register
    @Mappings({@Mapping(source = "memberSocial", target = "memberSocial")})
    MemberCommend.RegisterMemberRequest of(MemberDto.RegisterMemberRequest member);

}
