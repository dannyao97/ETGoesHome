#!/usr/bin/python
import re
# Populate tables.
# Daniel Yao - dsyao@calpoly.edu

# Open files
location = open("shootings.csv")
location_sql = open("build-Location.sql", "w+")
location_sql.write("-- Insert statements for Location table\n\n")

location2 = open("ufoSightings.csv")
location2_sql = open("build-Location2.sql", "w+")
location2_sql.write("-- Insert statements for Location table\n\n")

shooting = open("shootings.csv")
shooting_sql = open("build-Shooting.sql", "w+")
shooting_sql.write("-- Insert statements for Shooting table\n\n")

def date2mysql(oracleDate):

    ## Dictionary of months
    months = {'1':'01', '2':'02', '3':'03', '4':'04',
              '5':'05', '6':'06', '7':'07', '8':'08',
              '9':'09', '10':'10', '11':'11', '12':'12'}

    dateComponents = oracleDate.split('/')  ## decompose the date

    oracleMonth = dateComponents[0].upper()
    month = months[oracleMonth]  ## convert the month
  
    oracleYear = dateComponents[2].strip(" \n\r")
    year =""
    if len(oracleYear) == 2:
        year = '20'+oracleYear              ## convert the year
                                            ## this is cheating a bit
                                            ## but all dates in 365 DBs are in 
                                            ## the 21st century
    elif len(oracleYear) == 4:                    ## if the year is already four characters
        year = oracleYear                   ## then keep it intact      

    day = dateComponents[1]                 ## extract day of month
 
    mysqlDate = year+'-'+month+'-'+day      ## form the converted string
    return(mysqlDate)



# Create Location insert file
location.readline()
for line in location:
   lineArr = line.split(",")
   
   location_sql.write("INSERT INTO Location(State, City) VALUES("\
   + "'" + lineArr[9].lower() + "', '" + lineArr[8].lower() + "');\n")

# Create location2 insert file   
location2.readline()
count = 0
for line in location2:
   count += 1
   if count % 9 == 0:
      lineArr = line.split(",")
      if str(lineArr[3]) != "us":
         continue
      if len(lineArr[1].split(" ")) > 4:
         continue

      mystring = lineArr[1].lower()
      result = mystring
      start = mystring.find( '(' )
      end = mystring.find( ')' )
      if start != -1 and end != -1:
        result = mystring[start+1:end]
      
      if result.find('#')!=-1 or result.find('.')!=-1:
         continue
      
      location2_sql.write("INSERT INTO Location(State, City) VALUES("\
      + "'" + lineArr[2].lower() + "', '" + result + "');\n")

# Create Shooting insert file
shooting.readline()
for line in shooting:
   lineArr = line.split(",")
   if lineArr[7] == "" or lineArr[6] == "":
      continue
   
   shooting_sql.write("INSERT INTO Shootings(Id, Name, Day, Death, Weapon, Age,"\
   "Gender, Race, City, State, Mental, Threat, Flee, BodyCamera) VALUES("\
   + lineArr[0]
   + ", " + "'" + lineArr[1].lower() + "'"
   + ", " + "'" + date2mysql(lineArr[2]) + "'"
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
   + ", " + "'" + lineArr[13].upper()[:1] + "'" + ");\n")








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
