package com.smhrd.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.entity.Member;

@Mapper // 해당 파일이 Mapper임을 명시(sqlSessionfactory가 잘 찾을 수 있도록 선언)
// class 파일이 아닌 인터페이스로 만든 이유?
// 추상메소드,추상클래스에 abstract를 써야 하는데 번거로워서 interface를 사용하여 관리
// Mybatis가 인터페이스를 기반으로 구현체를 자동 생성되기 때문
// 유지보수성, 가독성이 좋아짐
// 자바와 sql을 분리하기 위해서
// membermapper가 로그인,로그아웃 등등의 기능들을 총괄하는 부모클래스이기 때문
public interface MemberMapper {
	// abstract를 계속선언해서 써줘야 하기때문에 interface로 해결한다.
	// 1. 연결
	// 기존 방식 --> 기능 실행시마다 Connect 생성 --> DB 부하가 커짐
	// >> Database Connection Pool (DataSource) : 반납을 해야 빌려주는 것이다.
	// 미리 만들어두고 ,빌려만 주자.
	
	// 2. 기능구현(MyBatis Framework)
	// java <---(mapping)--> xml
	// 실행코드                (sql)
	// insert/delete/update >> int 리턴
	// excuteUpdate
	// sql문의 id == 메소드 이름
	public int join(Member member); // 추상메소드 db쿼리를 실행하기 위해 선언 메서드 이름은 1대1로 매칭이 될 수 있기 때문에 id(mapper.xml)와 같아야 한다.

	// MemberDAO부분에 객체가 선언된게 없어서 MemberMapper로 변경한다.

	public Member login(Member member);

	public int update(Member member);
	
	public Member check(String email);

	
}
