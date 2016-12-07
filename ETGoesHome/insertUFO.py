#!/usr/bin/python

ufo = open("ufoSightings.csv")
ufo_sql = open("build-UFOs.sql", "w+")

def time2mysql(time):
   
   time = time.strip(" \n\r")
   timeComponents= time.split(":")   # decompose the time
   mysqlTime = ""

   if len(timeComponents) == 3:   # if the time is already in HH:MM:SS format 
       mysqlTime = time           # do nothing
   elif len(timeComponents) == 2: # otherwise, add "00" hours
       mysqlTime = '00:'+time

   return mysqlTime

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
   
   
# Create UFO insert file
ufo.readline()
count = 0;
for line in ufo:
   count += 1
   if count % 9 == 0:
      lineArr = line.split(",")
      datetime = lineArr[0].split(" ")
      if len(datetime) != 2 or str(lineArr[3]) != "us":
         continue
   
      ufo_sql.write("INSERT INTO UFOSightings(Occurence, State, City, Shape,"\
      "Duration_Seconds, Description) VALUES("\
      + "'" + date2mysql(datetime[0]) + " " + time2mysql(datetime[1]) + "'"
      + ", " + "'" + lineArr[2].lower() + "'"
      + ", " + "'" + lineArr[1].lower() + "'"
      + ", " + "'" + lineArr[4].lower() + "'"
      + ", " + lineArr[5]
      + ", " + "'" + lineArr[7].lower() + "'" + ");\n")



## convert Oracle default for time to MySQL time expression
## If the time value has [H]H:MM:SS format - do nothing
## if it has [M]M:SS format - add leading zeroes



