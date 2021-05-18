# CSX42: Assignment 2
**Name: Pranav Deshpande

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [channelpopularity/src](./channelpopularity/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile channelpopularity/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile channelpopularity/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile channelpopularity/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"
```
Note: Arguments accept the absolute path of the files.

#imp
Used 2 slack days

## Description:
Followed State Pattern to represent youtube Channel which consists of states like
Unpopular, MildlyPopular, HighlyPopular and UltraPopular.
Operations that user can perform on states are 1)add a vide
2)remove a video
3)approve/reject a request for advertisement
4)provide metrics
HashMaps were used at several places to get the runtime complexity of O(1)


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [6/26/20]


