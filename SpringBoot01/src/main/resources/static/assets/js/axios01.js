// 우클릭 > open with >generic Editor


// HTML 요소의 DOM를 선택
// 이벤트가 사라질 수 있기 때문에 const(상수)로 지정 var or let을 사용해도 됨.

const btn01 = document.getElementById("btn01");
btn01.addEventListener("click", request);

function request() {

	// 동기 통신 : 요청에 대한 응답으로 HTML을 응답

	// 비동기 통신 : 요청이 발생하면, 필요한 데이터만 응답
	//            페이지 이동(전환) X
	//            >> 주고 받는 데이터 양 줄어듦
	//            >> 작업을 이어서 할 수 있다.
	//            >> 데이터 처리를 Client에 위임(서버의 부하가 줄어듬)
	//            << 무분별한 요청할 수 있음
	//            F12(개발자 모드)에서 network에서 확인한다.
	// 내부서버(같은 프로젝트 내)요청시에는 URLMapping만 작성해도 된다.
	// 외부서버(다른 프로젝트/서버)로 요청시에는 전체 URL 작성해야 함
	//       >> CORS 에러를 처리해줘야한다.
	// 서버에서 요청을 하면 보낸 자신이 아닌 다른사람한테 비동기 요청을 하면 허락을 받아야 요청이 가능하다.
	var url = "axios01";

	// .get(url) : GET 방식 요청
	// .post(url) : POST 방식 요청
	axios.get(url)
		.then(function(res) {
			// 응답이 돌아오면 무슨일을 할지 정의
			// 응답데이터는 매개변수에 담김
			console.log(res.data);
		})
}



// ===================================================


// Get 방식으로 데이터 전송
const btn02 = document.getElementById("btn02");

btn02.addEventListener("click", request02);

function request02() {

	var url = "axios02";

	const inp01 = document.getElementById("inp01");
	let text = input01.value; // input text 가져오기

	// get 방식으로 데이터 전송시,
	// query string,path variable을 작성해서 전송
	axios.get(url + "?text=" + text)

}


// ===============================================================

// POST방식 + 데이터 응답
const btn03 = document.getElementById("btn03");

const div = document.getElementById("result");

// const tinput = document.getElementsByName("title")[0];

// 태그명[속성명=속성값]
const tinput = document.querySelector("input[name=title]");
const winput = document.querySelector("input[name=writer]");

// id로 getElementById이걸 사용하면 함수로 값을 선언할 수 없기 때문에 위에 방법으로 많이 사용함.(querySelector)

btn03.addEventListener("click", request03);

function request03() {

	let url = "axios03";

	let title = tinput.value;
	let writer = winput.value;
	let data = {
		"title": title,
		"writer": writer

	};
	// Post방식으로 전송시에, 데이터를 객쳏로 묶어서 전송
	// javascript --> json 형식으로 전송
	// javascript Object Notation
	// ""{""title" : title , "writer" : writer}
	axios.post(url, data)
		.then(function(res) {
			console.log(res.data);

			for (let i = 0; i < res.data.length; i++) {
				// div에 게시글 제목들을 출력
				div.innerHTML += "<p>" + res.data[i].title + "</p>";

			}

		})



}



