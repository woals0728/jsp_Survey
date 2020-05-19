<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Book Shopping Mall">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="http://l.bsks.ac.kr/~p201887082/DiliManage/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>

<body>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <%
                try{
                    if(session.getAttribute("stdno")==null){%>
            <div class="modal-body">
                <div class="text">
                    <h3 class="login-p">LOGIN</h3>
                    <p class="login-p2">회원님의 학번과 이름을 입력해주세요.</p>
                    <form name="login-form" method="post" action="loginPro.jsp">
                        <div class="stdno" align="center"><input type="text" name="stdno" placeholder="학번" class="id-input" maxlength="50" size="45" required/> </div>
                        <div class="name" align="center"><input type="text" name="name" placeholder="이름" class="pass-input" maxlength="16" size="45" required/> </div>
                        <div class="login-btn" align="center"><input type="submit" value="LOGIN" class="red_button shop_now_button" /></div>
                    </form>

                    <%}
                    }catch(NullPointerException e){
                        e.printStackTrace();
                    }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>


<header class="header trans_300">
    <div class="top_nav">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="top_nav_left"></div>
                </div>
                <div class="col-md-6 text-right">
                    <div class="top_nav_right">
                        <ul class="top_nav_menu">

                            <!-- Currency / Language / My Account -->

                            <li class="account">
                                <a href="#">
                                    My Account
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="account_selection">
                                    <%if(session.getAttribute("stdno")==null){%>
                                    <li><a data-toggle="modal" href="#myModal"><i class="fa fa-sign-in" aria-hidden="true"></i>로그인</a></li>
                                    <%}else{%>
                                    <li><a href="#" onclick="javascript:window.location='../logout.jsp'"><i class="fa fa-sign-out" aria-hidden="true"></i>로그아웃</a></li>
                                    <%}%>

                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main_nav_container">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-right">
                    <div class="logo_container">
                        <a href="admin.jsp">SUR<span>VEY</span></a>
                    </div>
                    <nav class="navbar">
                        <ul class="navbar_menu">
                        </ul>
                        <div class="hamburger_container">
                            <i class="fa fa-bars" aria-hidden="true"></i>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="fs_menu_overlay"></div>
<div class="hamburger_menu">
    <div class="hamburger_close"><i class="fa fa-times" aria-hidden="true"></i></div>
    <div class="hamburger_menu_content text-right">
        <ul class="menu_top_nav">

            <li class="menu_item has-children">
                <a href="#">
                    My Account
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="menu_selection">
                    <%if(session.getAttribute("stdno")==null){%>
                    <li><a data-toggle="modal" href="#myModal"><i class="fa fa-sign-in" aria-hidden="true"></i>로그인</a></li>
                    <%}else{%>
                    <li><a href="#" onclick="javascript:window.location='../logout.jsp'"><i class="fa fa-sign-out" aria-hidden="true"></i>로그아웃</a></li>
                    <%}%>
                </ul>
            </li>
        </ul>
    </div>
</div>



<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap4/popper.js"></script>
<script src="styles/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="js/custom.js"></script>
<script src="http://l.bsks.ac.kr/~p201887082/DiliManage/js/jq.js"></script>
<script src="http://l.bsks.ac.kr/~p201887082/DiliManage/js/bootstrap.js"></script>
<script src="js/modal_clear.js"></script>
</body>
</html>
