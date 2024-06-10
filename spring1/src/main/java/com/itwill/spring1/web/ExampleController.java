package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.spring1.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

// POCO(Plain Old C++ Object)
// POJO(Plain Old Java Object): 간단한 오래된 자바 객체.
// 특정 클래스를 상속(extends)하거나, 특정 인터페이스를 구현(implements) 할 필요가 없는
// (상위 타입의 특정 메서드들을 반드시 재정의할 필요가 없는) 평범한 자바 객체.
// 스프링 MVC 프레임워크에서는 POJO로 작성된 클래스를 컨트롤러로 사용할 수 있음!
// (비교) HttpServlet 을 상속받는 클래스에서는 doGet(req, resp) 또는 doPost(req, resp)를
// 반드시 재정의(override)해야 웹 서비스(요청 처리)가 가능.

@Slf4j // -> private static final Logger log =
		// LoggerFactory.getLogger(ExampleController.class);
@Controller // -> 디스패쳐 서블릿에게 컨트롤러 컴포넌트임을 알려줌.
// (1) servlet-context.xml 파일에서는  <context:component-scan ... /> 설정
// (2) 컨트롤러 클래스에서는 @Controller 애너테이션을 사용.
// -> 디스패쳐 서블릿이 컨트롤러 객체를 생성, 관리.
public class ExampleController {

	@GetMapping("/")
	public String home(Model model) {
		log.debug("home()");

		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now", now);
		// Model 객체는 컨트롤러에서 뷰로 데이터를 전달할 때 사용.
		// request.setAttribute(name, object)와 비슷한 기능.

		return "home";
		// -> 컨트롤러 메서드가 문자열을 리턴하면, 디스패쳐 서블릿이 뷰의 이름을 찾는 데 사용.
		// 디스패쳐 서블릿이 뷰 리졸버를 이용해서 /WEB-INF/views/returnValue.jsp
	}

	@GetMapping("/example")
	public void controllerExample() {
		log.debug("controllerExample()");
		// 컨트롤러 메서드가 리턴 값이 없는(void 로 선언된) 경우,
		// 요청 주소가 뷰의 이름이 됨.
	}

	@GetMapping("/ex1")
	public void controllerEx1(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "age", defaultValue = "0") int age, Model model) {
		log.info("ex1(userName={}, age={}", userName, age);
		// 컨트롤러 메서드 파라미터를 선언할 때 @requestParam 애너테이션을 사용하면,
		// 디스패쳐 서블릿이 컨트롤러 메서드를 호출할 때,
		// (1) request.getParameter("userName"), request.getParameter("age") 호출해서
		// 요청 파라미터 값들을 읽고,
		// (2) 컨트롤러 메서드의 아규먼트로 전달해 줌.

		// 요청 파라미터 값들로 UserDto 객체를 생성.
		UserDto user = UserDto.builder().userName(userName).age(age).build();

		// UserDto 객체를 뷰로 전달:
		model.addAttribute("user", user);
	}

	@PostMapping("/ex2")
	public String controllerEx2(@ModelAttribute(name = "user") UserDto userDto, Model model) {
		log.info("ex2(userdto={}", userDto);
		// 디스패쳐 서블릿은 컨트롤러 메서드를 호출하기 위해서
		// UserDto 클래스 기본 생성자를 호출하고, 요청 파라미터 이름으로 setter 를 찾아서 호출.
		// 생성된 객체를 컨트롤러 메서드의 아규먼트로 전달.

		// -> @ModelAttribute(name = "user") UserDto userDto 파라미터 선언은
		// model.addAtribute("user", user); 코드 작성과 같은 효과:
		// 컨트롤러에서 뷰로 전달하는 데이터.
		UserDto user = userDto;

		if (user.getAge() == null) {
			user.setAge(0);
		}
//		model.addAttribute("user", user);

		return "ex1"; // -> 뷰 이름
	}

	@GetMapping("/test")
	public void controllerTest() {
	}

	@GetMapping("/test2")
	public String controllerForward() {
		log.debug("forward()");

		return "forward:/test";
	}

	@GetMapping("/test3")
	public String controllerRedirectPage() {
		log.debug("redirect()");

		return "redirect:/test";
		// 컨트롤러 메서드가 "redirect:" 시작하는 문자열을 리턴 -> 리다이렉트 이동.
		// 리다이렉트 방식의 페이지 이동 - 최초 요청 주소가 리다이렉트 되는 주소로 바뀜

	}

	@GetMapping("/rest1")
	@ResponseBody
	// -> 컨트롤러 메서드가 리턴하는 값이 뷰를 찾기 위한 문자열이 아니라,
	// 클라이언트로 직접 응답되는 데이터.
	// 응답 패킷(response packet)의 몸통(body)에 포함되는 데이터.
	public String rest1() {
		log.debug("rest1()");

		return "Hello. 안녕하세요!";
	}

	@GetMapping("/rest2")
	@ResponseBody //-> 리턴 값이 클라이언트로 직접 응답되는 객체.
	public UserDto rest2() {
		log.debug("rest2()");

		UserDto userDto = UserDto.builder().userName("홍길동").age(18).build();

		return userDto;
		//-> REST 컨트롤러가 리턴한 자바 객체를 jackson-databind 라이브러리에서
		// JSON(JavaScript Object Notation) 형식의 문자열로 변환하고,
		// 클라이언트로 응답을 보냄.
	}
}
