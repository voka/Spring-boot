package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByID(Long id);
    Optional<Member> findByName(String name);
    /// Java 8에 추가된 기능으로 Null을 그대로 반환하는 방법보다 이 방법을 선호함.
    List<Member> findAll();
    void clearStore();
}
