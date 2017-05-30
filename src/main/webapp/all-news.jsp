<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>All-news</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<sql:setDataSource var="con" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://google/db_example_springboot?cloudSqlInstance=newprojectspringboot:us-central1:springbootinstance&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root&password=kien2509&useSSL=false
"
		user="root" password="kien2509" />
	<sql:query var="result"
		sql="select * from news where id ='${param.id}'" dataSource="${con}" />
		<jsp:include page="header.jsp" ></jsp:include>
	<div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Title</th>
					<th>ID</th>
					<th>View</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${result.rows}">
					<tr>
					<td><a>${row.title }</a></td>
					<td><a>${row.id }</a></td>
					<td><a href="@{'view?id='+${row.id}}">Link</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>