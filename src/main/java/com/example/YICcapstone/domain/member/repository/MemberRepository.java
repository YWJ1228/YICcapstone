package com.example.YICcapstone.domain.member.repository;

import com.example.YICcapstone.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Member findByNickname(String email);
    List<Member> findByName(String name);

    //boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}