<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="DB.*" %> 
<%
request.setCharacterEncoding("UTF-8");

String top_idx = request.getParameter("top_idx");
String stdno = request.getParameter("stdno");

TopsubDTO topsub = null;
    DbCon topsubDao = DbCon.getInstance();
topsub = topsubDao.getTopsub(Integer.parseInt(top_idx));

List<Obj_listDTO> objList = null;
List<String> obj_chList = null;
    DbCon objDao = DbCon.getInstance();
objList = objDao.getObjlist(Integer.parseInt(top_idx), "on");

List<Subj_listDTO> subjList = null;
    DbCon subjDao = DbCon.getInstance();
subjList = subjDao.getSubjlist(Integer.parseInt(top_idx), "on");
	
%>
<!DOCTYPE html>
<html>
<head>
<SCRIPT>
//투표 확인창 함수
function VoteSubmit(){
    var cons = confirm("참여하시겠습니까?");

    if (cons==true){
		document.survey.submit();
	}if (cons==false){
        return false;
    }
}
</SCRIPT>
<meta charset="UTF-8">
<STYLE type="text/css">
a { text-decoration: none; color: #CC0000; font-weight: bold;}
a:hover { text-decoration: none; color: #000000; font-weight: bold;}
</STYLE>
<title>설 문 조 사</title>
</head>
<body>
<form name="survey" action="survey_do.jsp" method="POST">
    <table align="center" border="0" width="600" cellpadding="0" cellspacing="0">
		<input type="hidden" name="top_idx" value="<%=top_idx%>">    
        <tr>
            <td>
                <p align="center"><font size="5"><b><%=topsub.getTopsubject()%></b></font></p>
            </td>
        </tr>
        <tr>
            <td><hr></td>
        </tr>
<% int cnt=1;
if(objList!=null) {
for(int j=0;j<objList.size();j++) {
	Obj_listDTO obj = (Obj_listDTO)objList.get(j);
	obj_chList = objDao.getChlist(obj.getNumber(), obj.getNum());
%>
        <tr>
            <td><b><%=cnt+"번. " +obj.getSubject()%></b></td>
        </tr>       
        <tr>
            <td>
                <p>
                <% for(int i=0; i<Integer.parseInt(obj.getNum());i++) { 
                 	 String ch = (String)obj_chList.get(i);
                %>
					<input type="<%=obj.getKind()%>" name="obj_<%=obj.getNumber()%>"
							value="ch<%=i+1%>"> <%=ch%>&nbsp;&nbsp;&nbsp;
				<% } %>
				</p>
            </td>
        </tr>
<% cnt++; } } %>
        <tr>
            <td><hr></td>
        </tr>
<%
if(subjList!=null) {
for(int j=0;j<subjList.size();j++) {
	Subj_listDTO subj = (Subj_listDTO)subjList.get(j);
    String[] result = subj.getArea().split("x");
%>
        <tr>
            <td><b><%=cnt+"번. " +subj.getSubject()%></b></td>
        </tr>
        <tr>
            <td>
            <% if(subj.getKind().equals("textarea")) {

            %>

				<textarea rows="<%=result[0]%>" cols="<%=result[1] %>"
							name="subj_<%=subj.getNum() %>"></textarea>
			<% } else if(subj.getKind().equals("text")) { %>
				<input type="text" size="<%=result[1] %>" name="subj_<%=subj.getNum() %>">
			<% } %>
			<br><br>
			</td>
        </tr>
<% cnt++; } } %>
        <tr>
            <td>
                <p align="center"><a href="#" onClick="VoteSubmit()">참여하기</a></p>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
