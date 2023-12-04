//package com.exercise.first.ex01;
//
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository1 memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(value = false) // 롤백을 false
//    public void testMember(){
//        Member1 member = new Member1();
//        member.setUsername("홍길동");
//        long member_id = memberRepository.save(member);
//        Assertions.assertThat(memberRepository.find(member_id)).isEqualTo(member);
//    }
//
//}