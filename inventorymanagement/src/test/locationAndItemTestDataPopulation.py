import mysql.connector
cnx = mysql.connector.connect(user='jabadehut', password='hftc2016', host='13.92.32.92', database='pcat')

cursor = cnx.cursor()
cnx.autocommit = True

cursor.execute('delete from inventory')
cursor.execute('delete from product_type')
cursor.execute('delete from location')

cursor.execute('insert into location (location_name) values("Nashville")')
nashville_location_id = cursor.lastrowid
cursor.execute('insert into location (location_name) values("Oak Ridge")')
oakridge_location_id = cursor.lastrowid
cursor.execute('insert into location (location_name) values("Chattanooga")')
chatanooga_location_id = cursor.lastrowid

cursor.execute('insert into product_type (product_name, product_desc) values("High Chair", "High Chair")')
high_chair_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Premie Car Seat", "Premie Car Seat")')
premie_car_seat_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Infant Car Seat", "Infant Car Seat")')
infant_car_seat_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Convertible Car Seat", "Convertible Car Seat")')
convertable_car_seat_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Booster Seat", "Booster Seat")')
booster_seat_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Pack & Play", "Pack & Play")')
pack_and_play_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Breast Pump", "Breast Pump")')
breast_pump_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Box Fan", "Box Fan")')
box_fan_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Floor Gym", "Floor Gym")')
floor_gym_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Sleep Sack", "Sleep Sack")')
sleep_sack_id = cursor.lastrowid
cursor.execute('insert into product_type (product_name, product_desc) values("Disinfectant", "Disinfectant")')
disinfectant_id = cursor.lastrowid


inventory_insert_template = "insert into inventory (product_type_id, location_id, total_inventory, reserved_inventory) values({0}, {1}, {2}, 0)"
cursor.execute(inventory_insert_template.format(high_chair_id, nashville_location_id, 12))
cursor.execute(inventory_insert_template.format(high_chair_id, oakridge_location_id, 17))
cursor.execute(inventory_insert_template.format(high_chair_id, chatanooga_location_id, 1))
cursor.execute(inventory_insert_template.format(premie_car_seat_id, chatanooga_location_id, 1))
cursor.execute(inventory_insert_template.format(premie_car_seat_id, nashville_location_id, 4))
cursor.execute(inventory_insert_template.format(premie_car_seat_id, oakridge_location_id, 8))
cursor.execute(inventory_insert_template.format(infant_car_seat_id, chatanooga_location_id, 7))
cursor.execute(inventory_insert_template.format(infant_car_seat_id, nashville_location_id, 6))
cursor.execute(inventory_insert_template.format(infant_car_seat_id, oakridge_location_id, 3))
cursor.execute(inventory_insert_template.format(convertable_car_seat_id, chatanooga_location_id, 20))
cursor.execute(inventory_insert_template.format(convertable_car_seat_id, nashville_location_id, 50))
cursor.execute(inventory_insert_template.format(convertable_car_seat_id, oakridge_location_id, 10))
cursor.execute(inventory_insert_template.format(booster_seat_id, chatanooga_location_id, 4))
cursor.execute(inventory_insert_template.format(booster_seat_id, nashville_location_id, 14))
cursor.execute(inventory_insert_template.format(booster_seat_id, oakridge_location_id, 12))
cursor.execute(inventory_insert_template.format(pack_and_play_id, chatanooga_location_id, 4))
cursor.execute(inventory_insert_template.format(pack_and_play_id, nashville_location_id, 44))
cursor.execute(inventory_insert_template.format(pack_and_play_id, oakridge_location_id, 1142))
cursor.execute(inventory_insert_template.format(breast_pump_id, chatanooga_location_id, 2))
cursor.execute(inventory_insert_template.format(breast_pump_id, nashville_location_id, 4))
cursor.execute(inventory_insert_template.format(box_fan_id, chatanooga_location_id, 2))
cursor.execute(inventory_insert_template.format(box_fan_id, nashville_location_id, 4))
cursor.execute(inventory_insert_template.format(box_fan_id, oakridge_location_id, 6))
cursor.execute(inventory_insert_template.format(floor_gym_id, chatanooga_location_id, 10100))
cursor.execute(inventory_insert_template.format(floor_gym_id, nashville_location_id, 2))
cursor.execute(inventory_insert_template.format(sleep_sack_id, nashville_location_id, 1000))
cursor.execute(inventory_insert_template.format(disinfectant_id, oakridge_location_id, 90000))








