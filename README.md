# Virtual Vending Machine Capstone

## Introduction
Command-line vending machine program that allows users to:
- display vending machine items,
- feed money,
- select products, and
- get change.

---
## Architecture
---
The application is written in Java and demonstrates File I/O, object-oriented programming principles, and JUnit tests, as described below.

### File Input
A delimited file includes the products available for sale, with the type of the item, and the price. The products are read from the file when the application loads to stock the vending machine with 5 of each product.  

The delimited file is used to instantiate a File object, which is used to instantiate a Scanner object to read the lines from the file. A try-with-resources block is used when instantiating the Scanner in order to catch a potential FileNotFoundException and to automatically close the file when the try block is done.

Different types of products are instantiated based on the type specified for the product in the file and the products are added to a List, where the type is the abstract Product superclass.

### File Output  
Vending Machine transactions are recorded in an audit log, which only contains transactions for that instance of the vending machine. The first time a transaction is recorded after the application loads, any existing entries in the audit log are replaced, or a new audit log file is created for the entry if the file doesn't already exist. A new PrintWriter instance with the audit log file as a parameter is created in this case.

Subsequent transactions are appended to the audit log by creating an instance of PrintWriter using an instance of FileOutputStream as a parameter, where the FileOutputStream instance is created using the file as a parameter and an append parameter of **true**.

Manual flush and close of the PrintWriter is demonstrated here, where a try-with-resources block was not used.

### OOP

This project demonstrates some of the OOP principles covered during Module 1 of Tech Elevator.
- The classes demonstrate Encapsulation by bundling the state and behaviors of the objects and by using access modifiers to protect the internal mechanisms of the objects. 
- The classes demonstrate Abstraction by making internal implementation details private and only making public the high-level methods for using it.
- Inheritance is demonstrated by an abstract Product class that is extended by classes for individual types of products. Each type of product has a different message that is printed when it is dispensed, but they can all use the methods and variables from the Product class without duplicating the code from the superclass in the subclasses.
- A type of Polymorphism is demonstrated by passing a Product[] to provided menu methods that accept Object[], where Product is a subclass of Object. The methods return Object instances, which are cast back to Product instances in order to use the Product methods. The TEnmo project has a good example of Polymorphism where different implementations of the DAO interfaces are used based on whether a "test" or "prod" profile is active.

### JUnit Tests
- ListOfItemsTest has examples of testing console output by using System.setOut(new PrintStream(new ByteArrayOutputStream())).
- The change() method of the Funds class is an example where it returns the console output as a String, rather than printing the output directly, eliminating the need to capture console output in FundsTest.



