<?php

$con = mysqli_connect("csmysql.cs.cf.ac.uk","c1560749","Z2pCeHRPdM","c1560749");

if (mysqli_connect_errno())
	{
		echo "Connection Failed: " . mysqli_connect_error();
	}

	function getIp() {
		$ip = $_SERVER['REMOTE_ADDR'];

		if (!empty($_SERVER['HTTP_CLIENT-IP'])) {
			$ip = $_SERVER['HTTP_CLIENT_IP'];
		} elseif (!empty($_SERVER['HTTP_X_FORWARDED_FOR'])) {
			$ip = $_SERVER['HTTP_X_FORWARDED_FOR'];
		}

		return $ip;
	}
//get categories

function getCategory(){
	global $con;

	$get_category = "select * from categories";

	$run_category = mysqli_query($con, $get_category);

	while ($row_category = mysqli_fetch_array($run_category)) {

		$category_id = $row_category['category_id'];
		$category_name = $row_category['category_name'];

	echo "<li><a href='index.php?category=$category_id'>$category_name</a></li>";
	}

}

//get brands

function getBrands(){
	global $con;

	$get_brands = "select * from brands";

	$run_brands = mysqli_query($con, $get_brands);

	while ($row_brands = mysqli_fetch_array($run_brands)) {

		$brand_id = $row_brands['brand_id'];
		$brand_name = $row_brands['brand_name'];

	echo "<li><a href='index.php?brand=$brand_id'>$brand_name</a></li>";
	}
}

function getItem(){
	global $con;

	if(isset($_GET['category'])){
		$category_id =$_GET['category'];
		$get_Item = "select * from items where item_category= '$category_id' LIMIT 0,8";
	}
	else if(isset($_GET['brand'])){
		$brand_id =$_GET['brand'];
		$get_Item = "select * from items where item_brand= '$brand_id' LIMIT 0,8";
	}
	else if(isset($_GET['search'])){
		$query = $_GET['query'];
		$get_Item = "select * from items where item_keywords like '%$query%' LIMIT 0,8";
	}
	else {
		$get_Item = "select * from items order by RAND() LIMIT 0,8";
	}

	$run_Item = mysqli_query($con, $get_Item);

	while($row_item=mysqli_fetch_array($run_Item)) {

		$item_id = $row_item['item_id'];
		$item_category = $row_item['item_category'];
		$item_brand = $row_item['item_brand'];
		$item_name = $row_item['item_name'];
		$item_description = $row_item['item_description'];
		$item_price = $row_item['item_price'];
		$item_image = $row_item['item_image'];
		$item_keywords = $row_item['item_keywords'];

		echo " 
			<div id='one_product'>

				<a href='full_description.php'><h3>$item_name</h3></a>

				<a href='full_description.php?item_id=$item_id'><img src='images/$item_image' width ='300px', height='250px'/ ></a>

				<p>$ <b>$item_price</b></p>

				<p margin-bottom:'25px', font-size:'12px'>Add to Cart:</p> <a href='index.php?add_to_cart=$item_id'><img src='images/cart.png', width='50px'></a>

			</div>";
	}
}

function getItemDetails(){
	global $con;

	if(isset($_GET['item_id'])) {

		$item_id =$_GET['item_id'];

		$get_Item = "select * from items where item_id='$item_id'";

		$run_Item = mysqli_query($con, $get_Item);

	while($row_item=mysqli_fetch_array($run_Item)) {

		$item_id = $row_item['item_id'];
		$item_name = $row_item['item_name'];
		$item_description = $row_item['item_description'];
		$item_price = $row_item['item_price'];
		$item_image = $row_item['item_image'];

		echo " 
			<div id='one_product'>

				<h3>$item_name</h3>

				<img src='images/$item_image' width ='500px'/ >
				<p>$ <b>$item_price</b></p>
				<p font-size:'20px'>$item_description<p>

				<p margin-bottom:'25px', font-size:'12px', float:'right'>Add to Cart:</p> <a href='index.php?add_to_cart=$item_id'><img src='images/cart.png', width='50px', float:'right'></a>

			</div>";
	}
}
}

function cart(){

	if(isset($_GET['add_to_cart']))
	{
		global $con;

		$ip = getIp();

		$item_id = $_GET['add_to_cart'];

		$check_item = "select * from cart where ip_addr='$ip' AND itm_id='$item_id'";

		$run_check = mysqli_query($con, $check_item);

		if(mysqli_num_rows($run_check) > 0) {

			echo "";
		}

		else {

			$insert_item = "insert into cart (itm_id, ip_addr) values ('$item_id','$ip')";

			$run_item = mysqli_query($con, $insert_item);

			echo "<script>window.open('', '_self')</script>";
		}
	}

}

function total_items() {
	global $con;

	if(isset($_GET['add_to_cart'])){

		$ip = getIp();

		$get_items = "select * from cart where ip_addr='$ip'";

		$run_items = mysqli_query($con, $get_items);

		$count_items = mysqli_num_rows($run_items);
	}

		else {

		$ip = getIp();

		$get_items = "select * from cart where ip_addr='$ip'";

		$run_items = mysqli_query($con, $get_items);

		$count_items = mysqli_num_rows($run_items);
		}

		echo $count_items;
	}

function total_price() {

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

			$prices = array_sum($item_price);

			$total += $prices;

		}
	}

	echo $total;
}
?>