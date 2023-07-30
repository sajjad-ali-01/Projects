<?php
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbName = "project";
    //create connection
    $con= mysqli_connect($servername,$username,$password,$dbName);

    //check connection
    if(!$con)
    {
        echo "connection failed: ".mysqli_connect_error();
    }
    else
    {
       // echo "connection ok: ";
    }
    ?>
