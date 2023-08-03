package com.saft.back.member.repository;

import com.saft.back.member.entity.MemberSocial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSocialRepository extends JpaRepository<MemberSocial, Long> {
}
