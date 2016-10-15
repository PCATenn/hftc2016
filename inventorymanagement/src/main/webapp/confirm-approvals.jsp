<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>PCAT inventory | Request approved</title>
</head>
<body>

    <nav>
        <a href="http://www.pcat.org/">
            <img src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
        </a>
        <button name="logout" class="button logout-button neutral">
            Log out
        </button>
    </nav>

    <section>
        <div class="section-body">
            <h2>
                Request approved.
            </h2>
            <h3>
                You’ll receive an email confirming your approval of the request.
            </h3>

            <button name="approvals-return" class="button medium-button affirmative" onclick="history.back(1)">
                Return to pending approvals list
            </button>
        </div>
    </section>
<!--
    <footer>
        Prevent Child Abuse Tennessee
    </footer>
-->
</body>
</html>
