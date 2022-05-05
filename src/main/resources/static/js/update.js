// (1) 회원정보 수정
function update(userId, event) {
	
	let data = $("#profileUpdate").serialize(); // form 태그가 들고 있는 모든 input 값들을 담을 수 있다.
	
	console.log(data);
	
	$.ajax({
		type:"put",
		url : `/api/user/${userId}`, // 주소
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", // 데이터가 뭔지를 설명하는 것
		dataType: "json" // 타입을 알려주면 json 데이터를 자바스크립트로 파싱해서 응답을 받는다.(res)
	}).done(res=>{
		console.log("update 성공");
		location.href = `/user/${userId}`
	}).fail(error=>{
		console.log("update 실패");
	});
}