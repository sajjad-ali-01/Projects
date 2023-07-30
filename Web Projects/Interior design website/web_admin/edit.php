<?php
include 'Connections.php';

$id = $_GET['id'];

$query = "SELECT * FROM registrations WHERE id = '$id'";
$data = mysqli_query($con, $query);
$total = mysqli_num_rows($data);
$result = mysqli_fetch_assoc($data);

if (isset($_POST['submit'])) {
	$name = mysqli_real_escape_string($con, $_POST['name']);
	$phone = mysqli_real_escape_string($con, $_POST['phone']);
	$email = mysqli_real_escape_string($con, $_POST['email']);

	$updateQuery = "UPDATE registrations SET name = '$name', phone = '$phone', email = '$email' WHERE id = '$id'";
	$result = mysqli_query($con, $updateQuery);

	if ($result) {
		echo "<script>alert('Update successful: $name'); window.location.href = 'display.php';</script>";
		exit;
	} else {
		echo "<script>alert('Updation unsuccessful: $name'); window.location.href = 'display.php';</script>";
		exit;
	}
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Register</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
		integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
		crossorigin="anonymous" />
	<link rel="stylesheet" href="ws.css">
</head>

<body>
	<div class="container" id="container">
		<div class="form-container sign-up-container">
			<form>
				<div class="social-container">

				</div>
			</form>
		</div>
		<div class="form-container sign-in-container">
			<form action="" method="POST">
				<h1>Update Record</h1>
				<div class="social-container">

				</div>
				<input type="text" placeholder="Name" name="name" value="<?php echo $result['name']; ?>"
					required>
				<input type="text" placeholder="Phone No" name="phone" value="<?php echo $result['phone']; ?>"
					required>
				<input type="email" placeholder="Email" name="email" value="<?php echo $result['email']; ?>"
					required>
				<button type="submit" name="submit" class="submit">Update</button>
			</form>
		</div>
	</div>
</body>

</html>
