package com.exercise.first.ex02.service;

import com.exercise.first.ex02.Member;
import com.exercise.first.ex02.repository.MemberRepository;
import com.exercise.first.ex02.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("kim");
        //When
        memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findOne(member.getId()));
    }
    // IllegalStateException 에러 제외
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //When
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.
        //Then
        fail("예외가 발생해야 한다."); // 얘가 출력되면 테스트 실패
    }
}