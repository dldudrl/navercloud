package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smhrd.entity.Board;
import com.smhrd.mapper.BoardMapper;

// 데이터를 응답하는 Controller에 RestController 어노테이션을 붙임
// >> @ResponseBody를 붙이지 않아도 된다.

@RestController
public class AxiosRestController {

	// REST : 요청이 발생했을 때, 응답으로 데이터를 돌려주는 구조

	// Rest Api : 요청에 따라 정보를 제공하는 api

	@ResponseBody // 리턴 문자열 == 응답할 데이터 (
	@RequestMapping("/axios01")
	public String axios01() {
		// 1. 데이터 수집
		// 2. 기능 실행
		System.out.println("request");
		// 3. 데이터 응답
		return "main";

	}

	@ResponseBody
	@RequestMapping("/axios02")
	public String axios02(String text) {
		// 1. 데이터 수집
		// 2. 기능 실행
		System.out.println(text);
		// 3. 데이터 응답
		return "GET";
	}

	@Autowired
	private BoardMapper mapper;

	// RequestMapping 속성값
	// value : URLMapping
	// produces : 응답 데이터 형식을 지정
	@ResponseBody
	@RequestMapping("/axios03")
	public List<Board> axios03(@RequestBody String data) {
		// 1. 데이터 수집
		// post 방식으로 데이터를 받아올 때, 만약 이름값이 정해지지 않았다면
		// >> 수집할 변수 앞에 @RequestBody 어노테이션을 붙인다.

		// csv, xml, json (자바스크립트 입장에서 필요한 것이라 많이 사용)
		System.out.println(data);

		// GSON : json(String) <--> java object
		Gson gson = new Gson();
		// json --> javaobject
		// fromJson("json", 옮겨담을 클래스 정보)
		// json이 key == DTO의 필드 변수명
		Board board = gson.fromJson(data, Board.class);
		System.out.println(board.getTitle());
		
		// 다른 키값()을 갖고 있으면 새로운 dto를 만들어 수집한다.

		// 2. 기능 실행
		List<Board> list = mapper.list();


		// json ex) "[{},{},{},{}]" 형태이다.
//      json ?? 방법		
//		(1)
//		String json ="[";
//		for(Board b : list) {
//			json +="{";
//			json +="\"title\" : \"" + b.getTitle() +"\"";
//			json +="\"writer\ : \"" +b.getWriter() +"\"";
//			json +="},";
//		}
//		json +="]";
//		json.replace(",]","]");

//      (2)
//		String json = gson.toJson(list);

		// 3. 데이터 응답
		// jackson databind : java 객체 리턴시, json으로 변환이 일어남
		return list;
	}

}
