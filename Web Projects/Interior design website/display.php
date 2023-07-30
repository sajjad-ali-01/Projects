<?php
include 'Connection.php';

$query = "SELECT * FROM registrations";
$data = mysqli_query($con, $query);
$total = mysqli_num_rows($data);

if ($total > 0) {
    ?>
    <table border="3">
        <tr>
        <th>Name</th>
        <th>Phone Number</th>
        <th>Email</th>
        </tr>
    <?php
    while ($result = mysqli_fetch_assoc($data)) {
        echo "<tr>
                <td>" . $result['name'] . "</td>
                <td>" . $result['phone'] . "</td>
                <td>" . $result['email'] . "</td>
              </tr>";
    }
    ?>
    </table>
    <?php
} else {
    //echo "Table has no records";
}
