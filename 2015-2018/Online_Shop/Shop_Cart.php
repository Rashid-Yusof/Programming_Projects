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
					<li><a href="Shop_Cart.php">Shopping Cart: <?php total_items(); ?> 
						items , ($<?php total_price(); ?>)</a></li>
					<li><a href="#">Contact Us</a></li>
				</ul>

				<div id="form">
					<form method="get" action="index.php" enctype="multipart/form-data">
						<input type="text" name="query" placeholder ="Search for a Product"/>
						<input type="submit" name="search" value="Search" />
					</form>
				</div>
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
					<br>
					<form action="" method="post" enctype="multipart/form-data">

						<table align="center" width="800px">
							<tr align="center">
								<td><h2>Your Cart</h2></td>
							</tr>

							<tr align="center">
								<th>Remove</th>
								<th>Product(s)</th>
								<th>Quantity</th>
								<th>Total Price</th>
							</tr>

	<?php
	$total = 0;

	global $con;

	$ip = getIp();

	$get_price = "select * from cart where ip_addr='$ip'";

	$run_price = mysqli_query($con, $get_price);

	while($itm_price=mysqli_fetch_array($run_price)) {

		$item_id = $itm_price['itm_id'];

		$item_price = "select * from items where item_id='$item_id'";

		$run_item_price = mysqli_query($con, $item_price);

		while ($itm2_price = mysqli_fetch_array($run_item_price)) {
			$item_price = array($itm2_price['item_price']);

			$item_name = $itm2_price['item_name'];

			$item_image = $itm2_price['item_image'];

			$single_price = $itm2_price['item_price'];

			$prices = array_sum($item_price);

			$total += $prices;?>

							<tr align="center">
								<td><input type="checkbox" name="remove[]" value="<?php echo $item_id;?>"/></td>
								<td><?php echo $item_name; ?><br>
								<img src="images/<?php echo $item_image;?>" width='65px' height='60px'/>
								</td>
								<td><input type="text" size="3" name="quantity"/></td>
								<td><?php echo "$" . $single_price?></td>
							</tr>

		<?php } } ?>

							<tr align='right'>
								<td colspan="2"><b>Final Total:</b></td>
								<td><?php echo '$' . $total; ?></td>
							</tr>

							<tr align="center">
								<td><input type="submit" name="update_cart" value="Update Cart"></td>
								<td><button><a href="checkout.php">Checkout</a></button></td>
							</tr>
						</table>
						
					</form>

		<?php
			global $con;

			$ip = getIp();

			if(isset($_POST['update_cart'])){

				foreach($_POST['remove'] as $remove_id){

					$delete_product = "delete from cart where itm_id='$remove_id' AND ip_addr='$ip'";

					$run_delete = mysqli_query($con, $delete_product);

					if($run_delete) {

						echo "<script>window.open('Shop_Cart.php','_self')</script>";
					}


				}
			}
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