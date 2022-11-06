package com.team.project.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryQuerydsl {

    List<Member> findAllByOrderByIdAsc(); //getAllMembers (사용안하는 것 같음)
    List<Member> findByUserId(String userId);

}
