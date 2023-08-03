package com.saft.back.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saft.back.member.entity.Member;
import com.saft.back.member.entity.MemberSocial;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@SpringBootTest
@Transactional
public class MemberTest {
    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @PersistenceUnit
    EntityManagerFactory enf; //데이터 로딩 되었는지 아닌지 확인하는

    @Test
    public void insertMemberData() {
        queryFactory = new JPAQueryFactory(em);
/*
        MemberSocial memberSocial = new MemberSocial("google","aa@gmail.com");
        em.persist(memberSocial);

        Member mem = new Member("user","nick","m");
        System.out.println(">>>>>>>>>>>>>>>>>");
        mem.toString();
        em.persist(mem);


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
        List<Long> result = queryFactory
                .select(member.id)
                .from(member)
                .fetch();
        for (Long s : result) {
            System.out.println(s);
        }
 */
    }



}