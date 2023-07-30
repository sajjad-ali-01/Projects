<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display</title>
    <link href="style/css/bootstrap.min.css" rel="stylesheet">
    <link href="style/css/k.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <style>
        table {
            background: white;
        }
        .update, .del {
            background-color: blue;
            color: white;
            border: 0;
            outline: none;
            border-radius: 5px;
            height: 25px;
            width: 80px;
            font-weight: bold;
            cursor: pointer;
        }
        .del {
            background-color: red;
        }
        .sidebar {
            float: left;
            width: 240px;
            margin-left: 20px;
        }
        .content {
            margin-left: 280px;
        }
        .heading {
            background-color: #c4e17f;
            padding: 10px;
        }
    </style>
</head>
<body>
    <?php include("include/header.php"); ?>
    <div class="container-fluid main-container">
        <div class="sidebar">
            <div class="row">
                <div class="absolute-wrapper"></div>
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
        </div>
        <div class="content">
            <div class="panel-heading heading">
                <h1>User Details</h1>
            </div>
            <?php
            include 'Connections.php';

            $query = "SELECT * FROM registrations";
            $data = mysqli_query($con, $query);
            $total = mysqli_num_rows($data);
            if ($total > 0) {
                ?>
                <br>
                <table align="center" border="3" cellspacing="7">
                    <tr>
                        <th width="5%">ID</th>
                        <th width="10%">Name</th>
                        <th width="10%">Phone Number</th>
                        <th width="20%">Email</th>
                        <th width="8%">Operations</th>
                    </tr>
                    <?php
                    while ($result = mysqli_fetch_assoc($data)) {
                        echo "<tr>
                                <td>" . $result['ID'] . "</td>
                                <td>" . $result['name'] . "</td>
                                <td>" . $result['phone'] . "</td>
                                <td>" . $result['email'] . "</td>
                                <td>
                                    <a href='edit.php?id=$result[ID]'>
                                        <input type='submit' value='UPDATE' class='update'>
                                    </a>
                                    <a href='delete.php?id=$result[ID]'>
                                        <input type='submit' value='Delete' class='del' onclick='return checkdelete()'>
                                    </a>
                                </td>
                              </tr>";
                    }
                    ?>
                </table>
                <?php
            } else {
                //echo "Table has no records";
            }
            ?>
        </div>
    </div>
</body>

<script>
    function checkdelete() {
        return confirm('Are you sure you want to delete the record?');
    }
</script>
</html>
