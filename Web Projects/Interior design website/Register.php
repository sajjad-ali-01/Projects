<?php
include 'Connection.php';

if(isset($_POST['submit'])){
	$name = mysqli_real_escape_string($con, $_POST['name']);
	$phone = mysqli_real_escape_string($con, $_POST['phone']);
	$email = mysqli_real_escape_string($con, $_POST['email']);
	$password = mysqli_real_escape_string($con, $_POST['password']);
	$cpassword = mysqli_real_escape_string($con, $_POST['Cpassword']);

	
	$emailquery = "SELECT * FROM registrations WHERE email = '$email'";
	$query = mysqli_query($con, $emailquery);

	$emailcount = mysqli_num_rows($query);
	if($emailcount > 0){
		echo "<script>alert('Email already exist: $name'); window.location.href = 'Register.php';</script>";
		exit;
	}
	else{
		if($password === $cpassword){
			$insertquery = "INSERT INTO registrations (name, phone, email, password, Cpassword)
							VALUES ('$name', '$phone', '$email', '$password', '$cpassword')";
			$iquery = mysqli_query($con, $insertquery);

			if($iquery){
				echo "<script>alert('Registration successful: $name'); window.location.href = 'index.php';</script>";
				exit;
			}
			else{
				echo "<script>alert('Registration failed: $name'); window.location.href = 'Register.php';</script>";
				exit;
			}
		}
		else{
			echo "<script>alert('passwords not matching: $name'); window.location.href = 'Register.php';</script>";

			exit;
		}
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
    <link rel="stylesheet" href="ws.css">
</head>

<body>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form>
			<div class="social-container">
				<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
				<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
				<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
			</div>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="" method="POST">
			<h1>Create Account</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
				<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
				<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
			</div>
			<span>or use your account</span>
			<input type="text" placeholder="Name" name="name" required>
			<input type="text" placeholder="Phone No" name="phone" required>
			<input type="email" placeholder="Email" name="email" required>
			<input type="password" placeholder="Password" name="password" required>
			<input type="password" placeholder="Confirm Password" name="Cpassword" required>
			<button type="submit" name="submit" class="submit">SIGN UP</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signUp"><a href="LOGIN.php" style="color:azure">Sign in</a></button>
			</div>
		</div>
	</div>
</body>
</html>
