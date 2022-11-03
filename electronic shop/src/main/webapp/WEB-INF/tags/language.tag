<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<select name="lang" id="lang" onchange="langAddress(this)">
    <option value=""><fmt:message key="settings_jsp.label.set_locale" /></option>
    <c:forEach items="${applicationScope.locales}" var="locale">
    <option value="${locale.key}">${locale.value}</option>
    </c:forEach>
</select>
