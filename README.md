# FitnessClass-Desktop-App
Java: Desktop application built using the BlueJ IDE and GUI tooling (https://www.bluej.org/). Simulates a fitness centre booking system.

## Try the app (WeekendFitnessClub_v2_16018361.jar)
Simply open the .jar file in the root folder - this will require that Java is installed on your machine.

## A detailed report (WeekendFitnessClub_v2_16018361 Report.pdf)
This readme file is intended to give the reader a quick overview, a more detailed report is located at the root folder.

## Planning (Assignment task list.txt)
I strongly believe in planning any sifficiently large project ahead of time. I often use a checklist to do this in reality, as this is a great way to communicate progress and intent to team members.

## Construction (https://www.bluej.org/)
I used the BlueJ editor to build the application using Java. Project files are available in the project-files folder.

## Architecture
The code is suitably decoupled such that the back end is comprised of a number of classes and collection classes (Hashmaps in this instance), of which the Club class is composed. The GUIWeekendFitnessClub class then provides the user a means of manipulating the club object using a graphical user interface.

## Highlights
* The use of enums was a great way to define a limited and immutable set of options, for instance Slot.java which contains the time slots for the fitness centre, that were not expected to change.
* Abstractions were used appropriately to maintain a balance between coupling and cohesion
* Simple, intuitive GUI design.

## Future development
* GUI elements could be extended to make the code drier (GUIWeekendFitnessClub.java, lines from 252)
* Serialization could have been used to quickly add persistence to the application
* The code was sufficiently decoupled that automated testing could have been incorporated
