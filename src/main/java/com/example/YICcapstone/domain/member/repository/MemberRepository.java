package com.example.YICcapstone.domain.member.repository;

import com.example.YICcapstone.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByPassword(String password);
    Optional<Member> findByNickname(String nickname);

    boolean existsByUsername(String username);
}