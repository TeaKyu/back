package com.saft.back.member.repository;

import com.saft.back.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>{
    //List<Member> findByUsername(String username);
}
