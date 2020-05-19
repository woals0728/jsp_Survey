<%--
  Created by IntelliJ IDEA.
  User: jaemin
  Date: 2019-12-20
  Time: 오후 3:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page import="DB.*"%>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%
    String id = (String)session.getAttribute("s_id");
    if(id==null) {
        response.sendRedirect("login.jsp");
        return;
    }

    request.setCharacterEncoding("UTF-8");

    TopsubDTO topsub = null;
    TopsubDAO topsubDAO = TopsubDAO.getInstance();
    topsub = topsubDAO.getTopsub(1);

    List<Subj_listDTO> subjlist = null;
    Subj_listDAO subjListDAO = Subj_listDAO.getInstance();
    subjlist = subjListDAO.getSubjlist(1);

    List<Obj_listDTO> objlist = null;
    Obj_listDAO objListDAO = Obj_listDAO.getInstance();
    objlist = objListDAO.getObjlist(1);



%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="add_obj" action="add_obj_do.jsp" method="post">
        <table border="1" width="916" align="center" cellspacing="0">
            <tr>
                <th colspan="2"><p>객관식 질문 추가폼</p></th>
            </tr>
            <tr>
                <td width="110"><p align="center">질문제목</p> </td>
                <td width="486"><input type="text" name="question" size="70"> </td>
            </tr>
            <tr>
                <td width="110"><p align="center">버튼설정</p> </td>
                <td width="486">
                    <select name="button" size="1">
                        <option value="radio">라디오버튼</option>
                        <option value="checkbox">체크버튼</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="110"><p align="center">항목갯수</p> </td>
                <td width="486">
                    <select name="item_num" size="1">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5" selected>5</option>
                    </select><font size="2" color="#1F497D">&lt;-선택한 값만큼만 아래 항목의 내용을 입력하세요.(최대 5개)</font>
                </td>
            </tr>
            <tr>
                <td width="110"><p align="center">항목1</p> </td>
                <td width="486"><input type="text" name="item1" size="20"> </td>
            </tr>
            <tr>
                <td width="110"><p align="center">항목2</p> </td>
                <td width="486"><input type="text" name="item2" size="20"> </td>
            </tr>
            <tr>
                <td width="110"><p align="center">항목3</p> </td>
                <td width="486"><input type="text" name="item3" size="20"> </td>
            </tr>
            <tr>
                <td width="110"><p align="center">항목4</p> </td>
                <td width="486"><input type="text" name="item4" size="20"> </td>
            </tr>
            <tr>
                <td width="110"><p align="center">항목5</p> </td>
                <td width="486"><input type="text" name="item5" size="20"> </td>
            </tr>
            <tr>
                <td width="834" colspan="2">
                    <p align="center">
                        <input type="submit" value="확인"> &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" onclick="history.back(-1)" value="취소">
                    </p>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
