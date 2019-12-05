 <%@page import="com.cgi.web.model.Customer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <div class="container">

            <div class="mbr-table">
                <div class="mbr-table-cell">

                    <div class="navbar-brand">
                        
                        <a class="navbar-caption" href="index.jsp">TRAVEL EXPERTS</a>
                    </div>

                </div>
                <div class="mbr-table-cell">

                    <button class="navbar-toggler pull-xs-right hidden-md-up" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar">
                        <div class="hamburger-icon"></div>
                    </button>

                    <ul class="nav-dropdown collapse pull-xs-right nav navbar-nav navbar-toggleable-sm" id="exCollapsingNavbar">
                    	<li class="nav-item"><a class="nav-link link" href="index.jsp">HOME</a></li>
                    	<li class="nav-item"><a class="nav-link link" href="https://mobirise.com/">TRAVEL PACKAGES</a></li>
                    	<li class="nav-item"><a class="nav-link link" href="https://mobirise.com/">CONTACTS</a></li>
                    	<c:choose>
                    		<c:when test = "${loggedInUser != null}">
                    			<%Customer user = (Customer)session.getAttribute("loggedInUser");
                    				String userName = user.getCustFirstName();
                    			%>
                    			<li class="nav-item dropdown open">
									<a class="nav-link link dropdown-toggle" href="https://mobirise.com/" aria-expanded="true" data-toggle="dropdown-submenu">HELLO, <% out.print(userName);%></a>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="#">My Account</a>
										<a class="dropdown-item" href="#">Travel Information</a>
										<a class="dropdown-item" href="logout.htm">Logout</a>
									</div>
								</li>
                    		</c:when>
                    		<c:otherwise>
                    			<li class="nav-item nav-btn"><a class="nav-link btn btn-white btn-white-outline" href="register.htm">SIGN UP</a></li>
                    			<li class="nav-item nav-btn"><a class="nav-link btn btn-white btn-white-outline" data-toggle="modal" data-target="#login-modal">LOG IN</a></li>
                    			
                    		</c:otherwise>
                    	</c:choose>
                   	</ul>
                    <button hidden="" class="navbar-toggler navbar-close" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar">
                        <div class="close-icon"></div>
                    </button>

                </div>
            </div>

        </div>