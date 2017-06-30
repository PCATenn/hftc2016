import pymssql
from StdSuites.Table_Suite import row
inventory_insert_template = "insert into inventory (TOTAL_INVENTORY, PRODUCT_NAME, PRODUCT_DESC,LOCATION) values({0}, '{1}', '{2}', '{3}')"
server = 'pcat-stage-db-server.database.windows.net:1433'
password =  'Nashville-Nashville-Stage'
user ='pcat-administrator@pcat-stage-db-server'
database ='pcat_stage'
autocommit=True
cnx = pymssql.connect(server=server, user=user, password=password, database=database,autocommit=autocommit )

cursor = cnx.cursor()

cursor.execute('delete from inventory')

import csv
import re
# con = pymssql.connect(server="pcat-stage-db-server.database.windows.net")
with open('/Users/jabadehut/Projects/hftc2016/sql/inventory.csv', 'rU') as csvfile:
    csvreader = csv.reader(csvfile, delimiter=',', quotechar='|')
    for row in csvreader:
        quantity = 0
        ints = re.findall(r'\d+', row[0])
        print "" + row[0] + " = row[0]"  
        if len(ints) > 0:
            quantity = ints[0]
        print "" + quantity + " = quantity"

        insert_string = inventory_insert_template.format(quantity, row[1], row[2], row[3]) 
        print insert_string
        cursor.execute(insert_string)
        
cursor.close()
cnx.close()
