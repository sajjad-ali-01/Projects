<?php
session_start();
?>
<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
include 'Connection.php';

if (isset($_POST['submit'])) {
    $email = $_POST['email'];
    $password = $_POST['password'];

    $email_search = "SELECT * FROM registrations WHERE email='$email'";

    $query = mysqli_query($con, $email_search);

    $email_count = mysqli_num_rows($query);

    if ($email_count) {
        $email_pass = mysqli_fetch_assoc($query);
        $user_pass = $email_pass['password'];
		$_SESSION['name'] = $email_pass['name'];

		if ($password == $user_pass) { // Compare input password with stored password
			echo "Login successful";
			$_SESSION['logged_in'] = true;
			header("Location: index.php");
			exit;
		} else {
			echo "Incorrect password";
		}
		
    } else {
        echo "Invalid Email";
    }
}
?>





<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LOGIN</title>
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
			<h1>SIGN IN</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
				<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
				<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
			</div>
			<span>or use your account</span>
			<input type="email" placeholder="Email" name="email" required>
			<input type="password" placeholder="Password" name="password" required>
			<button type="submit" name="submit" class="submit">LOGIN NOW</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">SIGN UP</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signUp"><a href="Register.php" style="color:azure">SIGN UP</a></button>
			</div>
		</div>
	</div>
</body>
</html>
