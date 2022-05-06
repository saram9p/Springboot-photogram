// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault(); // 폼태그 액션을 막기!
	
	let data = $("#profileUpdate").serialize(); // form 태그가 들고 있는 모든 input 값들을 담을 수 있다.
	
	console.log(data);
	
	$.ajax({
		type:"put",
		url : `/api/user/${userId}`, // 주소
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", // 데이터가 뭔지를 설명하는 것
		dataType: "json" // 타입을 알려주면 json 데이터를 자바스크립트로 파싱해서 응답을 받는다.(res)
	}).done(res=>{ // HttpStatus 상태코드 200번대
		console.log("성공", res);
		location.href = `/user/${userId}`
	}).fail(error=>{ // HttpStatus 상태코드 200번대가 아닐 때
		alert(JSON.stringify(error.responseJSON.data)); // JSON.stringify : 자바스크립트 오브젝트를 JSON 문자열로 변환
		console.log("실패", error.responseJSON.data);
	});
}