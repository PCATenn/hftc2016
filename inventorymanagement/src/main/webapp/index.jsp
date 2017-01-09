<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>PCAT inventory</title>
</head>
<body>

    <div class="content">
        <nav>
            <a href="http://www.pcat.org/">
                <img src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
            </a>

            <ul>

            </ul>
        </nav>

        <section>
            <div class="section-body">
                <h1>
                    Log in
                </h1>

                <div class="login-card">
                    <form class="login-form" action="loginPage">
                        <input type="email" class="login-input" value="" spellcheck="false"
                            name="email" id="email" placeholder="Enter your email address"
                            autofocus>
                        <button name="submit" class="button big-button affirmative">
                            Submit</button>
                    </form>

                </div>

            </div>
        </section>
    </div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
