-- 회원정보(email.pw,tel,address)
-- 를 저장하는 테이블 생성하기
create table Member
(
   email varchar(100),
   pw varchar(100) not null,
   tel varchar(100),
   address varchar(200),
   
   constraint member_email_pk primary key(email)
);
-- 블록지정 후 > alt + x
-- 테이블 칼럼명과 DTO에 있는 필드의 이름과 자료형이 같아야 한다.

select *from Member;

select*from Board;

-- 게시글
-- 제목, 작성자, 작성일, 내용,번호,조회수,이미지...
-- 관계형 데이터베이스이기 때문에 이미지가 아니라 문자형으로 저장한다.
create table Board
(
   title varchar(100) not null,
   writer varchar(100) not null,
   content varchar(1000),
   img varchar(200),
   indate datetime default now(),
   count int default 0,
   idx int auto_increment,
   
   constraint board_idx_pk primary key(idx),
   constraint board_writer_fk foreign key(writer)
   references Member(email)
);



insert into Board(title,writer,content)
values(
	'DCX기반 빅데이터 분석서비스 개발자과정(1)',
	'123',
	'게시판 만들기'
	
);

select * from Board;

update Board 
set count =35
where writer ='123';