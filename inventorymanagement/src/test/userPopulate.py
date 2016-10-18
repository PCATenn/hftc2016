import mysql.connector
# cnx = mysql.connector.connect(user='jabadehut', password='hftc2016', host='13.92.32.92', database='pcat')
cnx = mysql.connector.connect(user='root', password='root', host='localhost', database='pcat')
# cnx = mysql.connector.connect(host='localhost', database='pcat')

cursor = cnx.cursor()
cnx.autocommit = True

cursor.execute('delete from user')

user_insert_template = "insert into user (firstname, lastname, supervisor, email, role, supervisor_email, isactive) values(\"{0}\", \"{1}\", \"{2}\", \"{3}\", \"{4}\", \"{5}\", {6})"
cursor.execute(user_insert_template.format("Mary Beth", "Price", "Bonnie Fernandez", "mbprice@gmail.com", "Home Visitor", "bfernandez@gmail.com", 1))
cursor.execute(user_insert_template.format("Mary", "Bird", "Bonnie Fernandez", "mbird@gmail.com", "Home Visitor", "bfernandez@gmail.com", 0))
cursor.execute(user_insert_template.format("Beth", "Prices", "Cindy Lou Hoo", "bpricess@gmail.com", "Home Visitor", "clouhoo@gmail.com", 1))
cursor.execute(user_insert_template.format("Bonnie", "Fernandez", "Jennifer Caudle", "bfernandez@gmail.com", "Supervisor", "jcaudle@gmail.com", 1))
cursor.execute(user_insert_template.format("Bobby", "Brown", "Jennifer Caudle", "bbrown@gmail.com", "Supervisor", "jcaudle@gmail.com", 0))
cursor.execute(user_insert_template.format("Angie", "Taylor", "", "ataylor@gmail.com", "User Admin", "", 1))










