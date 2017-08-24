# VendingMachine-kata

to build:

    gradlew build

to run my tests:

    gradlew test

Classes will be in build/classes, or you can use the jar in build/libs.  I was a little uncertain as to whether this was supposed to be executable or not.  The general katas page says the readme should include instructions to build "and run" my solution, but the kata description seemed to be looking more for a library (I interpret "the brains of" as "back-end-logic for").  If you were looking for a vending machine simulator program, I'll be happy to provide a basic command line interface for this. If not, I've provided javadac which you can generate in build/docs/javadoc with

    gradlew javadoc
