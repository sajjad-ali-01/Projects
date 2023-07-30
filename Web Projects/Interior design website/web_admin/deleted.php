<?php
// Connecting to the Database
include 'connect.php';
$delete=false;

if(isset($_GET['deleteid'])){
    $sno = $_GET['deleteid'];
    $sql = "DELETE FROM `contactform` WHERE `sno` = $sno";
    $result = mysqli_query($conn, $sql);
   

// $sql = "DELETE FROM `contactform` WHERE `name` = 'harryjam' LIMIT 1";
// $result = mysqli_query($conn, $sql);
// $aff = mysqli_affected_rows($conn);
// echo "<br>Number of affected rows: $aff <br>";
if($result){
    global $delete ;
    $delete=true;
    header('location:contactform.php');
}
else{
    $err = mysqli_error($conn);
    echo "Not Delete successfully due to this error --> $err";
}
}