<%--
  Created by IntelliJ IDEA.
  User: scott1991
  Date: 09.10.2022
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="customtag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orders</title>

    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet'
          type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/inputInLine.css">

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
                <customtag:login/>
            </div>

            <div class="col-md-5">
                <div class="header-right">
                    <ul class="list-unstyled list-inline">
                        <a href="/reg"><fmt:message key="sign.up" /></a>
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

            <div class="col-sm-6">
                <div class="shopping-item">
                    <a href="/cart"><fmt:message key="cart" /> - <span class="cart-amunt">₴${sessionScope.cartInfo.totalPrice}</span> <i
                            class="fa fa-shopping-cart"></i> <span
                            class="product-count">${sessionScope.cartInfo.totalQuantity}</span></a>
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
                    <li><a href="/index.jsp"><fmt:message key="home"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/shop"><fmt:message key="shop"/></a></li>
                    <li><a href="/cart"><fmt:message key="cart"/></a></li>
                    <c:if test='${sessionScope.get("specificUser").getUserRole().equals("admin")}'>
                        <li><a href="/confirm"><fmt:message key="orderProcessing"/></a></li>
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
                    <h2><fmt:message key="orders" /></h2>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End Page title area -->

<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">

            <div class="col-md-8">
                <div class="product-content-right">
                    <div class="woocommerce">
                        <p id="errorMassage1"
                           style="color:red;font-size: xx-large"><c:if
                                test='${sessionScope.get("specificUser") == null}'>${sessionScope.get("error").get("userError")}</c:if>
                        </p>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                            <tr>
                                <th class="product-thumbnail"><fmt:message key="Norders" /></th>
                                <th class="product-name"><fmt:message key="date" /></th>
                                <th class="product-price"><fmt:message key="status" /></th>
                                <th class="product-quantity"><fmt:message key="stDes" /></th>
                                <th class="product-quantity"><fmt:message key="deliveryType" /></th>
                                <th class="product-subtotal"><fmt:message key="totPrice" /></th>
                                <c:if test='${sessionScope.goTo.equals("/confirm")}'>
                                    <th class="product-subtotal"><fmt:message key="update" /></th>
                                </c:if>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="orderInfo" items="${sessionScope.orderInfo.orderInfoList}">
                                <tr class="cart_item">
                                    <td class="product-thumbnail">
                                            ${orderInfo.orderNumber}
                                    </td>
                                    <td class="product-name">
                                        <fmt:formatDate type="date" value="${orderInfo.orderDate}"
                                                        pattern="dd.MM.yyyy HH:MM"/>
                                    </td>

                                    <td class="product-price">
                                        <c:if test='${!sessionScope.goTo.equals("/confirm")}'>
                                            ${orderInfo.orderStatus}
                                        </c:if>
                                        <c:if test='${sessionScope.goTo.equals("/confirm")}'>
                                        <form method="post" action="/confirm">
                                            <select name="status">
                                                <c:forEach items="${sessionScope.orderInfo.getStatusMap().entrySet()}"
                                                           var="orderStatus">
                                                    <option value="${orderStatus.getKey()}"
                                                            <c:if test="${orderStatus.getKey().equals(orderInfo.orderStatus)}">
                                                                selected
                                                            </c:if>
                                                    >${orderStatus.getValue()}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                    </td>

                                    <td class="product-quantity">
                                            ${orderInfo.statusDescription}
                                        <c:if test='${sessionScope.goTo.equals("/confirm")}'>
                                            <input type="text" name="description">
                                        </c:if>
                                    </td>

                                    <td class="product-subtotal">
                                            ${orderInfo.deliveryType}
                                    </td>
                                    <td class="product-subtotal">
                                            ${orderInfo.totalPrice}
                                    </td>
                                    <c:if test='${sessionScope.goTo.equals("/confirm")}'>
                                        <td>
                                            <input type="submit" value="<fmt:message key="update"/>" name="update_cart" class="button">
                                           </form>
                                        </td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td class="product-subtotal" colspan="7">
                                        <customtag:orderDetails orderId="${orderInfo.orderNumber}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
<script src="js/orderFields.js"></script>
<script src="js/selectPageSize.js"></script>
<script src="js/paginationUrl.js"></script>
<script src="js/multi-select-dropdown.js"></script>
</body>
</html>
