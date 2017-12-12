<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />

<c:set var="contextRoot" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Shop n Go - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme CSS.It did not work properly.I have to revisit this. -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!--  Bootstrap Datatables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">

		<!-- Navigation -->

		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
				</div>
			</div>
		</nav>

		<!-- Page Content -->
		<div class="content">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="jumbotron">
							<h1>${errorTitle}</h1>
							<hr/>
							<!--  styling blockquote to avoid overflow of text-->
							<blockquote style="word-wrap:break-word">
								${errorDescription}
								
							</blockquote>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.js"></script>

		<script src="${js}/popper.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!--  DataTable plugin -->
		<script src="${js}/jquery.dataTables.js"></script>

		<!-- DataTable Bootstrap plugin -->
		<script src="${js}/dataTables.bootstrap.js"></script>

		<!-- DataTable Bootstrap Script -->
		<script src="${js}/bootbox.min.js"></script>

		<!--  Self coded java script -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>
