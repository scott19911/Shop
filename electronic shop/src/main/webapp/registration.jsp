<%--
  Created by IntelliJ IDEA.
  User: scott1991
  Date: 08.09.2022
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="Registration"/></title>
    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet'
          type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" href="/css/avatarReg.css">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/checkbox.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body>
<div class="header-area">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <customTag:login/>
            </div>
            <div class="col-md-5">
                <div class="header-right">
                    <ul class="list-unstyled list-inline">
                        <a href="${pageContext.request.contextPath}/reg"><fmt:message key="sign.up" /></a>
                        <customTag:language/>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End header area -->

<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1><a href="index.jsp">e<span>Electronics</span></a></h1>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->

<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="index.jsp"><fmt:message key="home"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/shop"><fmt:message key="shop"/></a></li>
                    <li><a href="/cart"><fmt:message key="cart"/></a></li>
                    <c:if test='${sessionScope.get("specificUser").getUserRole().equals("admin")}'>
                        <li><a href="/confirm"><fmt:message key="orderProcessing"/></a></li>
                        <li><a href="/userList"><fmt:message key="userinfo"/></a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End mainmenu area -->

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2><fmt:message key="Registration"/></h2>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="product-content-right">
                    <div class="woocommerce">

                        <div id="customer_details" class="col2-set">
                            <div class="col-1">
                                <div class="woocommerce-billing-fields">
                                    <form action="/reg" class="checkout" method="post" name="checkout"
                                          onsubmit="return validateForm()">
                                        <div class="create-account">
                                            <p>Create an account by entering the information below. If you are a
                                                returning customer please login at the top of the page.</p>
                                            <p id="billing_first_name_field"
                                               class="form-row form-row-first validate-required">
                                                <label class="" for="billing_first_name"><fmt:message key="fName"/><abbr
                                                        title="required"
                                                        class="required">*</abbr>
                                                </label>
                                                <input type="text" value="${requestScope.get('com.example.electronicshop.registration.bean').getFirstName()}" placeholder="" id="billing_first_name"
                                                       name="billing_first_name" class="input-text ">
                                            <p id="demo" style="color: red;"></p>
                                            <p id="errorMassage" style="color:red"> ${requestScope.get("com.example.electronicshop.registration.error").get("billing_first_name")}
                                                </p>
                                            </p>
                                            <p id="billing_last_name_field"
                                               class="form-row form-row-last validate-required">
                                                <label class="" for="billing_last_name"><fmt:message key="lName"/> <abbr title="required"
                                                                                                        class="required">*</abbr>
                                                </label>
                                                <input type="text" value="${requestScope.get('com.example.electronicshop.registration.bean').getLastName()}" placeholder="" id="billing_last_name"
                                                       name="billing_last_name" class="input-text ">
                                            <p id="demo1" style="color: red;"></p>
                                            <p id="errorMassage" style="color:red"> ${requestScope.get("com.example.electronicshop.registration.error").get("billing_last_name")}

                                            <div class="clear"></div>
                                            <p id="billing_email_field"
                                               class="form-row form-row-first validate-required validate-email">
                                                <label class="" for="billing_email"><fmt:message key="email"/><abbr title="required"
                                                                                                        class="required">*</abbr>
                                                </label>
                                                <input type="text" value="${requestScope.get('com.example.electronicshop.registration.bean').getEmail()}" placeholder="" id="billing_email"
                                                       name="billing_email" class="input-text ">
                                            <p id="demo5" style="color: red;"></p>
                                            <p id="errorMassage" style="color:red"> ${requestScope.get("com.example.electronicshop.registration.error").get("billing_email")}
                                            </p>

                                            <p id="account_password_field" class="form-row validate-required">
                                                <label class="" for="account_password"><fmt:message key="pas"/><abbr
                                                        title="required" class="required">*</abbr>
                                                </label>
                                                <input type="password" value="" placeholder="Password"
                                                       id="account_password" name="account_password" class="input-text">
                                            <p id="demo7" style="color: red;"></p>
                                            <p id="errorMassage" style="color:red"> ${requestScope.get("com.example.electronicshop.registration.error").get("account_password")}
                                            </p>
                                            <customTag:captcha/>
                                            <p id="account_password_field" class="form-row validate-required">
                                                <label class="" for="captcha"><fmt:message key="Veri"/><abbr
                                                        title="required" class="required">*</abbr>
                                                </label>
                                                <input type="text" value="" placeholder="Captcha"
                                                       id="captcha" name="captcha" class="input-text">
                                            <p id="demo8" style="color: red;"></p>
                                            <p id="errorMassage" style="color:red"> ${requestScope.get("com.example.electronicshop.registration.error").get("captcha")}
                                            </p>
                                            <div class="cont">
                                            <p class="notifications"><fmt:message key="Receive"/></p>
                                            <label class="switch">
                                                <input type="checkbox" name="notifications" value="true">
                                                <span class="slider round"></span>
                                            </label>
                                            </div>
                                            <div class="form-row place-order">
                                                <input type="submit" data-value="Place order" value="<fmt:message key="Registration"/>"
                                                       id="place_order" name="woocommerce_checkout_place_order"
                                                       class="button alt">
                                            </div>
                                            <div class="clear"></div>
                                        </div>
                                    </form>
                                </div>

                            </div>
                            <div class="col-2">
                                <form method="post" action="${pageContext.request.contextPath}/uploadFile"
                                      enctype="multipart/form-data" class="avatarLoading">
                                    Select avatar:
                                    <br/>
                                    <input type="file" name="file"/>
                                    <br/>
                                    <input type="submit" value="Upload"/>
                                </form>
                                <p>
                                <div class="avatar">
                                    <img src="/drawAvatar" class="avatar__image">
                                </div>
                                </p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="footer-top-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-sm-6">
                <div class="footer-about-us">
                    <h2>e<span>Electronics</span></h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis sunt id doloribus vero quam
                        laborum quas alias dolores blanditiis iusto consequatur, modi aliquid eveniet eligendi iure
                        eaque ipsam iste, pariatur omnis sint! Suscipit, debitis, quisquam. Laborum commodi veritatis
                        magni at?</p>
                    <div class="footer-social">
                        <a href="#" target="_blank"><i class="fa fa-facebook"></i></a>
                        <a href="#" target="_blank"><i class="fa fa-twitter"></i></a>
                        <a href="#" target="_blank"><i class="fa fa-youtube"></i></a>
                        <a href="#" target="_blank"><i class="fa fa-linkedin"></i></a>
                        <a href="#" target="_blank"><i class="fa fa-pinterest"></i></a>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="footer-menu">
                    <h2 class="footer-wid-title">User Navigation </h2>
                    <ul>
                        <li><a href="">My account</a></li>
                        <li><a href="">Order history</a></li>
                        <li><a href="">Wishlist</a></li>
                        <li><a href="">Vendor contact</a></li>
                        <li><a href="">Front page</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="footer-menu">
                    <h2 class="footer-wid-title">Categories</h2>
                    <ul>
                        <li><a href="">Mobile Phone</a></li>
                        <li><a href="">Home accesseries</a></li>
                        <li><a href="">LED TV</a></li>
                        <li><a href="">Computer</a></li>
                        <li><a href="">Gadets</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="footer-newsletter">
                    <h2 class="footer-wid-title">Newsletter</h2>
                    <p>Sign up to our newsletter and get exclusive deals you wont find anywhere else straight to your
                        inbox!</p>
                    <div class="newsletter-form">
                        <input type="email" placeholder="Type your email">
                        <input type="submit" value="Subscribe">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer-bottom-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="copyright">
                    <p>&copy; 2015 eElectronics. All Rights Reserved. Coded with <i class="fa fa-heart"></i> by <a
                            href="http://wpexpand.com" target="_blank">WP Expand</a></p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="footer-card-icon">
                    <i class="fa fa-cc-discover"></i>
                    <i class="fa fa-cc-mastercard"></i>
                    <i class="fa fa-cc-paypal"></i>
                    <i class="fa fa-cc-visa"></i>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Latest jQuery form server -->
<script src="https://code.jquery.com/jquery.min.js"></script>

<!-- Bootstrap JS form CDN -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- jQuery sticky menu -->
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.sticky.js"></script>

<!-- jQuery easing -->
<script src="js/jquery.easing.1.3.min.js"></script>

<!-- Main Script -->
<script src="js/main.js"></script>
<script src="/js/validateJs.js"></script>
<script src="js/paginationUrl.js"></script>
</body>
</html>
