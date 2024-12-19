package com.smhrd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

@EnableWebSocket // 웹소켓 사용시작할 것이다.(선언을 안하면 안켜준다.)
@RequiredArgsConstructor
@Configuration // 설정 파일의 경우 반드시 있어야 함.
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final WebSocketHandler handler;
	
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		// ws://localhost:8088/chat                // handler에게 알려주기 위해(addHandler)를 선언      
		registry.addHandler(handler, "/chat/{room}")  // /chat은 url경로
				.setAllowedOrigins("*");  //  모든 도메인을 허락한다는 코드??  cors : 동일 서버 정책 주의!!
				// 외부 서버에서 연결을 허용
	}
	
	
	
}
