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
        <header>
            <a href="http://www.pcat.org/">
                <img src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
            </a>

            <button name="logout" class="button logout-button neutral">
                Log out
            </button>
        </header>

        <section>
            <nav>
                <ul>
                    <li class="underlined"><a href="request.jsp">request an item</a></li>
                    <li class="underlined"><a href="review-approvals.jsp">review approvals</a></li>
                    <li class="underlined"><a href="listAllInventories.jsp">manage items</a></li>
                    <li class="underlined"><a href="listAllUsers.jsp">manage users</a></li>
                </ul>
            </nav>

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
