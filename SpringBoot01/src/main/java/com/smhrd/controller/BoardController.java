package com.smhrd.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.entity.Board;
import com.smhrd.mapper.BoardMapper;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper mapper;

	@RequestMapping("/list")
	public String list(Model model) {
		// 1. 데이터 수집
		// 2. 기능 실행
		List<Board> list = mapper.list();

		// Scope : 정보저장(유지) 4개의 내장객체
		// page : 하나의 JSP 안에서만 유지
		// request*** : 한번의 요청 -- 응답동안만 유지
		// >> Model 객체 : 다이어트한 request, 저장기능외에 다른기능을 제거한 request
		// session : 하나의 브라우저 내에서 (계속지워야 하기 때문에 사용하지 않는다.)
		// application : 서버종료시까지, 공용공간

		// addAttribute는 setattribute와 똑같은 기능을 하는 메소드다.
		model.addAttribute("list", list);

		// 3. View 이동
		return "boardMain"; // WEB-INF/views/boardMain.jsp

	}

	@RequestMapping("/writerBoard")
	public void writerBoard() {
		// 1. 데이터 수집
		// 2. 기능 실행
		// 3. View 선택
		// return "writerBoard";
		// 리턴 타입이 void인 경우 >> urlmapping을 jsp 이름이라 간주하고 forward
	}

	// 배포시 ubuntu에는 c드라이브가 없다.

	// @Value를 이용해서 변수 값을 채울 수 있다.
	// 어노테이션 안에서 ${프로퍼티 이름}을 사용해서 application.properties에 정의해둔
	// 데이터를 가져올 수 있다.
	@Value("${save.path}")
	private String savePath;

	@RequestMapping("/write")
	public String write(Board board, MultipartFile file) {
		// 1. 데이터 수집
		// file을 수집하는 방법
		// (1) 전송된 파일을 수집할 때, MultipartFile 자료형으로 선언
		// (2) 파일을 저장할 폴더(경로에 한글X )
		// (3) 폴더 경로 저장
		// (4) 파일로 변환해서 저장
		if (file == null) {
			board.setImg("none"); // null을 변수값에 넣을 때 에러가 나기 때문에 작성함.(무조건 사진을 올려야 된다는 뜻으로 가정하여 오류 발생)
		} else {

			try {
				// 1) 앞에 랜덤한 문자열을 붙여서, 파일 이름 중복 방지
				// UUID는 랜덤문자열을 만드는 객체다.
				String uuid = UUID.randomUUID().toString();     // 랜덤수를 진짜 임의로 부여한거라 랜덤수로 식별을 할 수 없다.(단순히 파일명이 겹치지 않도록 하기 위해서만 랜덤수를 부여한 것이라 식별은 불가능하다.)

				String filename = uuid + file.getOriginalFilename();

				// 2) 전체경로(폴더 경로 + 파일 이름 )
				Path path = Paths.get(savePath + filename);

				// 3) 저장
				file.transferTo(path);  // 이동 및 저장까지 함.

				board.setImg(filename);

			} catch (Exception e) {
				e.printStackTrace(); // 오류 메세지 출력
			}
		}

		// 2. 기능 실행
		mapper.write(board);
		// 3. view 이동
		// 기능실행되고 가야 하기 때문에
		return "redirect:/list";
	}

	@RequestMapping("/view")
	public String view(int idx, Model model) {
		// 1.데이터 수집

		// 2. 기능 실행
		Board board = mapper.view(idx);

		model.addAttribute("board", board);
		// 3. View 이동
		return "viewBoard";

	}

	// ~~~~~~/delete/3
	@RequestMapping("/delete/{idx}")
	public String delete(@PathVariable("idx") int idx) {
		// 1.데이터 수집
		// 2.기능 실행
		mapper.delete(idx);
		// 3.View 이동
		return "redirect:/list";
	}
	
	@RequestMapping("/axios")
	public void axios() {
		
	}
	
}
