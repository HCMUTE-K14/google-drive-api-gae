<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Upload</title>
<!-- Bootstrap core CSS -->

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
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">

		<form class="form-horizontal" method="post" action="upload"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Tiêu đề</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" name="title"
						placeholder="Tiêu đề" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="message" class="col-sm-2 control-label">Nội dung</label>
				<div class="col-sm-10">
					<textarea id="editor" class="form-control" rows="4" name="content"></textarea>
					<script>
						CKEDITOR.replace("editor");
					</script>
				</div>
			</div>
			<div class="form-group">
				<label for="message" class="col-sm-2 control-label">Đính kèm</label>
				<div class="col-sm-10">
					<div class="input-group">
						<label class="input-group-btn"> <span
							class="btn btn-primary"> Browse&hellip; <input type="file"
								name="file" style="display: none;" multiple='multiple'></span>
						</label> <input type="text" class="form-control" readonly>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2">
					<input id="submit" name="submit" type="submit" value="Send"
						class="btn btn-primary">
				</div>
			</div>
		</form>
	</div>

	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<span>${requestScope.message}</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var message = '${requestScope.message}';
		if (message != null) {
			$('#myModal').modal()
		}
	</script>
	<script type="text/javascript" src="js/myscript.js"></script>

</body>

</html>