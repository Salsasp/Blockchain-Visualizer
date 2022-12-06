# Project 5: Java GUI

For this project, you will be implementing a GUI to visualize the data you have collected so far using any libraries you wish. You will create three charts, each one built from a method from the previous projects. Each chart will also have components that allow for user input to influence and update the charts. 

There are templates provided for Blocks.java and Transaction.java that are caught up through project 4 in case you were unable to complete it. Grading will be based on screenshots that you will upload to your repo (create a folder called screenshots to store them). Each screenshot will be reviewed for different inputs on your charts. Your screenshots do not have to match the given ones exactly. They will be checked for the required information on each chart and the required functionality for each component. You have complete freedom to make any new classes, methods, updates, or revisions to previous methods in order to get the GUI working. 

## Navigation

Implement a JComboBox that allows you to navigate to each chart on another page. (10 pts)

<img src=./samples/navigation.png width=50% height=50%>

## Unique Miners

Implement a Pie Chart that tracks each unique miner, with the slice of the pie being determined by its frequency. Implement two JSliders that are used to update the bounds of the chart. One should have access to all 100 Blocks (15049308 - 15049407), and the other should be dynamically updated to always have the minimum value be the current value of the first slider plus one to avoid giving the Blocks out of order. 

<img src=./samples/UniqMiners1.PNG width=50% height=50%>

(10 pts)

<img src=./samples/UniqMiners2.PNG width=50% height=50%>

(10 pts)

<img src=./samples/UniqMiners3.PNG width=50% height=50%>

(10 pts)

## Transaction Cost

Implement a Line Chart that can track up to 3 different transaction costs at once: average transaction cost, lowest transaction cost, and highest transaction cost of each Block. Only include Blocks 15049308 - 15049322 as those are the Blocks with the extra transaction data. Implement 3 JCheckBoxes to determine which costs should graphed at any moment. 

<img src=./samples/TCost1.PNG width=50% height=50%>

(5 pts)

<img src=./samples/TCost2.PNG width=50% height=50%>

(5 pts)

<img src=./samples/TCost3.PNG width=50% height=50%>

(5 pts)

<img src=./samples/TCost4.PNG width=50% height=50%>

(5 pts)

<img src=./samples/TCost5.PNG width=50% height=50%>

(5 pts)

<img src=./samples/TCost6.PNG width=50% height=50%>

(5 pts)

## Time Difference

Implement a Bar Graph that displays the time difference between two given Blocks in hours, minutes, and seconds. Implement two JTextFields where a user can input Block Numbers in any order, and a JButton that adds the time difference between them onto the graph without disturbing the previous entries. 

<img src=./samples/TDiff1.PNG width=50% height=50%>

(10 pts)

<img src=./samples/TDiff2.PNG width=50% height=50%>

(10 pts)

<img src=./samples/TDiff3.PNG width=50% height=50%>

(10 pts)

## Grading 

Plagiarism will not be tolerated under any circumstances. Participating students will be penalized depending on the degree of plagiarism. It includes “No-code” sharing among the students. It can lead to academic misconduct reporting to the authority if identical code is found among the students. 

This project will be graded entirely off of your provided screenshots. You should take screenshots to match each one given in the readme. Please place your screenshots in a folder called /screenshots in your project 5 repo. The layouts and charts do not have to match exactly, the grading is based off having all the required components and functionality. Also be sure to include your name in the title of the window as a signature of your work and make sure it is visible in the screenshots. 

Submit your project before the due date/time. No late submissions allowed.
