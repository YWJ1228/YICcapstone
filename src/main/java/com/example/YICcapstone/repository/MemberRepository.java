package com.example.YICcapstone.repository;

import com.example.YICcapstone.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}