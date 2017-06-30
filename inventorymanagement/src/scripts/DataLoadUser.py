import pymssql
from StdSuites.Table_Suite import row
user_insert_template = "insert into \"user_info\" (FIRSTNAME, lastname, supervisor, email, role, supervisor_email, isactive) values('{0}', '{1}', '{2}', '{3}', '{4}', '{5}', {6})"
server = 'pcat-stage-db-server.database.windows.net:1433'
password =  'Nashville-Nashville-Stage'
user ='pcat-administrator@pcat-stage-db-server'
database ='pcat_stage'
autocommit=True
cnx = pymssql.connect(server=server, user=user, password=password, database=database,autocommit=autocommit )

cursor = cnx.cursor()

cursor.execute('delete from "user_info"')

import csv
# con = pymssql.connect(server="pcat-stage-db-server.database.windows.net")
with open('/Users/jabadehut/Projects/hftc2016/sql/users.csv', 'rU') as csvfile:
    csvreader = csv.reader(csvfile, delimiter=',', quotechar='|')
    for row in csvreader:
        insert_string = user_insert_template.format(row[0], row[4], row[1], row[2], row[3], row[5], 1) 
        cursor.execute(insert_string)
        print insert_string
        
cursor.close()
cnx.close()