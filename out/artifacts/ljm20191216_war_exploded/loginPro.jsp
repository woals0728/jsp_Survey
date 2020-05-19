<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="DB.DbCon"%>

<% request.setCharacterEncoding("utf-8");%>

<%
    String stdno = request.getParameter("stdno");
    String name  = request.getParameter("name");

    DbCon member = DbCon.getInstance();
    int check= member.userCheck(stdno,name);

    if(check==1){
        session.setAttribute("stdno",stdno);
%>
<script>
    const User_value = "<%=name%>";
    console.log(User_value);
    alert(User_value+"님, 반갑습니다.");
    location.href='admin.jsp';
</script>
<%
}else if(check==0){
%>
<script>
    alert("이름을 확인 해주세요.");
    history.go(-1);
</script>
<%}else{ %>

<script>
    alert("학번을 확인 해주세요.");
    history.go(-1);
</script>
<%}
%>