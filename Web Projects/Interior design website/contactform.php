<?php
error_reporting(0);
$insert = false;
include 'connect.php';
$nameErr = $emailErr = $subjectErr = "";
$name = $email = $subject = "";

// validation function

function test_input($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
// Creation of new record

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (!isset($_POST['editid'])) {
        if (empty($_POST["name"])) {
            $nameErr = "Name is required";
        } else {
            $name = test_input($_POST["name"]);
        }

        if (empty($_POST["email"])) {
            $emailErr = "Email is required";
        } else {
            $email = test_input($_POST["email"]);
        }

        if (empty($_POST["subject"])) {
            $subjectErr = "Subject is required";
        } else {
            $subject = test_input($_POST["subject"]);
        }

        if ($nameErr or $emailErr or $subjectErr) {
            echo "<div class='alert alert-warning alert-dismissible fade show' role='alert'>
           <strong>Error!</strong><br>" . $nameErr . "<br>" . $emailErr . "<br>" . $subjectErr . "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>
           <span aria-hidden='true'>×</span></button></div>";
        } else {
            $sql = "INSERT INTO `contactform` ( `name`, `email`, `message`) VALUES ( '$name', '$email' , '$subject')";
            $insertion = mysqli_query($conn, $sql);
            if ($insertion) {
                $insert = true;
            } else {
                echo "The record was not inserted successfully because of this error ---> " . mysqli_error($conn);
            }
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

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
    <title>Document</title>
</head>

<body>
    <?php
    if ($insert) {
    echo "<div class='alert alert-success alert-dismissible fade show' role='alert'>
    <strong>Success!</strong> Your note has been inserted successfully
    <button type='button' class='close' data-dismiss='alert' aria-label='Close'>
      <span aria-hidden='true'>×</span>
    </button>
  </div>";
    }
   
    ?>

    <div class="container my-4">
        <center><h2>CONTACT</h2></center>
        <form method='post' action="contactform.php">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" class="form-control" name="name" placeholder="Your name..">
            </div>
            <div class="form-group"><label for="email">Email:</label>
                <input type="email" id="email" class="form-control" name="email" placeholder="Your Email here..">
            </div>
            <div class="form-group">
                <label for="subject">Subject</label>
                <textarea id="subject" name="subject" class="form-control" placeholder="Write something.." style="height:200px"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#myTable').DataTable();
        });
    </script>
</body>

</html>