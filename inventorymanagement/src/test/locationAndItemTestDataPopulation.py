import mysql.connector
#cnx = mysql.connector.connect(user='jabadehut', password='hftc2016', host='13.92.32.92', database='pcat')
cnx = mysql.connector.connect(user='root', password='root', host='localhost', database='pcat')

cursor = cnx.cursor()
cnx.autocommit = True

# cursor.execute('delete from inventory')

inventory_insert_template = "insert into inventory (product_desc, product_name, location, total_inventory, reserved_inventory) values(\"{0}\", \"{1}\", \"{2}\", {3}, 0)"
product_desc = "High Chair"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Disinfectant"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 1))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 0))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Sleep Sack"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 120))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 10))
product_desc = "Floor Gym"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 7))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Premie Car Seat"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Infant Car Seat"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 1000002))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Convertible Car Seat"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Booster Seat"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Pack & Play"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Box Fan"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))
product_desc = "Breast Pump"
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Chattanooga", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Oak Ridge", 12))
cursor.execute(inventory_insert_template.format(product_desc, product_desc, "Nashville", 12))










