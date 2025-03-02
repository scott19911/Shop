<%--
  Created by IntelliJ IDEA.
  User: scott1991
  Date: 23.09.2022
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customTag" tagdir="/WEB-INF/tags" %>
<html><head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>eElectronics - HTML eCommerce Template</title>

  <!-- Google Fonts -->
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
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
        <customTag:login></customTag:login>
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

      <div class="col-sm-6">
        <div class="shopping-item">
          <a href="/cart">Cart - <span class="cart-amunt">₴${sessionScope.cartInfo.totalPrice}</span> <i class="fa fa-shopping-cart"></i> <span
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
          <li class="active"><a href="${pageContext.request.contextPath}/shop"><fmt:message key="shop"/></a></li>
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
          <h2><fmt:message key="shops"/></h2>
        </div>
      </div>
    </div>
  </div>
</div>


<div class="single-product-area">
  <div class="zigzag-bottom"></div>
  <div class="product-pagination text-center">
    <nav>
      <ul class="pagination">
        <customTag:pagination/>
      </ul>
    </nav>
  </div>
  <br>
  <div class="col-md-2 col-sm-2">
    <select name="pageSize" id="pageSize" onchange="setPageSize(this)">
      <option value=""><fmt:message key="quantityEl"/></option>
      <option value="3">3</option>
      <option value="6">6</option>
      <option value="10">10</option>
    </select>
    <br>
    <br>
    <select name="orderBy" id="orderBy" onchange="setOrderBy(this)">
      <option value=""><fmt:message key="selOrder"/></option>
      <option value="ASC"><fmt:message key="priceIncrease"/></option>
      <option value="DESC"><fmt:message key="priceLower"/></option>
    </select>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/shop" class="checkout" method="get" name="checkout">
      <p><fmt:message key="price"/></p>
      <div class="InputInline">
        <input type="number" name="minPrice" id="minPrice" placeholder="Min" onchange="setPageSize()"
               value="${sessionScope.productData.minPrice}">

        <input type="number" name="maxPrice" id="maxPrice" placeholder="Max" value="${sessionScope.productData.maxPrice}">
      </div>
      <input type="hidden" name="pageSize" value="${sessionScope.productData.productFilter.pageSize}">
      <input type="hidden" name="pageSize" value="${sessionScope.productData.productFilter.order}">
      <br>
      <label for="productName"><fmt:message key="productName"/></label>
      <input type="text" name="productName" id="productName" value="${sessionScope.productData.productFilter.productName}">
      <br>
      <br>
      <label for="brand"><fmt:message key="chooseBrand"/></label>
      <select multiple="multiple" search='true' name="brand" id="brand" style="width: 98%">
        <c:forEach items="${sessionScope.productData.brandList}" var="brand">
          <option<c:if test="${sessionScope.productData.productFilter != null && sessionScope.productData.productFilter.getBrand().contains(brand)}"> selected</c:if>
                  value="${brand}">${brand}</option>
        </c:forEach>
      </select>
      <br>
      <br>
      <label for="category"><fmt:message key="chooseCategory"/></label>
      <select multiple="multiple" search='true' name="category" id="category" style="width: 98%">
        <c:forEach items="${sessionScope.productData.categoryDTOList}" var="category">
          <option <c:if test="${sessionScope.productData.productFilter != null && sessionScope.productData.productFilter.getCategory().contains(category.categoryId)}"> selected</c:if>
                  value="${category.categoryId}">${category.categoryName}</option>
        </c:forEach>
      </select>
      <br>
      <br>
      <input type="submit" data-value="Place order" value="<fmt:message key="search"/>"
             id="place_order"
             class="button alt">
    </form>
  </div>
  <div class="container">
    <div class="row">
      <c:if test="${sessionScope.productData.productList == null || sessionScope.productData.productList.size() == 0}">
        <h2 style="font-size: xx-large; color: #ac2925"><fmt:message key="productNotFound"/></h2>
      </c:if>
      <c:forEach items="${sessionScope.productData.productList}" var="product">
        <div class="col-md-offset-3 col-sm-3">
          <div class="single-shop-product">
            <div class="product-upper">
              <img src="${pageContext.request.contextPath}/drawAvatar?productImg=${product.imgUrl}" alt="">
            </div>
            <h2> ${product.brand}</h2>
            <h2><a href="">${ product.name}</a></h2>
            <p>${product.description}</p>
            <h2>
              <div class="product-carousel-price">
                <ins>₴${product.price}</ins>
                <del>₴${product.price + (product.price * 0.2)}</del>
              </div>
            </h2>

            <form action="${pageContext.request.contextPath}/cart" class="checkout" method="get" name="checkout" onsubmit="setInput(${product.productId})">
              <input type="hidden" name="cameFrom" id="cameFrom${product.productId}">
              <input type="hidden" name="id" value="${product.productId}">
              <input type="hidden" name="price" value="${product.price}">
              <input type="hidden" name="command" value="update">
              <input type="hidden" name="quantity" value="${sessionScope.cartInfo.cart.get(product) + 1}">
              <input type="submit" value="<fmt:message key="add"/>" id="place" class="button alt">
            </form>
          </div>
        </div>
      </c:forEach>
    </div>

    <div class="row">
      <div class="col-md-12">
        <div class="product-pagination text-center">
          <nav>
            <ul class="pagination">
              <customTag:pagination/>
            </ul>
          </nav>
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
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis sunt id doloribus vero quam laborum quas alias dolores blanditiis iusto consequatur, modi aliquid eveniet eligendi iure eaque ipsam iste, pariatur omnis sint! Suscipit, debitis, quisquam. Laborum commodi veritatis magni at?</p>
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
          <p>Sign up to our newsletter and get exclusive deals you wont find anywhere else straight to your inbox!</p>
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
          <p>&copy; 2015 eElectronics. All Rights Reserved. Coded with <i class="fa fa-heart"></i> by <a href="http://wpexpand.com" target="_blank">WP Expand</a></p>
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
<script src="js/selectPageSize.js"></script>
<script src="js/paginationUrl.js"></script>
<script src="js/multi-select-dropdown.js"></script>
</body>
</html>
