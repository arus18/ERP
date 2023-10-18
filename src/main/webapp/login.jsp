<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession sess = request.getSession(false);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V16</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
</head>
<body>
<%
    if (sess.getAttribute("name") != null) {
        response.sendRedirect("home.jsp");
    }
%>
<div class="limiter">
    <div class="container-login100"
         style="background-image: url('images/bg-01.jpg');">
        <div class="wrap-login100 p-t-30 p-b-50">
            <span class="login100-form-title p-b-41"> Employee Login </span>
            <form method="POST" action="Login"
                  class="login100-form validate-form p-b-33 p-t-5">

                <div class="wrap-input100 validate-input" data-validate="Enter id">
                    <input class="input100" type="number" name="empID"
                           placeholder="User id"> <span class="focus-input100"
                                                        data-placeholder="&#xe82a;"></span>
                </div>

                <div class="wrap-input100 validate-input"
                     data-validate="Enter password">
                    <input class="input100" type="password" name="password"
                           placeholder="Password"> <span class="focus-input100"
                                                         data-placeholder="&#xe80f;"></span>
                </div>

                <div class="container-login100-form-btn m-t-32">
                    <button type="submit" class="login100-form-btn">Login</button>
                </div>
                <div id="g_id_onload"
                     data-client_id="534119121437-m1vrr26elv8lpvk3tijmqugn5ekt7q0m.apps.googleusercontent.com"
                     data-callback="handleCredentialResponse">
                </div>
                <div class="g_id_signin" data-type="standard"></div>
            </form>
        </div>
    </div>
</div>
<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

<script src="https://accounts.google.com/gsi/client" async defer></script>

<script>
    function handleCredentialResponse(response) {
        if (response.credential) {
            // User is signed in
            var credential = response.credential;
            var user = credential.profile;

            console.log('ID: ' + user.id);
            console.log('Name: ' + user.name);
            console.log('Email: ' + user.email);

            // You can access more user details by inspecting the 'user' object
            console.log('User Details:', user);
        } else {
            // User is not signed in or canceled the sign-in
            console.log('User is not signed in.');
        }
    }
</script>
</body>
</html>
