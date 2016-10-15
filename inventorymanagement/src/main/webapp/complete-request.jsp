<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>PCAT inventory | Complete request</title>
</head>
<body>

<form action="submitForRequestApproval" method="post">
	<input type="hidden" name="userId" id="userId" value="<%=request.getParameter("userId") %>"/>
	<input type="hidden" name="inventoryId" id="inventoryId" value="${inventory.id}"/>
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
            <h1>
                Complete your request
            </h1>

            <div class="inline-directive">
                <h2>
                    Enter the family number:
                </h2>

                <label><input type="text" class="family-number-input" name="familyId" id="familyId"></label>
            </div>

            <div class="item-box">
                <h4>
                    Items youâre requesting:
                </h4>

                <div class="dummy-inventory">
                    <div class="dummy-inventory-label-row">
                        <p style="padding-left: 0;">Qty</p>
                        <p style="padding-left: 1.75rem;">Product #</p>
                        <p style="padding-left: 2.75rem;">Description</p>
                        <p style="padding-left: 14.25rem;">Location</p>
                    </div>

                    <div class="dummy-inventory-row">
                        <div class="dummy-qty dummy-table-row confirm"><input type="text" class="family-number-input" name="quantity" id="quantity"></div>
                        <div class="dummy-prod dummy-table-row">${inventory.productName}</div>
                        <div class="dummy-desc dummy-table-row">${inventory.productDesc}</div>
                        <div class="dummy-loc dummy-table-row">${inventory.location}</div>
                    </div>
                </div>
            </div>

            <h3 style="margin: 4rem 8rem 2rem 8rem;">
                To remove an item from the list, set quantity to 0 (zero) before submitting.
            </h3>

            <div>
                <button name="cancel-request" class="button medium-button neutral">
                    Start over
                </button>
                <button name="submit-request" class="button medium-button affirmative">
                    Submit for approval
                </button>
            </div>

        </div>
    </section>
<!--
    <footer>
        Prevent Child Abuse Tennessee
    </footer>
-->
</form>
</body>
</html>
