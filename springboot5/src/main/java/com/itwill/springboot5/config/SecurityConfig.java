package com.itwill.springboot5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
// -> 스프링 컨테이너에서 생성하고 관리하는 설정 컴포넌트.
// -> 스프링 컨테이너에서 필요한 곳에 의존성 주입을 해줌.
// @EnableWebSecurity
@EnableMethodSecurity
// -> 컨트롤러 메서드에서 인증(로그인), 권한 설정을 하기 위해서.
public class SecurityConfig {

	// Spring Security5 버전부터 비밀번호는 반드시 암호화를 해야함.
	// 만약 비밀번호를 암호화 하지 않으면, HTTP 403(access denied, 접근 거부) 또는
	// HTTP 500 (internal server error, 내부 서버 오류) 에러가 발생함.
	// 비밀번호를 암호화하는 객체를 스프링 컨테이너가 bean 으로 관리 해야함.
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 사용자 관리(로그인, 로그아웃, 회원가입 등)을 위한 서비스 인터페이스.
	// 스프링 부트 어플리케이션에서 스프링 시큐리티 를 이용한 로그인/로그아웃을 하려면
	// UserDetailsService 인터페이스를 구현하는 엔티티 클래스가 있어야 함.
	// UserDetails 인터페이스를 구현하는 엔터티 클래스가 있어야 함.
	// 사용자 엔터티와 사용자 서비스를 구현하기 전에 테스트 용도로 사용할 코드.

//	@Bean
//	UserDetailsService inMemoryUserDetailsService() {
//		// 에플리케이션이 동작중에 메모리에 임시 저장하는 사용자 객체를 생성:
//		UserDetails user1 = User.withUsername("유저1").password(passwordEncoder().encode("111")).roles("USER").build();
//		// roles("USER") => 사용자 권한(ADMIN, USER, ...)
//		UserDetails user2 = User.withUsername("유저2").password(passwordEncoder().encode("222")).roles("USER", "ADMIN")
//				.build();
//		UserDetails user3 = User.withUsername("유저3").password(passwordEncoder().encode("333")).roles("ADMIN").build();
//		UserDetails user4 = User.withUsername("유저4").password(passwordEncoder().encode("444")).roles("USER").build();
//		UserDetails user5 = User.withUsername("유저5").password(passwordEncoder().encode("555")).roles("USER").build();
//		UserDetails user6 = User.withUsername("유저6").password(passwordEncoder().encode("666")).roles("USER").build();
//
//		return new InMemoryUserDetailsManager(user1, user2, user3, user4, user5, user6);
//	}

	// 스프링 시큐리티 필터 체인 객체(bean)
	// 로그인/로그아웃, 인증 필터에서 필요한 설정 구성.
	// - 로그인 페이지(뷰), 로그아웃 페이지 설정.
	// - 페이지 접근 권한(ADMIN, USER) 설정.
	// - 인증 설정(로그인 없이 접근 가능한 페이지 vs 로그인 해야만 접근 가능한 페이지)
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//
		// SavedRequestAwareAuthenticationSuccessHandler successHandler = new
		// SavedRequestAwareAuthenticationSuccessHandler();
		//
		http
				// // CSRF 보호를 비활성화합니다.
				// // CSRF(Cross-Site Request Forgery)는 사이트 간 요청 위조 공격을 방지하기 위해 사용하는 보안 기능입니다.
				// // CSRF 기능을 활성화한 경우에는,
				// // REST API 요청에서 CSRF 토큰을 서버로 전송하지 않으면 HTTP 403 에러가 발생
				.csrf(csrf -> csrf.disable());
		//
		// // 페이지 접근 권한, 인증 구성: 아래의 1 또는 2 방법 중 한 가지를 선택.
		// // 1. HttpSecurity.authorizeHttpRequests(Customizer customizer) 메서드에서 설정.
		// // -> 장점: 한 곳에서 모든 설정을 구성할 수 있음.
		// // -> 단점: 새로운 요청 경로가 생길 때마다 설정 구성 코드를 수정해야 함.
		// // 2. 컨트롤러 메서드에서 애너테이션으로 설정.
		// // (1) SecurityConfig 빈에 @EnableMethodSecurity 애너테이션을 설정.
		// // (2) 각각이 컨트롤러 메서드에서 @PreAuthorize 또는 @PostAuthorize 애너테이션을 설정.
		// .authorizeHttpRequests(authorize -> authorize
		// // 모든 요청 주소에 대해서 (role에 상관없이) 아이디/비밀번호 인증을 하는 경우:
		// // auth.anyRequest().authenticated()
		//
		// // 모든 요청 주소에 대해서 "USER" 권한을 가진 아이디/비밀번호 인증을 하는 경우:
		// // auth.anyRequest().hasRole("USER")
		//
		// // 로그인이 필요한 페이지와 그렇지 않은 페이지를 구분해서 설정 구성:
		// .requestMatchers("/post/details/*", "/post/update/*", "/post/delete/*",
		// "/api/comment/**",
		// "/post/create")
		// .hasRole("ADMIN").anyRequest().permitAll())
		//
		// // 폼 로그인 설정을 정의합니다.
		// // 사용자가 로그인할 수 있는 로그인 페이지를 설정합니다.
		// // 'loginPage("/login")'는 로그인 페이지의 경로를 지정합니다.
		// // 이 경로는 모든 사용자에게 접근을 허용합니다.
		http.formLogin((login) -> login.loginPage("/member/signin"));
		// .formLogin(Customizer.withDefaults()) // => 스프링 시큐리티에서 제공하는 로그인 디폴트 페이지로 설정
		//
		// // 로그아웃 설정을 정의합니다.
		// // 사용자가 로그아웃할 수 있는 기능을 설정합니다.
		// // 로그아웃은 모든 사용자에게 접근을 허용합니다.
		http.logout((logout) -> logout.logoutSuccessUrl("/"));
		//
		// // SecurityFilterChain을 빌드하여 반환합니다.
		// // 반환된 SecurityFilterChain 객체는 스프링 시큐리티의 필터 체인으로 사용됩니다.
		return http.build();
	}

}
