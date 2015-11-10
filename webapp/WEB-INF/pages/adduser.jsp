<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,com.restcontroller.app.entities.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
<link rel="stylesheet" media="screen"
	href="${pageContext.request.contextPath}/resources/fonts/font-awesome/font-awesome.min.css" />
<link rel="stylesheet" media="screen"
	href="${pageContext.request.contextPath}/resources/jasny-bootstrap/css/jasny-bootstrap.css" />

<style>
.indent-small {
	margin-left: 5px;
}

.form-group.internal {
	margin-bottom: 0;
}

.dialog-panel {
	margin: 10px;
}

.datepicker-dropdown {
	z-index: 200 !important;
}

.panel-body {
	background: #e5e5e5; /* Old browsers */
	background: -moz-radial-gradient(center, ellipse cover, #e5e5e5 0%, #ffffff 100%);
	/* FF3.6+ */
	background: -webkit-gradient(radial, center center, 0px, center center, 100%,
		color-stop(0%, #e5e5e5), color-stop(100%, #ffffff));
	/* Chrome,Safari4+ */
	background: -webkit-radial-gradient(center, ellipse cover, #e5e5e5 0%, #ffffff 100%);
	/* Chrome10+,Safari5.1+ */
	background: -o-radial-gradient(center, ellipse cover, #e5e5e5 0%, #ffffff 100%);
	/* Opera 12+ */
	background: -ms-radial-gradient(center, ellipse cover, #e5e5e5 0%, #ffffff 100%);
	/* IE10+ */
	background: radial-gradient(ellipse at center, #e5e5e5 0%, #ffffff 100%);
	/* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e5e5e5',
		endColorstr='#ffffff', GradientType=1);
	/* IE6-9 fallback on horizontal gradient */
	font: 600 15px "Open Sans", Arial, sans-serif;
}

label.control-label {
	font-weight: 600;
	color: #777;
}
input[type=text],input[type=password],input[type=email],input[type=date]{
  border-radius: 0px;
}
</style>
<title>New User Form</title>
</head>
<body>
	<div class='container'>
		<div class='panel panel-primary dialog-panel'>
			<div class='panel-heading'>
				<h1>${formTitle}</h1>
			</div>
			<div class='panel-body'>
				<form class='form-horizontal' role='form' enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/${action}" id="frmAdd">
					<c:if test="${btn eq 'Save'}">
						<input type="hidden" name="id" value="${user.id }"/>
					</c:if>
						
					<div class='form-group'>
						<label class='control-label col-md-2 col-md-offset-2'>Username</label>
						<div class='col-md-8'>
							<div class='col-md-8'>
								<div class='form-group internal'>
									<input class='form-control' name='username'
										placeholder='Username' type='text' value="${user.username}">
								</div>
							</div>
						</div>
					</div>
					<div class='form-group'>
						<label class='control-label col-md-2 col-md-offset-2'>E-mail</label>
						<div class='col-md-8'>
							<div class='col-md-8'>
								<div class='form-group internal'>
									<input class='form-control' name='email' placeholder='email'
										type='email' value="${user.email}">
								</div>
							</div>
						</div>
					</div>
					<div class='form-group'>
						<label class='control-label col-md-2 col-md-offset-2'>Password</label>
						<div class='col-md-8'>
							<div class='col-md-8'>
								<div class='form-group internal'>
									<input class='form-control' name='password'
										placeholder='Password' type='password' value="${user.email}">
								</div>
							</div>
						</div>
					</div>
					<div class='form-group'>
						<label class='control-label col-md-2 col-md-offset-2'>Birth
							Of Date</label>
						<div class='col-md-8'>
							<div class='col-md-8'>
								<div class='form-group internal'>
									<input class='form-control' name='birthdate' placeholder='Birth Of Date' type='date' value="${user.birthdate}"> 
								</div>
							</div>
						</div>
					</div>
					<div class='form-group'>
						<label class='control-label col-md-2 col-md-offset-2'>Register
							Date</label>
						<div class='col-md-8'>
							<div class='col-md-8'>
								<div class='form-group internal'>
									<c:set var="now" value="<%=new java.util.Date()%>" />
									<%-- <c:set var="registerdate" value="${user.registerDate} " /> --%>
									<input class='form-control' name='registerDate'
										placeholder='Register Date' type='text'
										value='<c:if test="${empty user.registerDate}"><fmt:formatDate value="${now}" type="date" pattern="yyyy-MM-dd" /></c:if><c:if test="${not empty user.registerDate}"><c:out value="${user.registerDate}" escapeXml="false" /></c:if>' readonly="readonly">
								</div>
							</div>
						</div>
					</div>
					<div class="form-group ">
						<div class='col-md-8 col-md-offset-4'>
							<c:if test="${empty user.imageURL }">
								<div class="fileinput fileinput-new" data-provides="fileinput">
									<div class="fileinput-preview thumbnail"
										data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
									<div>
										<!-- <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"></div> -->
										<span class="btn btn-default btn-file"><span
											class="fileinput-new">Select image</span><span
											class="fileinput-exists">Change</span><input type="file"
											name="file"></span> <a href="#"
											class="btn btn-default fileinput-exists"
											data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</c:if>
							
							<c:if test="${not empty user.imageURL }">
								<div class="fileinput fileinput-exist" data-provides="fileinput">
									<div class="fileinput-preview thumbnail"
										data-trigger="fileinput" style="width: 200px; height: 150px;"><img src="${pageContext.request.contextPath}/resources/upload/${user.imageURL}" /></div>
									<div>
										<span class="btn btn-default btn-file"><span
											class="fileinput-new">Select image</span><span
											class="fileinput-exists">Change</span><input type="file"
											name="file"></span> <a href="#"
											class="btn btn-default fileinput-exists"
											data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</c:if>
							
						</div>
					</div>
					<div class='form-group'>
            <div class='col-md-8 col-md-offset-4'>
  					   <div class='col-md-6' style="padding-left:0px;">
  						  <input type="submit" name="btnAddNCancel" value="${btn}" class="btn btn-primary ">
  							<input type="submit" name="btnAddNCancel" value="Cancel" class="btn btn-danger ">
  						 </div>
            </div>
					</div>
				</form>

			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/jasny-bootstrap/js/jasny-bootstrap.js" />

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
	<script>


		$("#frmAdd").submit(function(e) {
			e.preventDefault();
			$.ajax({
				type : "POST",
				url : "http://localhost:8080/HwRestController/api/add",
				enctype : 'multipart/form-data',
				data : new FormData(document.getElementById("frmAdd")),
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				success : function(data) {
					if (data.SUCCESS == 200) {
						alert("olo");
						location.href="http://localhost:8080/HwRestController/";
					}
				},
				error : function(data) {
					
				}
			})
		});

		



	</script>
</body>
</html>