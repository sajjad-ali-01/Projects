<?php
error_reporting(0);
// Connecting to the Database
include 'connect.php';
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
    <link href="style/css/bootstrap.min.css" rel="stylesheet">
    <link href="style/css/k.css" rel="stylesheet">

    <title>Display</title>
</head>

<body>
    <?php include("include/header.php"); ?>
    <div class="container-fluid main-container">
        <div class="row">
            <div class="col-md-3 sidebar">
                <!-- Menu -->
                <div class="side-menu">
                    <nav class="navbar navbar-default" role="navigation">
                        <!-- Main Menu -->
                        <div class="side-menu-container">
                            <ul class="nav navbar-nav">
                                <li>
                                    <button style="width:240px; height:50px" type="button" class="btn btn-default btn-primary" onClick="location.href='index.php'">
                                        <span class="glyphicon glyphicon-home pull-left"></span> Dashboard
                                    </button>
                                </li>
 
                                <li>
                                    <button style="width:240px; height:50px" type="button" class="btn btn-default btn-primary" onClick="location.href='Contact.php'">
                                        <span class="glyphicon glyphicon-edit pull-left"></span> Customer queries
                                    </button>
                                </li>
                                <li>
                                    <button style="width:240px; height:50px" type="button" class="btn btn-default btn-primary" onClick="location.href='display.php'">
                                        <span class="glyphicon glyphicon-edit pull-left"></span> Manage Users
                                    </button>
                                </li>
                                <li>
                                    <button style="width:240px; height:50px" type="button" class="btn btn-default btn-danger" onClick="location.href='logout.php'">
                                        <span class="glyphicon glyphicon-off pull-left"></span> Logout
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <div class="col-md-9 content">
                <!-- table for displaying records-->
                <div class="container my-4">
                    <table class="table table-dark table-hover" id="myTable">
                        <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Message</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            $sql = "SELECT * FROM `contactform`";
                            $result = mysqli_query($conn, $sql);
                            while ($row = mysqli_fetch_assoc($result)) {
                                echo "<tr>
                                <td>" . $row['name'] . "</td>
                                <td>" . $row['email'] . "</td>
                                <td>" . $row['message'] . "</td>
                                <td>  
                                <button class='delete btn btn-sm btn-danger'><a class='text-light' href='delete.php?deleteid=" . $row['sno'] . "'>Delete</a></button> </td>
                                </tr>";
                            }
                            ?>
                        </tbody>
                    </table>
                </div>
                <hr>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#myTable').DataTable();
        });
    </script>
</body>

</html>
