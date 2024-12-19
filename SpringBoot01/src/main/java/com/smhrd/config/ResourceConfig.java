package com.smhrd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

	

// Class 파일에서 설정을 하는 경우
// 외부 리소스 접근 url을 만드는 설정, security 등등...


@Configuration // 반드시 com.smhrd 파일 밑에 있어야 사용 가능하다.(설정파일이라는 것을 알려줘야 함)
// WebMvcConfigurer : override부터 밑에 있는 메소드까지 생성
public class ResourceConfig implements WebMvcConfigurer {
	@Value("${save.path}")
	private String savePath;
	
	// 외부 리소스(폴더)에 접근하는 URL을 지정하는 것
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// http://localhost:8088/save/cat.jpg
		
		// C:save/cat.jpg
		
		registry.addResourceHandler("/save/**") // 어떤 양식의 URL인지? 첫번째 아스타리카: 전체 두번째 아스타리카 : 전체 확장자
				.addResourceLocations("file:///" + savePath) // 어떤 폴더랑 연결할건지 (통신프로토콜이라 앞에 "file:///"이게 붙어야 한다.)
		;// (주의! 마지막에 세미콜론 한번만 찍어라)
		 // bean : spring이 생성해주는 객체
		 // 눈에 보이지 않는 것을 생성해주는 것
		 // 전역변수로 선언하고 다른 클래스에서 사용하는 느낌
		 // interface와 xml dao를 ??
	}
}
