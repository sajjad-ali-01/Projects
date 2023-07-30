<?php
include 'Connections.php';
$id = $_GET['id'];
$query = "DELETE FROM registrations WHERE id = '$id'";
$data = mysqli_query($con, $query);

if ($data) {
    echo "<script>alert('Record deleted successful: $name'); window.location.href = 'display.php';</script>";
		exit;
} else {
    echo "<script>alert('Failed to delete record: $name'); window.location.href = 'display.php';</script>";
	exit;
}
?>
