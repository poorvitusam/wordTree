Navigate to the folder of this README file:

Perform Below commands to compile/run/clean the program:


## To clean:
ant -buildfile wordTree/src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile wordTree/src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
ant -buildfile wordTree/src/build.xml run -Darg0=input.txt -Darg1=output.txt 
-Darg2=NUM_OF_THREADS -Darg3=WORDS_TO_BE_DELETED -Darg4=DebugLevelInt

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

To store results and threads: arraylist- O(n)

Used Binary Search Tree for storing words: 
Complexity::
search - worst case = O(n) & best case = O(logn) where n is the number of nodes
insert - worst case = O(n) & best case = O(logn) where n is the number of nodes


-----------------------------------------------------------------------

MyLogger Updates:

Log Level 0 - RELEASE
Log Level 1 - DEBUG - Prints the output (Statistics)
Log Level 2 - VERBOSE - Prints the Thread process
Log Level 3 - RUNNABLE - Every time a thread's run method is called
Log Level 4 - CONSTRUCTOR - Every time a constructor is called

-----------------------------------------------------------------------
