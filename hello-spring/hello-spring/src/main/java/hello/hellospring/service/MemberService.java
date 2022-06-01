package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional // jpa를 사용할때는 데이터를 저장, 변경할때 항상 트랜젝션이 있어야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 의존성 삽입 - Dependancy Injection
     */

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 경우 중복 회원 제거
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 화면 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findByID(memberId);
    }


}
