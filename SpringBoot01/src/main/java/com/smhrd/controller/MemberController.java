package com.smhrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

import com.smhrd.entity.Member;
import com.smhrd.mapper.MemberMapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller // Spring에게 POJO임을 알려주기 위한 어노테이션(필수적으로 존재해야 함.)

public class MemberController {

	// xml, interface --> SqlSessionFactory --> DAO
	// MyBatis가 interface를 구현해서 서버 메모리에 등록시켜 놓음
	@Autowired // 메모리에서 넣을 수 있는거 가져와서 넣어라
	// 이유? sqlSessionfactory를 가져다가?
	private MemberMapper mapper; // 업캐스팅

	// Spring
	// FrontController 기반
	// Servlet이 늘어날수록 서버 메모리 부하 증가 --> Servlet을 하나만 쓰자.
	// 일반 Class에 "어떤 요청이 들어왔을 때, 무슨 일을 할지를 정의"
	// FrontController에서는 정의된 내용을 실행

	// Spring에서는 Servlet을 기본으로 제공, 클래스만 만들면 된다.
	// POJO(Plain Old Object)라고도 부른다.

	// "어떤 요청이 들어왔을 때, 무슨 일을 할지를 정의" --> 메소드
	@RequestMapping("/goMain") // goMain 요청이 들어오면 goMain()을 실행해라
	public String goMain() {
		// 1. 데이터 수집

		// 2. 기능 실행

		// 3. View 이동
		// forward 이동시, 이동하고 싶은 "jsp파일의 이름만" 리턴 하면 된다.
		return "main";

	}

	@RequestMapping("/join") // 기본생성자 어노테이션하지 않으면 Member member 사용 못함.
	public String join(Member member) {
		// 필요한 객체 생겼다!
		// --> 매개변수에 변수를 선언해두면, Spring이 알아서 객체를 집어 넣어줌
		// 1. 데이터 수집

		// 메소드의 매개변수 자리에 변수를 선언해두면 자동으로 수집
		// 변수명 == name(main.jsp에 input태그에 있는 name을 말하는 것이다.)
		// 형변환도 자동으로 진행

		// DTO를 이용해서 한번에 수집도 가능
		// DTO에 선언된 필드 변수명 == name

//		String email = request.getParameter("email");
//		String pw = request.getParameter("pw");
//		String tel = request.getParameter("tel");
//		String address = request.getParameter("address");
//		
//		// >> 하나로 묶는 과정까지
//		Member member = new Member();
//		member.setEmail(email);
//		member.setPw(pw);
//		member.setTel(tel);
//		member.setAddress(address);

		// 필터라는 것이 있어서 인코딩을 할 필요가 없다.
		// 2. 기능 실행
		// >> DB에 회원정보 저장하기
		int cnt = mapper.join(member);

		if (cnt > 0) {
			System.out.println("회원가입 성공");
		}

		// 3. view 이동
		// redirect
		return "redirect:/goMain";
		// main을 적으면 안됨. 이미 컨트롤러가 있는 경우 redirect방식으로 한다. default가 forward다.
		// 컨트롤러에서 컨트롤러로 이동시 redirect방식, jsp로 이동시 forward방식이다.
	}

	@RequestMapping("/login") // form태그 action과 일치해야 함.
	public String login(Member member, HttpSession session) {
		// 1. 데이터 수집
		// 2. 기능 실행
		Member result = mapper.login(member); // object로 반환하기 때문에

		if (result == null) {
			System.out.println("login f");
		} else {
			System.out.println("login t");
			session.setAttribute("user", result);
		}
		// 3. View 이동
		return "redirect:/goMain";

	}

	@RequestMapping("/goUpdate")
	public String goUpdate() {
		// 1. 데이터 수집
		// 2. 기능실행
		// 3. View 이동
		return "update";
	}

	@RequestMapping("/update")
	public String update(Member member, HttpSession session) {
		// 1. 데이터 수집
		Member user = (Member) session.getAttribute("user"); // 다운캐스팅
		String email = user.getEmail();

		member.setEmail(email);
		// 2. 기능 실행
		int cnt = mapper.update(member);

		if (cnt > 0) {
			System.out.println("update t");
			session.setAttribute("user", member);
		} else {
			System.out.println("update f");
		}
		// 3. view 이동
		return "redirect:/goMain";

	}

	@RequestMapping("/socket")
	// void : forward이동
	public void socket() {
		
	}

}
