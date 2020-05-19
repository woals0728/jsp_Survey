<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="DB.*" %>  

<%
request.setCharacterEncoding("UTF-8"); 

String top_idx = request.getParameter("top_idx");
String stdno = (String)session.getAttribute("stdno");

List<Obj_listDTO> objList = null;
	DbCon objDao = DbCon.getInstance();
objList = objDao.getObjlist(Integer.parseInt(top_idx), "on");

List<Subj_listDTO> subjList = null;
	DbCon subjDao = DbCon.getInstance();
subjList = subjDao.getSubjlist(Integer.parseInt(top_idx), "on");

	DbCon answerDao = DbCon.getInstance();


if(objList!=null) {
for(int i=0;i<objList.size();i++) {
	Obj_listDTO obj = objList.get(i);
	
	//현재 진행중인(view값이 on인) 객관식 질문들중 사용자가 선택하지 않은 질문이 있다면 경고창 출력 후, 프로그램 실행 중지(return)
	String var = request.getParameter("obj_"+obj.getNumber());
	if(var == null){%>
		<SCRIPT>
			alert("모든 질문에 답변해주셔야 합니다.");
			history.back(-1);
		</SCRIPT>
	<%return;}	
} }

if(subjList!=null) {
for(int i=0;i<subjList.size();i++) {
	Subj_listDTO subj = subjList.get(i);
	
	//현재 진행중인(view값이 on인) 주관식 질문들중 사용자가 작성하지 않은 질문이 있다면 경고창 출력 후, 프로그램 실행 중지(return)
	String var = request.getParameter("subj_"+subj.getNum());
	if(var == ""){%>
		<SCRIPT>
			alert("주관식 질문도 모두 답변해주셔야 합니다.");
			history.back(-1);
		</SCRIPT>
	<%return;}	
} }

if(objList!=null) {
for(int i=0;i<objList.size();i++) {
	Obj_listDTO obj = objList.get(i);
	
	if(obj.getKind().equals("radio")){ //종류가 라디오버튼이면 유일한 하나의 선택값 request.getParameter("obj_"+obj.getIdx())을 토대로 투표값 증가 query문 실행
		String ch_cn = request.getParameter("obj_"+obj.getNumber())+"_cn";
		objDao.updateCh_cn(ch_cn, obj.getNumber());

	}else if(obj.getKind().equals("checkbox")){ //종류가 체크버튼이면 일단 arr[] 배열변수를 선언하여 선택값들을 arr[i]에 저장하고 그 값들을 토대로 투표값을 각각 증가시키는 query문 실행
		String arr[] = request.getParameterValues("obj_"+obj.getNumber());
		for(int j=0;j<arr.length;j++){
			String ch_cn = arr[j]+"_cn";
			objDao.updateCh_cn(ch_cn, obj.getNumber());
		}
	}
} }

if(subjList!=null) {
for(int i=0;i<subjList.size();i++) {
	Subj_listDTO subj = subjList.get(i);
	String answer = request.getParameter("subj_"+subj.getNum());
	answerDao.insertAnswer(subj.getNum(), answer, Integer.parseInt(top_idx));
} }

%>

<%
	objDao.answerCheck(stdno, Integer.parseInt(top_idx));
%>

<SCRIPT language="JavaScript">
	alert("참여해주셔서 감사합니다.");
	window.opener.location.reload();
	window.close();



</SCRIPT>





