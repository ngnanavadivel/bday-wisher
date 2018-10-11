# bday-wisher

## 1. How to build this code?

```sh
mvn clean package
```

>The above command will create a fat jar **bday-wisher-1.0-jar-with-dependencies.jar** under **target** folder.


## 2. Now COPY the following files

   - __bday-wisher-1.0-jar-with-dependencies.jar__
   - __excel-config.properties__

to any folder of your choice. For example, **C:/Users/Admin/bday-job**

## 3. Make sure you update the Excel file location in excel-config.properties

.

## 4. Now execute the batach job:

```sh
C:/Users/Admin/bday-job> java -jar bday-wisher-jar-1.0-with-dependencies.jar
```
.
## 5. Sample Output:

Batch Job has STARTED : 11.Oct.2018 09:06:00

Excel Sheet File Name : C:\Users\Admin\Downloads\Students.xlsx

WorkSheet Index : 0

Name column -> 0

DOB column -> 1

Email column -> 2

Phone column -> 3

Batch column -> 4

Branch Code column -> 5

Mail successfully sent to xxxx.yyyyy@gmail.com

Mail successfully sent to pppp.qqqqq@gmail.com

Batch Job has ENDED : 11.Oct.2018 09:06:09

