<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="supermarket.discount.service.Service"%>
<%@ page import="supermarket.discount.model.Discount"%>
<%@ page import="org.owasp.encoder.Encode" %>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("name") == null) {
		response.sendRedirect("login.jsp");
	}
	%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Discount</title>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous">
<!-- Our Custom CSS -->
<link rel="stylesheet" href="style.css">

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
	integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
	crossorigin="anonymous"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
	integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
	crossorigin="anonymous"></script>
</head>

<body>

	<!--Modal-->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-sm"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Update
						discount</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form method="POST" action="UpdateDiscount">
					<div class="modal-body">
						<div class="form-group">
							<label for="formGroupExampleInput2">Quantity</label> <input
								type="number" class="form-control" id="formGroupExampleInput2"
								placeholder="Quantity" name="quantity">
						</div>
						<div class="form-group">
							<label for="formGroupExampleInput2">Discount</label> <input
								type="number" class="form-control" id="formGroupExampleInput2"
								placeholder="Discount" name="discount">
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" value="" name="prodID">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary" class="ajax-submit-btn">Save
							changes</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--Modal-->
	<div class="wrapper">
		<!-- Sidebar  -->
		<nav id="sidebar">
			<ul class="list-unstyled components">
				<a href="home.jsp"><p><%= session.getAttribute("name") %></p></a>
				<li><a href="dashboard.jsp"><i class="fas fa-chart-line"></i> Dashboard</a></li>
				<li><a href="billing.jsp"><i class="fas fa-calculator"></i> Billing</a></li>
				<li><a href="distributor.jsp"> <i class="fas fa-address-card"></i> Distributors</a></li>
				<li><a href="employee.jsp"><i class="fas fa-user-tie"></i> Employee</a></li>
				<li><a href="discount.jsp"><i class="fas fa-percent"></i> Discounts</a></li>
				<li><a href="purchase.jsp"><i class="fas fa-money-bill"></i> Purchase</a></li>
				<li><a href="order.jsp"><i class="fas fa-truck"></i> Orders</a></li>
				<li><a href="stock.jsp"><i class="fas fa-boxes"></i> Stock</a></li>
				<li><a href="sales.jsp"><i class="fas fa-balance-scale"></i> Sales</a></li>
				<li><a href="product.jsp"><i class="fas fa-archive"></i> Products</a></li>
				<li><a href="store.jsp"><i class="fas fa-building"></i> Store</a></li>
				<li><a href="payments.jsp"><i class="fas fa-money-bill"></i> Payments</a></li>
				<li><a href="endOfDay.jsp"><i class="fas fa-clock"></i> End of day</a></li>
			</ul>
		</nav>
		<!-- Page Content  -->
		<div id="content">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">

					<button type="button" id="sidebarCollapse" class="btn btn-info">
						<i class="fas fa-align-left"></i> <span>Toggle Sidebar</span>
					</button>
					<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
						type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="fas fa-align-justify"></i>
					</button>
				</div>
			</nav>
			<div class="container">
				<div class="row">
					<div class="col-sm">
						<form method="POST" action="SearchDiscount" class="mb-5">
							<div class="form-row">
								<div class="col-6">
									<input type="text" class="form-control"
										placeholder="Product name" name="search" required>
								</div>
								<div class="col-2">
									<button type="submit" class="btn btn-primary">Search</button>
								</div>
							</div>
						</form>
					</div>
					<div class="col-sm">
						<a href="selectproductdiscount.jsp" class="btn btn-success"
							role="button">+ Add discount</a>
					</div>
				</div>
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">ProductID</th>
						<th scope="col">Product name</th>
						<th scope="col">Quantity</th>
						<th scope="col">Discount</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (request.getAttribute("searchDiscount") != null) {
						ArrayList<Discount> result = (ArrayList<Discount>) request.getAttribute("searchDiscount");
						int count = 1;
						for (Discount d : result) {
					%>
					<tr>
						<th scope="row"><%=count%></th>
						<td><a href="#exampleModalCenter" data-toggle="modal"
							data-target="#exampleModalCenter"
							data-product-id=<%=d.getProductID()%>
							data-product-discount=<%=d.getDiscount()%>
							data-product-quantity=<%=d.getQuantity()%> class="text-dark"><%=d.getProductID()%></a></td>
						<td><%= org.owasp.encoder.Encode.forHtml(d.getProductName()) %></td>
						<td><%=d.getQuantity()%></td>
						<td><%=d.getDiscount()%></td>
						<td><form method="POST" action="DeleteDiscount">
								<button type="submit" class="btn btn-primary" class="ajax-submit-btn">Delete</button>
								<input type="hidden" value=<%=d.getProductID()%>
									name="productID">
							</form></td>
					</tr>
					<%
					count++;
					}
					} else {
					ArrayList<Discount> discounts = Service.getDiscounts();
					int count = 1;
					for (Discount d : discounts) {
					%>
					<tr>
						<th scope="row"><%=count%></th>
						<td><a href="#exampleModalCenter" data-toggle="modal"
							data-target="#exampleModalCenter"
							data-product-id=<%=d.getProductID()%>
							data-product-discount=<%=d.getDiscount()%>
							data-product-quantity=<%=d.getQuantity()%> class="text-dark"><%=d.getProductID()%></a></td>
						<td><%= org.owasp.encoder.Encode.forHtml(d.getProductName()) %></td>
						<td><%=d.getQuantity()%></td>
						<td><%=d.getDiscount()%></td>
						<td><form method="POST" action="DeleteDiscount">
								<button type="submit" class="btn btn-primary" class="ajax-submit-btn">Delete</button>
								<input type="hidden" value=<%=d.getProductID()%>
									name="productID">
							</form></td>
					</tr>
					<%
					count++;
					}
					}
					%>
				</tbody>
			</table>

		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	<!-- jQuery CDN - Slim version (=without AJAX) -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<!-- Popper.JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
		integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
		crossorigin="anonymous"></script>
	<!-- Bootstrap JS -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
		integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
		crossorigin="anonymous"></script>


	<script type="text/javascript">
		$(document).ready(function() {
			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').toggleClass('active');
			});
		});
		$('#exampleModalCenter').on('show.bs.modal', function(e) {

			//get data-id attribute of the clicked element
			var prodID = $(e.relatedTarget).data('product-id');
			var quantity = $(e.relatedTarget).data('product-quantity');
			var discount = $(e.relatedTarget).data('product-discount');
			//populate the textbox
			$(e.currentTarget).find('input[name="prodID"]').val(prodID);
			$(e.currentTarget).find('input[name="quantity"]').val(quantity);
			$(e.currentTarget).find('input[name="discount"]').val(discount);

		});
	</script>
</body>

</html>
