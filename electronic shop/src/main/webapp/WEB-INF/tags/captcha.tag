<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<p>
        <img src="${pageContext.request.contextPath}/captcha?captchaId=${captchaId}" alt=""/>
        <c:if test='${pageContext.servletContext.getInitParameter("storeType").equals("field")}'>
                <input type ="hidden" name = "captchaId" value = "${captchaId}">
        </c:if>
</p>