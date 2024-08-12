package com.itwill.springboot5.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString(callSuper = true)
//@EqualsAndHashCode(exclude = {"password"}, callSuper = false) // 비번 제외하고 비교
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
// onlyExplicitlyIncluded 속성: @EqualsAndHashCode.Include 
//  에너테이션이 설정된 필드만 사용 할 것 인지 여부.
// callSuper 속성: superclass 의 equals(), hashCode() 메서드를 사용할 것인지 여부.
@Entity
@Table(name = "MEMBERS")
public class Member extends BaseTimeEntity implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@EqualsAndHashCode.Include // username 필드를 equals() 와 hashCode()를 재정의할 때 사용.
	@NaturalId // unique 제약조건
	@Column(name = "USERNAME", length = 20, updatable = false)
	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	@Size(min = 1, max = 20, message = "이름은 1자 이상, 20자 이하로 입력해야 합니다.")
	private String username;

	@Column(name = "PASSWORD", length = 255)
	@NotBlank(message = "비번은 필수 입력 항목입니다.")
	@Size(min = 1, max = 255, message = "비번은 1자 이상, 255자 이하로 입력해야 합니다.")
	private String password;

	@Column(name = "EMAIL", length = 255)
	@NotBlank(message = "이메일은 필수 입력 항목입니다.")
	@Size(min = 1, max = 255, message = "비번은 1자 이상, 255자 이하로 입력해야 합니다.")
	private String email;

	@Builder.Default // Builder 패턴에서도 null 이 아닌 HashSet<> 객체로 초기화될 수 있도록.
	@ToString.Exclude // toString() 제외.
	@ElementCollection(fetch = FetchType.LAZY) // 연관 테이블(Member_roles) 사용.
	@Enumerated(EnumType.STRING) // DB 테이블에 저장될 때 상수 이름(문자열)을 사용.
	private Set<MemberRole> roles = new HashSet<>();

	public Member addRole(MemberRole role) {
		roles.add(role); // Set<> 에 원소를 추가.

		return this;
	}

	public Member addAllRole(Set<String> roleSet) {
		for (String role : roleSet) {
			if (role.equals("3")) {
				roles.add(MemberRole.ADMIN);
			} else if (role.equals("2")) {
				roles.add(MemberRole.STEP);
			} else {
				roles.add(MemberRole.USER);
			}
		}

		return this;
	}

	public Member removeRole(MemberRole role) {
		roles.remove(role);

		return this;
	}

	public Member clearRoles() {
		roles.clear(); // Set<> 의 모든 원소를 지움.

		return this;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		for (MemberRole r : roles) {
//			GrantedAuthority authority = new SimpleGrantedAuthority(r.getAuthority());
//			authorities.add(authority);
//		}

//		roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority())) // MemberRole을 SimpleGrantedAuthority로 변환
//				.forEach(authorities::add); // 변환된 권한 객체들을 authorities 리스트에 추가
//		return authorities;
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toList());
	}
}
