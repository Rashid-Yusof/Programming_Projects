<!DOCTYPE>
<?php
include("functions/functions.php");
?>

<html>
	<head>
		<title>My Online Shop</title>
	<link rel="stylesheet" href="style.css" media="all" />
	</head>
<body>

	<!-- Main Div Design -->
	<div class="main_wrapper">

		<!-- Header -->
		<div class = "header_wrapper">

			<img id = "logo" src="images/Drawing.png">

		</div>

		<!-- Menu -->

		<div class="menubar">
				<ul id="menu">
					<li><a href="index.php">Home</a></li>
					<li><a href="Shop_Cart.php">Shopping Cart <?php total_items(); ?> 
						items  , ($<?php total_price(); ?>)</a></li>
					<li><a href="#">Contact Us</a></li>
				</ul>

				<div id="form">
					<form method="get" action="index.php" enctype="multipart/form-data">
						<input type="text" name="query" placeholder ="Search for a Product"/>
						<input type="submit" name="search" value="Search" />
					</form>
				</div>
				}
		</div>

		<!-- Main Content -->

		<div class="content_wrapper">
		
			<div id="sidebar">
				<div id="sidebar_title">Categories</div>

				<ul id="products">
					
					<?php getCategory(); ?>
				</ul>

				<div id="sidebar_title">Brands</div>

				<ul id="products">
					<?php getBrands(); ?>
				</ul>

			</div>
			<div id="content_area">

				<?php cart(); ?>

				<div id="products_format">

					<?php
					getItemDetails();
					?>

			</div>
		</div>

		<!-- Footer -->

		<div id="footer">

			<h2 style="text-align:center; padding-top:30px;">
				CyberWare is not a real store. If you have actually
				bought from this website then you have been scammed.</h2>
		</div>


	</div>

	<!-- End of Main Design -->

</body>
</html>