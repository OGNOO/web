package com.itwill.springboot5.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwill.springboot5.domain.Member;
import com.itwill.springboot5.dto.RegisterDto;
import com.itwill.springboot5.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;

	private final PasswordEncoder passwordEncoder;

	public Member createMember(RegisterDto registerDto) {
		log.info("회원가입");
		registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		System.out.println(registerDto.getRoles());
//		Member member = memberRepository.save(registerDto.toEntity().addRole(MemberRole.USER));
		Member member = memberRepository.save(registerDto.toEntity().addAllRole(registerDto.getRoles()));
		System.out.println(member.getRoles());

		return member;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// DB 테이블(members)에 username 이 일치하는 사용자가 있으면 UserDetails 타입의
		// 객체를 리턴하고, 그렇지 않으면 UsernameNotFoundException을 던짐
		Optional<Member> member = memberRepository.findByUsername(username);
		if (member.isPresent()) {
			return member.get();
		} else {
			throw (new UsernameNotFoundException(username + "을 찾을 수 없음"));
		}

	}
}
