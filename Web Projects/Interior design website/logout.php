<?php
session_start();
session_destroy();
header("Location: index.php"); // Redirect to the desired page after logout
exit();
?>
