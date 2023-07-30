<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Sacramento&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700;800;900&display=swap" rel="stylesheet">
    <script src="	https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>

<script src="	https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <title>S&F Interiors</title>
</head>
<body >
      <!-- topbar start -->
      <div class="topbar container-fluid d-flex flex-wrap justify-content-center justify-content-lg-between">
        <div class="info-topbar mt-2 d-flex flex-wrap justify-content-center justify-content-lg-between">
            <div class="info-content d-inline-flex">
                <div class="mt-1"><span class="icon-phone-alt"></span></div>
                <p class="phone ms-2">+92 32466-22087, +92 17422-1680</p>
            </div>

            <div class="info-content ms-4 d-inline-flex">
                <div class="mt-1"><span class="icon-envelope"></span></div>
                <p class="email ms-2">SF.interiors@project.com</p>
            </div>
        </div>

        <div class="location-topbar ms-3 mt-2 d-flex align-items-center">
            <p>Adda plot,Raiwind road Lahore, Pakistan</p>
        </div>
    </div>
    <!-- topbar end -->
      <!---NAV BAR-->
      <nav class="navbar navbar-expand-lg navbar-light mx-md-auto me-md-auto flex flex-wrap items-center text-bas">
        <div class="nav-content container-fluid">
        <a href="index.html" class="navbar-brand">SF.<span>Interiors</span></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mynavbar">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="mynavbar">
              <ul class="navbar-nav mx-md-auto">
                  <li class="nav-item mx-4"><a href="index.php" class="nav-link active">Home</a></li>
                  <li class="nav-item mx-4"><a href="Projects.html" class="nav-link">Projects</a></li>
                  <li class="nav-item mx-4"><a href="services.html" class="nav-link">Services</a></li>
                  <li class="nav-item mx-4"><a href="aboutUs.html" class="nav-link">About Us</a></li>   
              </ul>
              <ul class="navbar-nav mx-md-5">
              <li class="nav-item mx-3"><button class="btn btn-primary"><a href="contactform.php" style="color:white">Contact Us</a></button></li>
                <?php
                  if (isset($_SESSION['logged_in']) && $_SESSION['logged_in'] === true) {
                 echo '<li class="nav-item"></li><button class="Login btn-primary"><a href="logout.php">Logout</a></button></li>';
                  } else {
                      echo '<li class="nav-item"></li><button class="Login btn-primary"><a href="LOGIN.php">Sign In</a></button></li>';
                  }
                ?>

              </ul>
          </div>
          </div>
      </nav>
  </div>
