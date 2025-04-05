# CSCI 1228
Assignment 6
Objective
Practicing I/O, working with a .CSV file
Practicing Comparable/Comparator interfaces
NOTE: Code formatting requirements are the same as for the CSCI 1226: https://cs.smu.ca/~myoung/csci1226/

Complete the tasks below and submit the Summary.java and Person.java files on Brightspace

Introduction
In this assignment you are going to practice a bit more with I/O and work on a simple dataset using the techniques that you learned during the course

Your Tasks:
We are going to work with a CSV file located here: https://people.math.sc.edu/Burkardt/datasets/csv/biostats.csv. It contains biometric statistics for a group of office workers. The file has an initial header line, 18 records, and 5 fields: Name, Sex, Age, Height, and Weight.

You will work with the following classes:

A class Person that represents a single record from the CSV file (provided).

Implement any additional methods/fields for the Person class based on the needs of the project.

A class named Summary (a starter file is provided) that should implement the following behavior based on given command-line arguments (see sample outputs below). Options for the first argument:

download (provided for you): Downloads the file from the link above into a CSV file specified as the second argument.
The command should download a CSV file in the current working directory (DO NOT download the file manually from your browser, you program must do it!).
A message indicating whether the file has been downloaded successfully (or not) should be printed to the console.
The corresponding command in your Tester.java file is:

//download
Summary.main(new String[]{"download", "people.csv"});

summary: print a summary for the file (you must caclulate the values, not just hardcode them):
Total number of records (excluding the header)
Youngest person
Oldest person
Average age
Tallest person and their name
Shortest female's name
The second command-line argument must be a name of the input .CSV file.

The corresponding command in your Tester.java file is:

//summary - csv
Summary.main(new String[]{"summary", "person.csv"});


print: print the contents of the file with sorting options.
The second command-line argument must be a name of the input file (you must provide a name of the input file: the .CSV one or the binary one depending on the second argument). The outputs must be the same for both files.

The third argument is optional: it tells your program how to sort the records. Available options: n - sort by name, a - sort by age, h - sort by height

The fourth argument is optional: it tells your program whether the sort must be reversed. Available options: true

NOTE: Use Comparators for the Person class, do not forget to re-compile the Person class after you add Comparators to it!

General Hints:
Think about designing your methods that help to re-use your code and avoid code duplication (e.g., it's easier to work with an array of Personss for printing and providing a summary)
You are provided with the Tester.java file: feel free to run the needed commands from it instead of using command-line interface - it will save your time!
Grading scheme (max 10 points):
[2 pts] The Person class has all necessary Comparators to allow sorting objects by the required fields
[3 pts] The Summary program correctly calculates and prints the stats for the summary command
[2 pts] The Summary program correctly implements the print command excluding the optional arguments for sorting the records
[1.5 pts] The Summary program correctly implements the sorting arguments for sorting the records for the print command (n/a/h)
[1.5 pts] The Summary program correctly implements the sorting arguments for sorting the records for the print command (for sorting in reverse order)
BONUS: 1.5 pt if the project is on GitHub with the link provided and the project has reasonable commit history
Points may be subtracted for:
[-1] Poorly formatted code
[-1] Javadocs are missing or incorrect
[-1] Meaningless variable names or variable names that do not follow the standards
[-1] Poorly designed code (e.g., duplicating code without proper code reuse)
