package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot5.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Test
	void testDependencyInjection() {
		assertThat(memberRepository).isNotNull();
		log.debug(memberRepository.toString());

		assertThat(passwordEncoder).isNotNull();
		log.debug(passwordEncoder.toString());

	}

//	@Test
//	void saveTest() {
//		Member member = Member.builder().username("test").password(passwordEncoder.encode("test")).email("test@qwe.com")
//				.build();
//		member.addRole(MemberRole.USER);
//		memberRepository.save(member);
//	}

//	@Test
	@Transactional
	void findAllTest() {
		List<Member> memberList = memberRepository.findAll();
		memberList.forEach(i -> System.out.println(i + "\n" + i.getRoles() + "\n\n"));
	}

	@Test
	void findByUsername() {
		Optional<Member> member = memberRepository.findByUsername("test1");
		if (member.isPresent()) {
			System.out.println(member.get() + "\n" + member.get().getRoles());
		} else {
			System.out.println("없음");
		}
	}
}