</div>
</div>
<!--navbar End-->
    
    <!---Slider-->

            <div class="col-lg-12" id="right">
              <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                        aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                        aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                        aria-label="Slide 3"></button>
                </div>
        
                <div class="carousel-inner">
                    <div class="carousel-item carousel-item1 active">
                        <div class="carousel-caption d-block">
                            <h5 class="animate__animated animate__fadeInDown"
                                style="animation-duration: 1.7s; animation-delay: 0.6s">We create beautiful home interior design
                            </h5>
                            <p class="animate__animated animate__fadeInDown"
                                style="animation-duration: 1.7s; animation-delay: 1.7s">
                                <a href="about.html " style="font-size=60px">Learn More</a>
                            </p>
                        </div>
                    </div>
        
                    <div class="carousel-item carousel-item2">
                        <div class="carousel-caption d-block">
                            <h5 class="animate__animated animate__fadeInDown"
                                style="animation-duration: 1.7s; animation-delay: 0.6s">Luxury Interior design for your dream
                                living</h5>
                            <p class="animate__animated animate__fadeInDown"
                                style="animation-duration: 1.7s; animation-delay: 1.7s">
                                <a href="#" style="font-size=60px">Learn More</a>
                            </p>
                        </div>
                    </div>
        
                    <div class="carousel-item carousel-item3">
                        <div class="carousel-caption d-block">
                            <h5 class="animate__animated animate__fadeInDown"
                                style="animation-duration: 1.7s; animation-delay: 0.6s">Giving your home a modern and unique
                                style</h5>
                            <p class="animate__animated animate__fadeInDown"
                                style="animation-duration: 1.7s; animation-delay: 1.7s">
                                <a href="#" style="font-size=60px">Learn More</a>
                            </p>
                        </div>
                    </div>
                </div>
        
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                    data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
        
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                    data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            <!--Slider-->
    </div>
    </div>
    </div> 
    
    <!---Text Rotate-->
    <div class="container-fluid">
      <div class="row">
          <div class="col-lg-12" style="text-align: center; padding-top: 200px; padding-bottom: 200px;">
            <link href="https://fonts.googleapis.com/css?family=Raleway:200,100,400" rel="stylesheet" />
            <h1>Our <span style="color: maroon;">Designs</span> Are
              <span
                class="txt-rotate"
                data-period="1500"
                data-rotate='[ "Stylish.", "Classy.", "Trendy.", "The best.", "Custom made for you." ]'></span>
            </h1>
        </div>
      </div>
    </div>
    <!---Our Choices---->
    <section class="Choice">

        <h1>Your wide range of Choices</h1>
  
        <div class="container">
  
          <div class="row">
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="ConferenceRome">
  
              <h3>Conference Room</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="livingRoom">
  
              <h3>Living Room</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="BedRoom">
  
              <h3>Bed Room</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="Kitchen">
  
              <h3>Kitchen</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="DrawingRoom">
  
              <h3>Drawing Room</h3>
  
            </div>
            
            <div class="col-md-4 col-lg-3 col-sm-6" id="BathRoom">
  
              <h3>Bath Room</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="office">
  
              <h3>Office</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="Restaurant">
  
              <h3>Restaurant</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="Cafe">
  
              <h3>Cafe</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="Clinic">
  
              <h3>Clinic</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="beautySalon">
  
              <h3>Beauty Salon</h3>
  
            </div>
  
            <div class="col-md-4 col-lg-3 col-sm-6" id="Shop">
  
              <h3>Shop</h3>
  
            </div>
  
          </div>
  
        </div>
      </section>
      <!---Our Working Process-->
      <section class="working-plan">
            <div class="sec-title text-center" >
              <h2 style="color: #fff;">Our Working Process</h2>
            </div>
            <div class="container">
              <p>We've distilled our interior design process into 4 Steps – the same steps we have been using for more than 41 years, In this steps, the designer visits your home to gather more.</p>
              <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="single-working-item">
                    <div class="icon-box">
                      <div class="icon">
                        <img style="border-radius:50%;" src="data:image/jpeg;base64,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" width="100%" height="100%" alt="">
                      </div>
                      <div class="count">
                        <h3>1</h3>
                      </div>
                    </div>
                    <div class="text-box text-center">
                      <h3>Concept</h3>
                      <p>Our designers will meet the customer to gather more Information.</p>
                    </div>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="single-working-item">
                    <div class="icon-box">
                      <div class="icon">
                        <img style="border-radius:50%;" src="data:image/jpeg;base64,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" width="100%" height="100%" alt="">
                      </div>
                      <div class="count">
                        <h3>2</h3>
                      </div>
                    </div>
                    <div class="text-box text-center">
                      <h3>Plan</h3>
                      <p>Then we plan the design accordingly with customer's opinions.</p>
                    </div>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="single-working-item">
                    <div class="icon-box">
                      <div class="icon">
                        <img style="border-radius:50%;" src="data:image/png;base64,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" width="100%" height="100%" alt="">
                      </div>
                      <div class="count">
                        <h3>3</h3>
                      </div>
                    </div>
                    <div class="text-box text-center">
                      <h3>Build</h3>
                      <p>After the complete plan is done, we build the design with experts.</p>
                    </div>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="single-working-item">
                    <div class="icon-box">
                      <div class="icon">
                        <img style="border-radius:50%;" src="data:image/jpeg;base64,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" width="100%" height="100%" alt="">
                      </div>
                      <div class="count">
                        <h3>4</h3>
                      </div>
                    </div>
                    <div class="text-box text-center">
                      <h3>Launch</h3>
                      <p>And hence the customer is always satisfied with our work.</p>
                    </div>
                  </div>
                </div>
              </div>
    
            </div>
          </div>
        </div>
      </section>
      <!----Footer-->
      <div class="container-fluid bg-dark">
        <div class="top-footer">
        <div class="row">
          <div class="col-lg-4 col-md-4 col-sm-6 col-12 mx-auto">
            <div class="widget widget-contact text-right" >
              <h3 class="widget-title"><a href="ContactUS.html" class="text-warning">Contact US</a></h3>
              <ul class="ft-links">
                <li ><a href=""class="text-light"> SF Interiors Lahore, Pakistan</a></li>
                <li ><a href="#" class="text-light">+92-3246622087</a></li>
                <li ><a href="#" title="" class="text-light">Sajjadshazii116@gmail.com</a></li>
              </ul>
            </div>
          </div>
          <div class="col-lg-4 col-md-4 col-sm-6 col-12 mx-auto">
            <div class="widget widget-comapany text-right">
              <h3 class="widget-title"><a href="#" class="text-warning">Nvigate To</a></h3>
              <ul class="ft-links">
                <li><a href="index.html" class="text-light">Home</a></li>
                <li ><a href="Projects.html" class="text-light">Projects</a></li>
                <li ><a href="Services.html" class="text-light">Services</a></li>
                <li ><a href="Login.html" class="text-light">Login</a></li>
                <li ><a href="Contact.html" class="text-light">Contact US</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
        
        <div class="bottom-footer">
          <div class="row">
            <div class="col-lg-4 col-md-4 col-sm-6 col-12">
              <h7 class="text-light">©All rights reserved By <a href="index.html">SF.Interrior</a></h7><br>
              <span class="text-align">design by Sajjad & Faseeh</span>
            </div>
      </div>
        </div>
      </div>

      <script>
        var TxtRotate = function(el, toRotate, period) {
        this.toRotate = toRotate;
        this.el = el;
        this.loopNum = 0;
        this.period = 1500;
        this.txt = '';
        this.tick();
        this.isDeleting = false;
      };
      
      TxtRotate.prototype.tick = function() {
        var i = this.loopNum % this.toRotate.length;
        var fullTxt = this.toRotate[i];
      
        if (this.isDeleting) {
          this.txt = fullTxt.substring(0, this.txt.length - 1);
        } else {
          this.txt = fullTxt.substring(0, this.txt.length + 1);
        }
      
        this.el.innerHTML = '<span class="wrap">'+this.txt+'</span>';
      
        var that = this;
        var delta = 300 - Math.random() * 100;
      
        if (this.isDeleting) { delta /= 2; }
      
        if (!this.isDeleting && this.txt === fullTxt) {
          delta = this.period;
          this.isDeleting = true;
        } else if (this.isDeleting && this.txt === '') {
          this.isDeleting = false;
          this.loopNum++;
          delta = 500;
        }
      
        setTimeout(function() {
          that.tick();
        }, delta);
      };
      
      window.onload = function() {                                     //main func
        var elements = document.getElementsByClassName('txt-rotate');
        for (var i=0; i<elements.length; i++) {
          var toRotate = elements[i].getAttribute('data-rotate');
          var period = elements[i].getAttribute('data-period');
          if (toRotate) {
            new TxtRotate(elements[i], JSON.parse(toRotate), period);
          }
        }
        
        var css = document.createElement("style");
        css.type = "text/css";
        css.innerHTML = ".txt-rotate{ border-right: 0.08em solid #666 }";
        document.body.appendChild(css);
      };
      </script>
</body>
</html>