<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表页</title>
    <%@include file="common/head.jsp" %>
</head>
<body>
<!--页面显示部分-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel panel-heading text-center">
            <h2>秒杀列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>库存</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>创建时间</th>
                    <th>详情页</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="s">
                <tr>
                    <td>${s.name}</td>
                    <td>${s.seckillNum}</td>
                    <td> <fmt:formatDate value="${s.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                    <td><fmt:formatDate value="${s.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${s.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a class="btn btn-info" href="/seckill/${s.seckillId}/detail" target="_blank">
                            秒杀详情
                        </a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</div>


</body>
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="${pageContext.request.contextPath}/static/js/jquery-3.0.0.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</html>