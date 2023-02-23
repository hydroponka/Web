<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Login Form on HTML5</title>



    <link rel="stylesheet" href="css/style.css">


</head>

<body>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>Paper Stack</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div class="container">
    <section id="content">
        <form action="controller">
            <input type="hidden" name="command" value="login"/>
            <h1>Login Form</h1>
            <div>
                <input type="text" name="login" placeholder="Username" required="" id="username" />
            </div>
            <div>
                <input type="password" name="password" placeholder="Password" required="" id="password" />
                <br/>
                ${login_msg.toUpperCase()}
                <br/>
            </div>
            <div>
                <input type="submit" name="sub" value="Log in" />
                <a href="register.jsp">Register</a>
            </div>
        </form><!-- form -->

    </section><!-- content -->
</div><!-- container -->
</body>
</html>


</body>
</html>
