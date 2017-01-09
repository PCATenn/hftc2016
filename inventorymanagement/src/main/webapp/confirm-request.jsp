<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Confirm a request | PCAT inventory</title>
</head>
<body>

    <div class="content">
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
                    Forwarded for approval.
                </h2>

                <h3>
                    You’ll receive an email confirming your request.
                </h3>

                <button name="inventory-return" class="button medium-button affirmative" onclick="history.back(1)">
                    Return to inventory list
                </button>
            </div>
        </section>
    </div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
