<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myapp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/resources/fonts/font-awesome/font-awesome.min.css" />
<title>REST Controller</title>
<style type="text/css" media="screen">
	.btn{
		border-radius: 0px !important;
	}
	th{
		color: #ffffff;
  		text-transform: uppercase;
  		letter-spacing: 2px;
  		font-size: 17px;
  		text-align: center;
  		font-weight: 700;
	}
	thead tr {
		background-color: #34495e;
	}
	tbody tr{
		background-color: #ffffff;
		color:#bdc3c7;
		text-align: center;
		font-size: 18px !important;
	}
	input[type=text]{
		border-radius: 0px;
	}

	.form-submit form{
		display: inline-block;
		
	}
	
	td:not(:nth-child(7))  {
  		line-height: 100px !important;
	}
	

</style>
</head>
<body ng-controller="mycontroller">
	<div class="container-fluid">
		<div class="row text-center text-danger">
			<h1>USE RESTFULL CONTROLLER</h1>
		</div>
		<div class="col-lg-8 col-lg-offset-2">
			<div class="row" style="margin-bottom:5px;">
				<div class="col-lg-12">
					<form action="${pageContext.request.contextPath }/addUserNSearch" class="form-inline" method="post">
							<input type="submit" name="btnAddNSearch" value="Add New" class="btn btn-primary">
							<!-- <input type="submit" name="btnAddNSearch" value="Search" class="btn btn-default pull-right">&nbsp; -->
							<div class="form-group pull-right">
								<input type="text" ng-model="txtSearch" class="form-control">
							</div>

					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>ID</th>
									<th>Username</th>
									<th>Email</th>
									<th>Password</th>
									<th>Date of Birth</th>
									<th>Register Date</th>
									<th>Image</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								
									<tr ng-repeat="user in users | filter:txtSearch">
										<td>{{$index+1}}</td>
										<td>{{user.username}}</td>
										<td>{{user.email}}</td>
										<td>**********</td>
										<td>{{user.birthdate}}</td>
										<td>{{user.registerDate}}</td>
										<td ><img ng-src="${pageContext.request.contextPath}/resources/upload/{{user.imageURL}}" class="img-responsive" style="height:100px;width:100px"/></td>
										<td class="form-submit">
											<form action="${pageContext.request.contextPath}/viewUpdateDeleteUser" method="post">
												<input type="hidden" name="userId" value="{{user.id}}">
												<input type="submit" name="btnViewUpdateDelete" value="View" class="btn btn-primary btn-sm">
												<input type="submit" name="btnViewUpdateDelete" value="Update" class="btn btn-warning btn-sm">
												<input type="button" name="btnViewUpdateDelete" value="Delete" class="btn btn-danger btn-sm" ng-click="deleteUser(user.id)" />
											</form>
											
										</td>
									</tr>	

									
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
	<script>

	var app = angular.module("myapp", []);
	app.controller("mycontroller", function($scope,$http) {

		loadData();
		
		function loadData(){
			 $http.get("http://localhost:8080/HwRestController/api/").success(function(response) {
			 	$scope.users = response.RESPONSE_DATA;
			 });

		}

		$scope.deleteUser = function(id){
			var x = confirm("Are you sure to delete?");
			if(x==true){
			$http.get("http://localhost:8080/HwRestController/api/delete",{params: {id:id}})
			.then(function(data){
				loadData();
			}, function(){
				
			});
			}
		};
	
		

	});

	</script>
</body>
</html>