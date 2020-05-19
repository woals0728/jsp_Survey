<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="DB.*" %>
<% 
request.setCharacterEncoding("UTF-8");

     String stdno = (String) session.getAttribute("stdno");

     List<TopsubDTO> topsubList = null;
     DbCon topsubDao = DbCon.getInstance();
     topsubList = topsubDao.getTopsublist();

%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 페이지</title>
    <style type="text/css">
        table,td{
            border:1px solid black
        }
        table{
            width:90%;
            height:100px;
            margin:auto;
            text-align:center;
        }
    </style>

</head>
<body>

<div class="super_container">
    <jsp:include page="header_navigation.jsp" flush="false" />
   <div class="main_slider" style="background-image:url(images/slider_1.jpg)" >
        <div class="container fill_height">
            <div class="row align-items-center fill_height">
                <div class="col">
                    <center>
                    <div class="main_slider_content">
                        <form name="admin" action="" method="POST">
                            <table>
                                <tr>
                                    <th width="906" colspan="3" ><p align="center">설 문 지 목 록</p></th>
                                </tr>
                                <tr >
                                    <td width="100" style="color:black">
                                        <p align="center" style="color:black"><b>설문지번호</b></p>
                                    </td>
                                    <td width="650" style="color:black">
                                        <p align="center"><b>설 문 지 제 목</b></p>
                                    </td>
                                    <td width="176" style="color:black">
                                        <p align="center"><b>참 여 여 부</b></p>
                                    </td>
                                </tr>
                                <% int cnt=0; //설문지갯수 파악을 위한 Int형 변수 선언 %>
                                <%
                                     for(int i=0;i<topsubList.size();i++) {
                                        TopsubDTO topsub = (TopsubDTO)topsubList.get(i);
                                %>
                                <SCRIPT>
                                    //질문별 상세보기 함수(해당 객관식 질문의 idx번호를 매개변수로 받음)
                                    function topsub_DetailView(topidx){
                                        var popupX = (window.screen.width / 2) - (800 / 2);
                                        var popupY= (window.screen.height / 2) - (600 / 2);
                                        window.open("survey3.jsp?top_idx="+topidx,"DetailView","width=800, height=600, toolbar=no, scrollbars=yes, status=no,left="+ popupX + ", top="+ popupY);
                                    }
                                </SCRIPT>
                                <tr>
                                    <td>
                                        <p align="center">설문지-<%=cnt+1%></p>
                                    </td>
                                    <td >
                                        <p align="center">
                                            <%

                                                if(stdno != null)
                                                {
                                                    int Check2 = topsubDao.answer_Check(stdno, topsub.getTop_idx());
                                                    if(Check2 == 1){ %>
                                            <a href="javascript:" onclick="clickCheck()"><%=topsub.getTopsubject()%></a>
                                                  <%  }else{
                                            %>

                                            <a href="#myModal" onClick="topsub_DetailView(<%=topsub.getTop_idx()%>)"><%=topsub.getTopsubject()%></a>
                                            <%
                                            }}else {
                                            %>
                                            <a href="javascript:" onclick="handleClick()"><%=topsub.getTopsubject()%></a>
                                            <%
                                                }
                                            %>
                                        </p>
                                        <p align="center">
                                            <%=topsub.getInfo()%>
                                        </p>
                                    </td>
                                    <td>
                                        <%
                                            if(stdno == null) {

                                          %>
                                        <p align="center">로그인 필요
                                        </p>
                                        <%}else{
                                            int Check = topsubDao.answer_Check(stdno, topsub.getTop_idx());

                                            if(Check == 1){
                                        %>
                                        <p align="center">참여
                                        </p>
                                        <%
                                        }else {
                                        %>
                                        <p align="center">미참여</p>
                                        <%}}%>

                                    </td>
                                </tr>
                                <% cnt++; //각 레코드 갯수만큼 cnt 값 증가, 각 레코드의 idx번호를 objLastIdx변수에 계속 덮어씀으로써 최종적으로는 마지막 객관식 질문 idx번호를 저장
                                    }  %>

                            </table>
                        </form>
                    </div>
                    </center>

                </div>

            </div>

        </div>

    </div>

    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="footer_nav_container">
                        <div class="cr" align="right">©2020 부산경상대학교 201606023 이재민</div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</div>

<script src="loginCheck.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap4/popper.js"></script>
<script src="styles/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="js/categories_custom.js"></script>
</body>

</html>