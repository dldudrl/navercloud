package com.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.smhrd.entity.Board;

@Mapper
// 인터페이스를 먼저 만들고 해야 실수를 덜한다.
public interface BoardMapper {

	public List<Board> list();

	public int write(Board board);

	public Board view(int idx);

	@Delete("""
			delete from Board
			where idx = #{idx}
			""")
	public int delete(int idx);

	// id가 "smart인 회원이 작성된 게시글의 글번호, 제목, 내용, 작성일과, 회원의 전화번호 주소를
	// 조회
	/*
	 * select m.tel,m.address,b.title, b.idx, b.content, b.indate from Board b,
	 * Member m where b.writer = m.email and m.email ='smart'
	 */

	public List<Board> search(String text);
	
	public List<Board> chart();
	
	

}
