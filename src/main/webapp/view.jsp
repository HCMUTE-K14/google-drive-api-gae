<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>View</title>

<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/starter-template.css" rel="stylesheet" />
<script src="js/jquery221.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/ckeditor/ckeditor.js"></script>
<style type="text/css">
.btn-file {
	position: relative;
	overflow: hidden;
}

.btn-file input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	min-width: 100%;
	min-height: 100%;
	font-size: 100px;
	text-align: right;
	filter: alpha(opacity = 0);
	opacity: 0;
	outline: none;
	background: white;
	cursor: inherit;
	display: block;
}
</style>

</head>
<body>
	<sql:setDataSource var="con" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://google/db_example_springboot?cloudSqlInstance=newprojectspringboot:us-central1:springbootinstance&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root&password=kien2509&useSSL=false
"
		user="root" password="kien2509" />
	<sql:query var="result"
		sql="select * from news where id ='${param.id}'" dataSource="${con}" />
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<c:forEach var="news" items="${result.rows}">
			<form class="form-horizontal">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Tiêu đề</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="title" name="title"
							placeholder="Tiêu đề" th:value=${news.title } disabled>
					</div>
				</div>
				<div class="form-group">
					<label for="message" class="col-sm-2 control-label">Nội
						dung</label>
					<div class="col-sm-10">
						<textarea id="editor" class="form-control" rows="4" name="content"
							disabled></textarea>
						<script type="text/javascript">
						CKEDITOR.replace("editor");
						CKEDITOR.instances["editor"].setData([[${news.content}]])
					</script>
					</div>
				</div>
				<div class="form-group">
					<label for="message" class="col-sm-2 control-label">Đính
						kèm</label>
					<div class="col-sm-10">
						<div class="input-group">
							<a class="input-group-btn" href="${news.attactlink}"
								target="_blank"> <span class="btn btn-primary">
									Download&hellip; </span>
							</a> <input type="text" class="form-control" readonly
								value="${#httpServletRequest.getContextPath()}__/__${news.attactlink}">
						</div>
					</div>
				</div>

			</form>
		</c:forEach>
	</div>
</body>
</html>