#!/usr/bin/python

ufo = open("ufoSightings.csv")
ufo_sql = open("build-UFOs.sql", "w+")

# Create UFO insert file
ufo.readline()
count = 0;
for line in ufo:
   count += 1
   if count % 10 == 0:
      lineArr = line.split(",")
   
      ufo_sql.write("INSERT INTO UFOSightings(Occurence, State, City, Shape,"\
      "Duration_Seconds, Description) VALUES("\
      + lineArr[0]
      + ", " + "'" + lineArr[1].lower() + "'"
      + ", " + "'" + lineArr[2].lower() + "'"
      + ", " + "'" + lineArr[3].lower() + "'"
      + ", " + lineArr[4]
      + ", " + "'" + lineArr[5].lower() + "'" + ");\n")



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

