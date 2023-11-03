<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<% session.getAttribute("loginUser"); %>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Journey HTML CSS Template</title>
<!-- 
Journey Template 
http://www.templatemo.com/tm-511-journey
-->
    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="../css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" type="text/css" href="../css/datepicker.css"/>
    <link rel="stylesheet" type="text/css" href="../slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../slick/slick-theme.css"/>
    <link rel="stylesheet" href="../css/templatemo-style.css">                                   <!-- Templatemo style -->

      </head>

      <body>
        <div class="tm-main-content" id="top">
            <div class="tm-top-bar-bg"></div>    
            <div class="tm-top-bar" id="tm-top-bar">
                <div class="container">
                    <div class="row">
                        <nav class="navbar navbar-expand-lg narbar-light">
                       		<c:choose>
	                     		<c:when test="${not empty loginUser}">
		                            <a class="navbar-brand mr-auto" onclick="location.href='UserServlet?command=main&userid=${loginUser.userid}'"><img src="img/chunsik.png" alt="Site logo">KaKao airline</a>
		                        </c:when>
		                        <c:otherwise>
		                         	<a class="navbar-brand mr-auto" onclick="location.href='UserServlet?command=main'"><img src="img/chunsik.png" alt="Site logo">KaKao airline</a>
		                        </c:otherwise>
		                    </c:choose>        
		                            <button type="button" id="nav-toggle" class="navbar-toggler collapsed" data-toggle="collapse" data-target="#mainNav" aria-expanded="false" aria-label="Toggle navigation">
		                                <span class="navbar-toggler-icon"></span>
		                            </button>
                            <div id="mainNav" class="collapse navbar-collapse tm-bg-white">
                                <ul class="navbar-nav ml-auto">
                                	<li class="nav-item">
                                		<c:choose>
	                                		<c:when test="${not empty loginUser}">
	                                    		<a class="nav-link active" href="UserServlet?command=main&userid=${loginUser.userid}">Home</a>
	                                    	</c:when>
	                                    	<c:otherwise>
	                                    		<a class="nav-link active" href="UserServlet?command=main">Home</a>
	                                    	</c:otherwise>	
                                 		</c:choose>
                                 	</li>
                                 	<li class="nav-item">
                                 		<c:choose>
	                     	            	<c:when test="${not empty loginUser}">
	                                    		<a class="nav-link active" onclick="location.href='UserServlet?command=flightList&curPage=1&userid=${loginUser.userid}'" curPage="${page}">Flight</a>
	                                		</c:when>
	                            	    	<c:otherwise>
	                                    		<a class="nav-link active" onclick="location.href='UserServlet?command=flightList&curPage=1'" curPage="${page}">Flight</a>
	                                		</c:otherwise>
                                		</c:choose>
                                 	</li>
                                	<li class="nav-item">
										<c:choose>
	                     	            	<c:when test="${not empty loginUser}">
	                                    		<a class="nav-link" href="#tm-section-3" onclick="location.href='UserServlet?command=logout'">logout_${loginUser.userid}</a>
	                                		</c:when>
	                            	    	<c:otherwise>
	                                    		<a class="nav-link" href="#tm-section-3" onclick="location.href='UserServlet?command=login'">login_${loginUser.userid}</a>
	                                		</c:otherwise>
                                		</c:choose>
                                	</li>
                                	<li class="nav-item">
	                                	<c:if test="${loginUser.admin == 1}">
	                                	 	<a class="nav-link" href="#tm-section-3" onclick="location.href='UserServlet?command=admin&userid=${loginUser.userid}'">My Page(admin)</a>
	                                	</c:if>
	                                	<c:if test="${loginUser.admin == 0}">
	                                		 <a class="nav-link" href="#tm-section-3" onclick="location.href='UserServlet?command=user&userid=${loginUser.userid}'">My Page(user)</a>
	                                	</c:if>
                               	    </li>
                                	<li class="nav-item">
                                		<c:if test="${loginUser.admin == 1}">
	                                	 	<a class="nav-link" href="#tm-section-3" onclick="location.href='BoardServlet?command=notice_list&userid=${loginUser.userid}'">Notice(admin)</a>
	                                	</c:if>
	                          			<c:if test="${loginUser.admin == 0}">
	                                		<a class="nav-link" onclick="location.href='BoardServlet?command=notice_list'">Notice</a>
	                                	</c:if>
	                                	<c:if test="${empty loginUser}">
	                                		<a class="nav-link" onclick="location.href='BoardServlet?command=notice_list'">Notice</a>
	                                	</c:if>
                                    	
                                	</li>
                                	<li class="nav-item">
	                                	 <a class="nav-link" href="#tm-section-3" onclick="location.href='BoardServlet?command=qna_list'">Q&A</a>
                                	</li>
                                	<li class="nav-item">
	                                	 <a class="nav-link" href="#tm-section-3" onclick="location.href='boardDiaryList.do'">Board</a>
                                	</li>
                                	<li class="nav-item">
	                                	 <a class="nav-link" href="#tm-section-3" onclick="location.href='boardEventList.do'">Event</a>
                                	</li>
                                	
                                	
                            	</ul>
                        	</div>                            
                    	</nav>
                	</div> <!-- row -->
            	</div> <!-- container -->
        	</div> <!-- .tm-top-bar -->
    <!--</div>  .main-content -->

    <!-- load JS files -->
    <script src="js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
    <script src="js/popper.min.js"></script>                <!-- https://popper.js.org/ -->       
    <script src="js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
    <script src="js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
    <script src="js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
    <script src="slick/slick.min.js"></script>                <!-- http://kenwheeler.github.io/slick/ -->
    <script src="js/jquery.scrollTo.min.js"></script>           <!-- https://github.com/flesler/jquery.scrollTo -->
    <script> 
   

        /* DOM is ready
        ------------------------------------------------*/
        $(function(){

            // Change top navbar on scroll
            $(window).on("scroll", function() {
                if($(window).scrollTop() > 50) {
                    $(".tm-top-bar").addClass("active");
                } else {                    
                 $(".tm-top-bar").removeClass("active");
                }
            });

            // Smooth scroll to search form
           $('.tm-down-arrow-link').click(function(){
                $.scrollTo('#tm-section-search', 300, {easing:'linear'});
            });

            // Date Picker in Search form
            var pickerCheckIn = datepicker('#inputCheckIn');
            var pickerCheckOut = datepicker('#inputCheckOut');

            // Update nav links on scroll
          $('#tm-top-bar').singlePageNav({
                currentClass:'active',
                offset: 60
            }); 

            // Close navbar after clicked
           $('.nav-link').click(function(){
                $('#mainNav').removeClass('show');
            }); 

            // Slick Carousel
	        $('.tm-slideshow').slick({
                infinite: true,
                arrows: true,
                slidesToShow: 1,
                slidesToScroll: 1
            });

            loadGoogleMap();                                       // Google Map                
            $('.tm-current-year').text(new Date().getFullYear());  // Update year in copyright           
        });

    </script>             

</body>
</html>