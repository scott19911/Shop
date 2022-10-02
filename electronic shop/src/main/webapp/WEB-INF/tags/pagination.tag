<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.filter.pageQuantity - 1 > 0}">
    <c:forEach begin="0" end="${sessionScope.filter.pageQuantity - 1}" var="i">
        <c:choose>
            <c:when test="${sessionScope.filter.pageNumber == i}">
                <li></li>
            </c:when>
            <c:otherwise>
                <li><a href="#" onclick="setAddress(this,${i})">${i+1}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>