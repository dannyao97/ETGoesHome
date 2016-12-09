#!/usr/bin/python
import re
# Populate tables.
# Daniel Yao - dsyao@calpoly.edu

# Open files
location3 = open("statecountycity.csv")
location_sql = open("build-Location3.sql", "w+")
location_sql.write("-- Insert statements for Location table\n\n")



# Create Location insert file
location3.readline()
for line in location3:
   lineArr = line.split(",")
   
   location_sql.write("INSERT INTO Location(State, City) VALUES("\
   + "'" + lineArr[0].lower() + "', '" + lineArr[2] + "');\n")

