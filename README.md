# Programming course by ITMO University, 1st year studying #

## Laboratory work #1. ##

Write a Java program that performs the appropriate action. The program must meet the following requirements:

1. It should be packed into an executable jar archive.
1. The expression must be evaluated in accordance with the rules for evaluating mathematical expressions (the order of actions must be followed, etc.).
1. The program must use math functions from the Java standard library.
1. The result of evaluating the expression must be output to the standard output stream in the specified format.

The execution of the program must be demonstrated on the  ```helios``` server.

### Individual specification: ###

1. Create a one-dimensional array d of type ```long```. Fill it in with even numbers from 2 to 20 inclusive in descending order.
1. Create a one-dimensional array x of type ```double```. Fill it with 16 random numbers ranging from -11.0 to 5.0.
1. Create a 10x16 two-dimensional array d. Calculate its elements using the following formula (where x = x [j]):
![task1](https://user-images.githubusercontent.com/71551187/140786451-1ec74c46-e573-4b85-935e-2a67c03e3e3d.png)
1. Print the resulting array in two decimal places.

### The laboratory report should contain: ###

1. The text of the task.
1. The source code of the program.
1. The result of the program.
1. Conclusions on the work.

### Questions to defend laboratory work: ###

1. Java language. Features of the language.
1. Development tools. JDK and JRE.
1. Primitive data types in Java.
1. Working with variables. Declaration. Initialization. Assignment.
1. Branching and looping instructions.
1. Operators and Expressions in Java. Features of the calculation, priorities of operations.
1. Mathematical functions in the Java standard library. Class ```java.lang.Math```.
1. Formatted numeric output.

## Laboratory work #2. ##

Based on the base ```Pokemon``` class, write your own classes for the specified types of Pokemon. Each type of Pokémon must have one or two types and standard basic characteristics:

1. health points (HP)
1. attack
1. defense
1. special attack
1. special defense
1. speed

Pokémon classes must be inherited according to the Pokémon evolutionary chain. Based on the base classes  ```PhysicalMove```, ```SpecialMove``` and ```StatusMove```, implement your own classes for the specified types of attacks.

The attack must be of standard type, power and accuracy. Standard attack effects must be implemented. Assign attacks to each type of Pokémon according to the option. The Pokémon level is chosen as the minimum required for all implemented attacks.

Using the ```Battle``` simulation class, create 2 Pokémon teams (each Pokémon must have a name) and start the battle.

The base classes and the battle simulator are in the jar-archive (updated on 10/9/2018, a bug with the addition of attacks and encoding was fixed). Documentation in Javadoc format is [here](https://se.ifmo.ru/~tony/doc/).

Information about Pokémon, evolution chains and attacks can be found at http://poke-universe.ru, http://pokemondb.net, http://veekun.com/dex/pokemon

### Comments. ###

Purpose of work: using a simple example to understand the basic concepts of OOP and learn how to use them in programs.

### What to do (short description): ###

1. Check out the documentation with a special focus on the Pokemon and Move classes. As you continue to complete the lab, read the documentation a few more times.
1. Download the Pokemon.jar file. It will need to be used both for compilation and for running the program. You don't need to unpack it! You need to learn how to connect external jar files to your program.
1. Write a minimal program and see how it works.
```
Battle b = new Battle();
Pokemon p1 = new Pokemon("Stranger", 1);
Pokemon p2 = new Pokemon("Predator", 1);
b.addAlly(p1);
b.addFoe(p2);
b.go();
```
1. Create one of the ```Pokemon``` classes for your variant. The class must inherit from the base Pokemon class. In the constructor, you will need to set the types of the Pokemon and its basic characteristics. After that, try adding Pokemon to the battle.
1. Create one of the attack classes for your variant (it is best to start with a physical or special attack). The class must inherit from the ```PhysicalMove``` or ```SpecialMove``` class. In the constructor, you will need to set the type of attack, its strength and accuracy. After that, add an attack to the Pokémon and test its action in battle. Don't forget to override the describe method to display the desired message.
1. If the attack action differs from the standard one, for example, the Pokemon does not miss, or the attacking Pokemon also receives damage, then the corresponding methods must be additionally redefined in the attack class (see the documentation). When implementing attacks that change the status of a Pokemon (inherited from StatusMove), you will most likely have to deal with the Effect class. It allows you to change the state of the Pokemon or the modifier of its basic characteristics for one or several turns.
1. Finish all the necessary attacks and all the Pokémon, distribute the Pokémon into teams, start the battle.

### Individual specification: ###
![task2](https://user-images.githubusercontent.com/71551187/140787268-643a8a3a-1779-4704-852f-34af41c75d7d.png)

### The work report should contain: ###

1. The text of the task.
1. Class diagram of the implemented object model.
1. The source code of the program.
1. The result of the program.
1. Conclusions on the work.

### Questions to defend laboratory work: ###

1. Object Oriented Programming. Basic concepts: objects, inheritance, polymorphism, encapsulation.
1. Class concept. Classes and objects in Java.
1. Class members. Access modifiers.
1. Creation and initialization of objects. Calling methods.
1. Variable scopes.
1. ```Final``` and ```static``` modifiers.
1. Packages, ```import``` statement.

## Laboratory work #3. ##

### The program must meet the following requirements: ###

1. The modified model must comply with the SOLID principles.
1. The program must contain at least two interfaces and one abstract class (the nomenclature must be agreed with the teacher).
1. The developed classes must override the ```equals()```, ```toString()``` and ```hashCode()``` methods.
1. The program must contain at least one enumerated type (enum).

### Work order: ###

1. Refine the application object model.
1. Redraw the class diagram according to the changes made to the model.
1. Agree with the teacher about the changes made to the model.
1. Modify the program in accordance with the changes made to the model.

### Individual specification: ###

Text has been lost. It was part of “Neznayka on the Moon” fairy tale. The arrival of the expedition to the moon was described, a dialogue between a Neznayka with a local journalist about the differences between life on Earth and the Moon. Further, according to the plot, Neznayka handed over samples of terrestrial plants to lunar scientists who began to study them.

### The work report should contain: ###

1. The text of the task.
1. Class diagram of the implemented object model.
1. The source code of the program.
1. The result of the program.
1. Conclusions on the work.

### Questions to defend laboratory work: ###

1. The principles of object-oriented programming SOLID and STUPID.
1. ```Object``` class. The default implementation of its methods.
1. Features of the implementation of inheritance in Java. Simple and multiple inheritance.
1. Abstract class concept. The ```abstract``` modifier.
1. Interface concept. Implementation of interfaces in Java, default methods. Differences from abstract classes.
1. Enumerated data type (enum) in Java. Features of implementation and use.
1. Methods and fields with ```static``` and ```final``` modifiers.
1. Overloading and overriding methods. Return data type covariants.
1. Elements of functional programming in Java syntax. Functional interfaces, lambda expressions. Method references.

## Laboratory work #4. ##

### The program must meet the following requirements: ###

1. The program must implement 2 of its own exception classes (checked and unchecked), as well as the exception handling of these classes.
1. The use of local, anonymous and nested classes (static and non-static) must be added to the program.

### Work order: ###

1. Refine the application object model.
1. Redraw the class diagram according to the changes made to the model.
1. Agree with the teacher about the changes made to the model.
1. Modify the program in accordance with the changes made to the model.


### Individual specification: ### 
Text for this laboratory work has been lost too. In was a large text. The plot of the previous laboratory work was described, as well as the further development of the business on the Moon according to the model of market relations. Various shops and hospitals were opened. A joint-stock company was opened, headed by travelers well-known according to the previous assignment. They started selling shares. The process of creating this organization and its interior were also described. Further in the plot, this company was robbed and the subsequent arrival of the police.

### The work report should contain: ###

1. The text of the task.
1. Object model class diagram.
1. The source code of the program.
1. The result of the program.
1. Conclusions on the work.

### Questions to defend laboratory work: ###

1. Exception handling, three types of exceptions.
1. Nested, local and anonymous classes.
1. The reflection mechanism in Java. Class ```Class```.

## Laboratory work #5. ##

Implement a console application that implements interactive management of a collection of objects. The collection must store objects of the ```Person``` class, which is described below.

### The developed program must meet the following requirements: ###

1. A class whose collection of instances is managed by a program must implement default sorting.
1. All requirements for class fields (specified in the form of comments) must be met.
1. For storage, you must use a collection of type ```java.util.HashSet```
1. When the application starts, the collection should be automatically filled with values from the file.
1. The filename must be passed to the program using: command line argument.
1. The data must be stored in a file in ```xml``` format
1. Reading data from a file must be implemented using the ```java.io.BufferedReader``` class
1. Writing data to a file must be implemented using the ```java.io.FileWriter``` class
1. All classes in the program must be documented in javadoc format.
1. The program must work correctly with incorrect data (user input errors, lack of access rights to the file, etc.).

### In interactive mode, the program must support the execution of the following commands: ###

1. ```help```: display help for available commands
1. ```info```: print information about the collection (type, date of initialization, number of elements, etc.) to standard output
1. ```show```: display all elements of the collection in string representation to standard output
1. ```add {element}```: add a new element to the collection
1. ```update id {element}```: update the value of the collection element whose id is equal to the given
1. ```remove_by_id id```: remove an item from the collection by its id
1. ```clear```: clear the collection
1. ```save```: save the collection to a file
1. ```execute_script file_name```: read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.
1. ```exit```: exit the program (without saving to file)
1. ```add_if_min {element}```: add a new element to the collection if its value is less than the smallest element in this collection
1. ```remove_greater {element}```: remove all elements greater than the specified one from the collection
1. ```remove_lower {element}```: remove all elements from the collection that are less than the given one
1. ```sum_of_height```: Print the sum of the values of the height field for all elements of the collection
1. ```group_counting_by_nationality```: group the elements of the collection by the value of the nationality field, display the number of elements in each group
1. ```count_greater_than_nationality nationality```: Print the number of items whose nationality is greater than the specified value

### Command input format: ###

1. All command arguments that are standard data types (primitive types, wrapper classes, String, date storage classes) must be entered on the same line as the command name.
1. All composite data types (class objects stored in a collection) must be entered one field per line.
1. When entering composite data types, the user should be shown an input prompt containing the field name (for example, "Enter date of birth:")
1. If the field is an enum, then the name of one of its constants is entered (in this case, the list of constants must be previously displayed).
1. In case of incorrect user input (a string is entered that is not the name of a constant in the enum; a string is entered instead of a number; the entered number is not included in the specified limits, etc.) an error message should be displayed and the field should be prompted to re-enter the field.
1. Use an empty string to enter null values.
1. Fields with the comment "The value of this field should be generated automatically" should not be entered manually by the user when adding.

### Description of the classes stored in the collection: ###

Все аргументы команды, являющиеся стандартными типами данных (примитивные типы, классы-оболочки, String, классы для хранения дат), должны вводиться в той же строке, что и имя команды.
Все составные типы данных (объекты классов, хранящиеся в коллекции) должны вводиться по одному полю в строку.
При вводе составных типов данных пользователю должно показываться приглашение к вводу, содержащее имя поля (например, "Введите дату рождения:")
Если поле является enum'ом, то вводится имя одной из его констант (при этом список констант должен быть предварительно выведен).
При некорректном пользовательском вводе (введена строка, не являющаяся именем константы в enum'е; введена строка вместо числа; введённое число не входит в указанные границы и т.п.) должно быть показано сообщение об ошибке и предложено повторить ввод поля.
Для ввода значений null использовать пустую строку.
Поля с комментарием "Значение этого поля должно генерироваться автоматически" не должны вводиться пользователем вручную при добавлении.
Описание хранимых в коллекции классов:

```
public class Person {
    private int id; //The field value must be greater than 0, The value of this field must be unique, The value of this field must be generated automatically
    private String name; //Field cannot be null, String cannot be empty
    private Coordinates coordinates; //The field cannot be null
    private java.time.LocalDateTime creationDate; //The field cannot be null, the value of this field must be generated automatically
    private long height; //The field value must be greater than 0
    private Color eyeColor; //The field can be null
    private Color hairColor; //The field can be null
    private Country nationality; //The field can be null
    private Location location; //The field can be null
}
public class Coordinates {
    private long x; //Maximum Field Value: 690
    private float y;
}
public class Location {
    private long x;
    private Float y; //The field cannot be null
    private String name; //The field cannot be null
}
public enum Color {
    GREEN,
    RED,
    BLUE;
}
public enum Color {
    BLUE,
    YELLOW,
    ORANGE;
}
public enum Country {
    GERMANY,
    CHINA,
    NORTH_KOREA;
}
```

## Laboratory work #6 ##

Divide the program from **laboratory work #5** into client and server modules. The server module must execute commands for managing the collection. The client module must interactively read commands, send them for execution to the server, and output the results of execution.

### The following requirements must be met: ###

1. Collection object processing operations must be implemented using the Stream API using lambda expressions.
1. Objects between client and server must be serialized.
1. Objects in the collection passed to the client should be sorted by default
1. The client must correctly handle temporary server unavailability.
1. The exchange of data between the client and the server must be carried out using the UDP protocol
1. To exchange data on the server, you must use a **network channel**
1. To exchange data on the client, you must use **datagrams**
1. Network links must be used in non-blocking mode.

### Responsibilities of the server application: ###

1. Working with a file that stores a collection.
1. Managing a collection of objects.
1. Assigning automatically generated fields to objects in a collection.
1. Waiting for connections and requests from the client.
1. Processing received requests (commands).
1. Saving the collection to a file when the application exits.
1. Saving a collection to a file when executing a special command available only to the server (the client cannot send such a command).

### The server application should consist of the following modules (implemented as one or more classes): ###

1. The module for accepting connections.
1. Request reader.
1.The module for processing received commands.
1. Module for sending responses to the client.

The server must be running in **single threaded** mode.

### Responsibilities of the client application: ###

1. Reading commands from the console.
1. Validation of input data.
1. Serialization of the entered command and its arguments.
1. Sending the received command and its arguments to the server.
1. Processing a response from the server (outputting the result of command execution to the console).
1. The ```save``` command must be removed from the client application.
1. The ```exit``` command terminates the client application.

**Important!** Commands and their arguments must be class objects. The exchange of "simple" strings is inadmissible. So, for the ```add``` command or its equivalent, you need to form an object containing the command type and the object that should be stored in your collection.
Additional task:
Implement logging of various stages of server operation (starting work, receiving a new connection, receiving a new request, sending a response, etc.) using **Logback**

### The work report should contain: ###

1. The text of the task.
1. Class diagram of the developed program (both client and server applications).
1. The source code of the program.
1. Conclusions on the work.

### Questions to defend laboratory work: ###

1. Networking - client-server architecture, basic protocols, their similarities and differences.
1. TCP protocol. ```Socket``` and ```ServerSocket``` classes.
1. UDP protocol. The ```DatagramSocket``` and ```DatagramPacket``` classes.
1. Differences between blocking and non-blocking Input/Output, their advantages and disadvantages. Working with network channels.
1. ```SocketChannel``` and ```DatagramChannel``` classes.
1. Data transmission over the network. Serialization of objects.
1. ```Serializable``` interface. Object graph, serialization and deserialization of fields and methods.
1. Java Stream API. Creation of conveyors. Intermediate and terminal operations.
1. Design patterns: Decorator, Iterator, Factory method, Command, Flyweight, Interpreter, Singleton, Strategy, Adapter, Facade, Proxy.

## Laboratory work #7 ##

Modify the program from **laboratory work #6** as follows:

1. Organize collection storage in a relational DBMS (PostgresQL). Remove storage of the collection in a file.
1. Use the database facility (sequence) to generate the id field.
1. Update the state of the collection in memory only when the object is successfully added to the database
1. All data retrieval commands must work with the collection in memory, not in the database
1. Organize the possibility of registration and authorization of users. The user has the option to specify a password.
1. Store passwords hashed using the ```SHA-512``` algorithm
1. Prevent unauthorized users from executing commands.
1. When storing objects, store information about the user who created this object.
1. Users should be able to view all objects in the collection, but only those that belong to them can be modified.
1. To identify the user, send a username and password with each request.

It is necessary to implement **multithreaded request processing**.

1. For multi-threaded reading of requests, use the ```Cached thread pool```
1. For multithreaded processing of the received request, use the ```Cached thread pool```
1. Use ```Cached thread pool``` for multithreaded response
1. To synchronize access to the collection use synchronization read and write using ```java.util.concurrent.locks.ReadWriteLock```

### Work order: ###

1. Use PostgreSQL as a database.
1. To connect to the database on the cathedral server, use the host ```pg```, the database name is ```studs```, the username / password are the same as for connecting to the server.

### The work report should contain: ###

1. The text of the task.
1. Class diagram of the developed program.
1. The source code of the program.
1. Conclusions on the work.

### Questions to defend laboratory work: ###

1. Multithreading. ```Thread``` class, interface ```Runnable```. The ```synchronized``` modifier.
1. The ```wait()```, ```notify()``` methods of the ```Object``` class, the ```Lock``` and ```Condition``` interfaces.
1. Synchronizing classes from the ```java.util.concurrent``` package.
1. The ```volatile``` modifier. Atomic data types and operations.
1. Collections from the ```java.util.concurrent``` package.
1. ```Executor```, ```ExecutorService```, ```Callable```, ```Future``` interfaces
1. Thread pools
1. JDBC. The order of interaction with the database. ```DriverManager``` class. ```Connection``` interface
1. Interfaces ```Statement```, ```PreparedStatement```, ```ResultSet```, ```RowSet```
1. Design patterns.
