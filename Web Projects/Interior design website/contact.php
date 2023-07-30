<!DOCTYPE html>
<head>
<link rel="stylesheet" href="css/css/default.css">
<link rel="stylesheet" href="css/css/fonts.css">
<link rel="stylesheet" href="css/css/layout.css">
<link rel="stylesheet" href="css/css/magnific-popup.css">
<link rel="stylesheet" href="css/css/media-queries.css.css">
</head>
<section id="contact_us">
      <div class="d-flex">
         <form action="" id="" style="width:100%" method="POST">
         <div class="col-lg-12">
            <h4 class="text-center text-light">Contact Us</h4>
               <div class="d-flex">
                  <div class="col-lg-6">
                     <div class="form-group">
                        <label for="full_name" class="control-label text-light">Full Name</label>
                        <input type="text" class="form-control" id="full_name" name="full_name" required value="<?php echo isset($_POST['full_name']) ? $_POST['full_name'] : "" ?>">
                     </div>
                     <div class="form-group">
                        <label for="text" class="control-label text-light">Email</label>
                        <input type="text" class="form-control" id="name" name="text" required value="<?php echo isset($_POST['email']) ? $_POST['email'] : "" ?>">
                     </div>
                     <div class="form-group">
                        <label for="contact_no" class="control-label text-light">Contact</label>
                        <input type="text" class="form-control" id="contact_no" name="contact" required value="<?php echo isset($_POST['contact']) ? $_POST['contact'] : "" ?>">
                     </div>
                     <div class="form-group">
                        <label for="subject" class="control-label text-light">Subject</label>
                        <input type="text" class="form-control" id="subject" name="subject" required value="<?php echo isset($_POST['subject']) ? $_POST['subject'] : "" ?>">
                     </div>
                  </div>
                  <div class="col-lg-6">
                     <div class="form-group">
                        <label for="message" class="control-label text-light">Message</label>
                        <textarea name="message" id="message" cols="30" rows="16" class="form-control" required><?php echo isset($_POST['message']) ? $_POST['message'] : "" ?></textarea>
                     </div>
                  </div>
               </div>
               <div class='text-center'>
                  <?php if(isset($msg_status) && $msg_status =='success'): ?>
                     <span class="text-success">Message Successfully Sent.</span>
                  <?php elseif(isset($msg_status) && $msg_status =='failed'): ?>
                     <span class="text-success">Message Sending Failed.</span>
                     <?php endif; ?>
               </div>
               <center>
                  <button class="btn btn-primary">Send Message</button>
               </center>
               <div>
                  <h2 class="text-light">Contact Details</h2>
                  <p class="address">
                     <span><?php echo $contact['address'] ?></span><br>
                     <span><?php echo $contact['mobile'] ?></span><br>
                     <span><?php echo $contact['email'] ?></span>
                  </p>
               </div>
            </div>
         </form>
      </div>
   </section>