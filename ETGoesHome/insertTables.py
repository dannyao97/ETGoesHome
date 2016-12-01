#!/usr/bin/python

# Populate tables.
# Daniel Yao - dsyao@calpoly.edu

# Open files
location = open("shootings.csv")
location_sql = open("build-Location.sql", "w+")
location_sql.write("-- Insert statements for Location table\n\n")

shooting = open("shootings.csv")
shooting_sql = open("build-Shooting.sql", "w+")
shooting_sql.write("-- Insert statements for Shooting table\n\n")

# Create Location insert file
location.readline()
for line in location:
   lineArr = line.split(",")
   
   location_sql.write("INSERT INTO Location(State, City) VALUES("\
   + "'" + lineArr[9].lower() + "', '" + lineArr[8].lower() + "');\n")

# Create Shooting insert file
shooting.readline()
for line in shooting:
   lineArr = line.split(",")
   
   shooting_sql.write("INSERT INTO Shootings(Id, Name, Day, Death, Weapon, Age,"\
   "Gender, Race, City, State, Mental, Threat, Flee, BodyCamera) VALUES("\
   + lineArr[0]
   + ", " + "'" + lineArr[1].lower() + "'"
   + ", " + lineArr[2]
   + ", " + "'" + lineArr[3].lower() + "'"
   + ", " + "'" + lineArr[4].lower() + "'"
   + ", " + lineArr[5]
   + ", " + "'" + lineArr[6] + "'" 
   + ", " + "'" + lineArr[7] + "'" 
   + ", " + "'" + lineArr[8].lower() + "'"
   + ", " + "'" + lineArr[9].lower() + "'"
   + ", " + "'" + lineArr[10].lower()[:1] + "'" 
   + ", " + "'" + lineArr[11].lower() + "'"
   + ", " + "'" + lineArr[12].lower() + "'" 
   + ", " + "'" + lineArr[13].lower()[:1] + "'" + ");\n")








## convert Oracle default for time to MySQL time expression
## If the time value has [H]H:MM:SS format - do nothing
## if it has [M]M:SS format - add leading zeroes

# this version preserves single quotes
def time2mysqlQuotes(time):
   
   time = time.strip(" \n\r'")
   timeComponents= time.split(":")   # decompose the time
   mysqlTime = ""

   if len(timeComponents) == 3:   # if the time is already in HH:MM:SS format 
       mysqlTime = "'"+time+"'"   # do nothing
   elif len(timeComponents) == 2: # otherwise, add "00" hours
       mysqlTime = "'00:"+time+"'"

   return mysqlTime
