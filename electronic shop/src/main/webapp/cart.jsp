<%--
  Created by IntelliJ IDEA.
  User: scott1991
  Date: 09.10.2022
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customtag" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="cart"/></title>

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
                        &nbsp; &nbsp;
                        <customtag:language/>
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
                    <a href="/cart"><fmt:message key="cart"/> - <span class="cart-amunt">₴${sessionScope.cartInfo.totalPrice}</span> <i
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
                    <li class="active"><a href="/cart"><fmt:message key="cart"/></a></li>
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
                    <h2><fmt:message key="shopCart"/></h2>
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
                           style="color:red;font-size: xx-large"><c:if test='${sessionScope.get("specificUser") == null}'>${sessionScope.get("error").get("userError")}</c:if>
                        </p>
                        <p id="errorMassage2"
                           style="color:red;font-size: xx-large"> ${sessionScope.get("error").get("cartError")}
                        </p>
                        <table cellspacing="0" class="shop_table cart">
                            <c:if test="${sessionScope.cartInfo.cart != null}">
                                <form method="get" action="/cart">
                                    <thead>
                                    <tr>
                                        <th class="product-remove">&nbsp;</th>
                                        <th class="product-thumbnail">&nbsp;</th>
                                        <th class="product-name"><fmt:message key="product"/></th>
                                        <th class="product-price"><fmt:message key="price"/></th>
                                        <th class="product-quantity"><fmt:message key="quantity"/></th>
                                        <th class="product-subtotal"><fmt:message key="total"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="entry" items="${sessionScope.cartInfo.cart.entrySet()}">
                                        <tr class="cart_item">
                                            <td class="product-remove">
                                                <a title="Remove this item" class="remove"
                                                   href="/cart?command=update&id=${entry.getKey().productId}&price=${entry.getKey().price}&quantity=0">×</a>
                                            </td>

                                            <td class="product-thumbnail">
                                                <a href="single-product.html"><img width="145" height="145"
                                                                                   alt="poster_1_up"
                                                                                   class="shop_thumbnail"
                                                                                   src="/drawAvatar?productImg=${entry.getKey().imgUrl}"></a>
                                            </td>

                                            <td class="product-name">
                                                <a href="single-product.html">${entry.getKey().name}</a>
                                            </td>

                                            <td class="product-price">
                                                <span class="amount">₴${entry.getKey().price}</span>
                                            </td>

                                            <td class="product-quantity">
                                                <div class="quantity buttons_added">
                                                    <input type="hidden" name="id" value="${entry.getKey().productId}">
                                                    <input type="hidden" name="price" value="${entry.getKey().price}">
                                                    <input type="hidden" name="command" value="update">
                                                    <input type="number" size="4" name="quantity"
                                                           class="input-text qty text" title="Qty"
                                                           value="${entry.getValue()}"
                                                           min="0" step="1">
                                                </div>
                                                <p id="errorProduct${entry.getKey().productId}" style="color:red"> ${sessionScope.get("error").get("errorProduct".concat(entry.getKey().productId))}
                                                </p>
                                            </td>

                                            <td class="product-subtotal">
                                                <span class="amount">₴${entry.getKey().price * entry.getValue()}</span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td class="actions" colspan="6">
                                            <input type="submit" value="<fmt:message key="updateCart"/>" name="update_cart" class="button">
                                            <br>
                                            <br>
                                        </td>
                                    </tr>
                                    </tbody>
                                </form>
                            </c:if>
                            <form action="${pageContext.request.contextPath}/cart" class="checkout" method="get"
                                  name="checkout">
                                <input type="hidden" name="command" value="clear">
                                <input type="submit" value="<fmt:message key="clearCart"/>" id="place" class="button alt">
                            </form>
                        </table>

                        <div class="cart-collaterals">
                            <div class="cart_totals ">
                                <h2><fmt:message key="totalCart"/></h2>

                                <table cellspacing="0">
                                    <tbody>
                                    <tr class="cart-subtotal">
                                        <th><fmt:message key="subtotalCart"/></th>
                                        <td><span class="amount">₴${sessionScope.cartInfo.totalPrice}</span></td>
                                    </tr>

                                    <tr class="shipping">
                                        <th><fmt:message key="ship"/></th>
                                        <td><fmt:message key="free"/></td>
                                    </tr>

                                    <tr class="order-total">
                                        <th><fmt:message key="orderTotal"/></th>
                                        <td><strong><span
                                                class="amount">₴${sessionScope.cartInfo.totalPrice}</span></strong>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>


                            <form method="post" action="/order" class="shipping_calculator">
                                <h2><a onclick="hiddenAllEmptyFields()" class="shipping-calculator-button"
                                       data-toggle="collapse"
                                       href="#calcalute-shipping-wrap" aria-expanded="false"
                                       aria-controls="calcalute-shipping-wrap"><fmt:message key="orderCreate"/></a></h2>

                                <section id="calcalute-shipping-wrap" class="shipping-calculator-form collapse">
                                    <label  for="delivery"><fmt:message key="delivery"/></label>
                                    <p class="form-row form-row-wide">
                                        <select rel="calc_shipping_state" class="country_to_state"
                                                id="delivery" name="delivery">
                                            <option disabled><fmt:message key="delivery"/></option>
                                            <c:forEach items="${sessionScope.payment.delivery.entrySet()}" var="entry">
                                                <option value="${entry.getKey()}"  <c:if test='${sessionScope.get("order").delivery.equals(entry.getKey())}'>selected</c:if>>${entry.getValue()}</option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <label id="cityLabel" for="city"><fmt:message key="city"/></label>
                                    <input type="text" name="city" id="city" value="${sessionScope.get("order").city}">
                                    <p id="errorCity" style="color:red"> ${sessionScope.get("error").get("valCity")}
                                    </p>
                                    <label id="departmentLabel" for="department"><fmt:message key="department"/></label>
                                    <input type="text" name="department" id="department"
                                           value="${sessionScope.get("order").department}">

                                    <label id="streetLabel" for="street"><fmt:message key="Street"/></label>
                                    <input type="text" name="street" id="street"
                                           value="<c:if test='${sessionScope.get("order").department.isBlank()}'>${sessionScope.get("order").street}</c:if>">
                                    <label id="apartmentLabel" for="apartment"><fmt:message key="Apartment/House"/></label>
                                    <input type="text" name="apartment" id="apartment" value="<c:if test='${sessionScope.get("order").department.isBlank()}'>${sessionScope.get("order").house}</c:if>">
                                    <label id="firstNameLabel" for="firstName"><fmt:message key="recName"/></label>
                                    <input type="text" name="firstName" id="firstName" value="${sessionScope.get("order").recipientName}">
                                    <p id="errorName" style="color:red"> ${sessionScope.get("error").get("valName")}
                                    </p>
                                    <label id="surnameLabel" for="surname"><fmt:message key="recSurName"/></label>
                                    <input type="text" name="surname" id="surname" value="${sessionScope.get("order").recipientSurname}">
                                    <p id="errorSurname"
                                       style="color:red"> ${sessionScope.get("error").get("valSurname")}
                                    </p>
                                    <label id="phoneLabel" for="phone"><fmt:message key="recPhone"/></label>
                                    <input type="text" name="phone" id="phone" value="${sessionScope.get("order").recipientPhone}">
                                    <p id="errorPhone" style="color:red"> ${sessionScope.get("error").get("valPhone")}
                                    </p>
                                    <label  for="payment"><fmt:message key="payment"/></label>
                                    <p class="form-row form-row-wide">
                                        <select rel="calc_shipping_state" class="country_to_state"
                                                id="payment" name="payment">
                                            <option disabled><fmt:message key="payment"/></option>
                                            <c:forEach items="${sessionScope.payment.payment.entrySet()}" var="entry">
                                                <option value="${entry.getKey()}" <c:if test='${sessionScope.get("order").payment.equals(entry.getKey())}'>selected</c:if>>${entry.getValue()}</option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                    <label id="cardLabel" for="card"><fmt:message key="card"/></label>
                                    <input type="text" name="card" id="card" value="${sessionScope.get("order").cardNumber}">
                                    <p id="errorCard" style="color:red"> ${sessionScope.get("error").get("valCard")}
                                    </p>
                                    <label id="dataLabel" for="data"><fmt:message key="per"/></label>
                                    <input type="text" name="data" id="data" value="${sessionScope.get("order").dataCard}">
                                    <p id="errorCardData"
                                       style="color:red"> ${sessionScope.get("error").get("valCardData")}
                                    </p>
                                    <label id="cvv2Label" for="cvv2">CVV2</label>
                                    <input type="password" name="cvv2" id="cvv2" value="">
                                    <p id="errorCvv2" style="color:red"> ${sessionScope.get("error").get("valCardCVV2")}
                                    </p>
                                    <br>
                                    <br>
                                    <p>
                                        <button class="button" value="1" name="calc_shipping" type="submit"><fmt:message key="create"/>
                                        </button>
                                    </p>

                                </section>
                            </form>


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
<script src="js/orderFields.js"></script>
<script src="js/selectPageSize.js"></script>
<script src="js/paginationUrl.js"></script>
<script src="js/multi-select-dropdown.js"></script>
</body>
</html>
