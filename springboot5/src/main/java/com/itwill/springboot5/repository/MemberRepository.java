package com.itwill.springboot5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.springboot5.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	@EntityGraph(attributePaths = "roles")
	Optional<Member> findByUsername(String username);
}
