package com.example.YICcapstone.domain.member.repository;

import com.example.YICcapstone.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);
    //Optional<Member> findByNickname(String nickname);
    List<Member> findByName(String name);

    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);

    @Override
    ArrayList<Member> findAll();

    Optional<Member> findByRefreshToken(String refreshToken);
}