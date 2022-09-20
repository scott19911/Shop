<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="role" required="true" rtexprvalue="true" %>
<p>

        <img src="${pageContext.request.contextPath}/captcha?captchaId=${captchaId}"/>
        <c:if test='${pageContext.servletContext.getInitParameter("storeType").equals("field")}'>
                <input type ="hidden" name = "captchaId" value = "${captchaId}">
        </c:if>

</p>