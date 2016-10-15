import mysql.connector
cnx = mysql.connector.connect(user='jabadehut', password='hftc2016', host='13.92.32.92', database='pcat')

cursor = cnx.cursor()
cnx.autocommit = True

cursor.execute('delete from family_inventory')

CREATE TABLE `family_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `family_id` varchar(255) NOT NULL,
  `inventory_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `requested_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(45) DEFAULT NULL,



user_insert_template = "insert into user (family_id, inventory_id, quantity, requested_date, status) values(\"{0}\", {1}, {2}, \"{3}\", \"{4}\", \"{5}\", {6})"
cursor.execute(user_insert_template.format("Smith1465", "Price", "Bonnie Fernandez", "mbprice@gmail.com", "Home Visitor", "bfernandez@gmail.com", 1))
cursor.execute(user_insert_template.format("Mary", "Bird", "Bonnie Fernandez", "mbird@gmail.com", "Home Visitor", "bfernandez@gmail.com", 0))
cursor.execute(user_insert_template.format("Beth", "Prices", "Cindy Lou Hoo", "bpricess@gmail.com", "Home Visitor", "clouhoo@gmail.com", 1))
cursor.execute(user_insert_template.format("Bonnie", "Fernandez", "Jennifer Caudle", "bfernandez@gmail.com", "Supervisor", "jcaudle@gmail.com", 1))
cursor.execute(user_insert_template.format("Bobby", "Brown", "Jennifer Caudle", "bbrown@gmail.com", "Supervisor", "jcaudle@gmail.com", 0))
cursor.execute(user_insert_template.format("Angie", "Taylor", "", "ataylor@gmail.com", "User Admin", "", 1))










