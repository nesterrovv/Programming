# Programming course by ITMO University, 1st year studying #

## Laboratory work #1. ##

Write a Java program that performs the appropriate action. The program must meet the following requirements:

1. It should be packed into an executable jar archive.
1. The expression must be evaluated in accordance with the rules for evaluating mathematical expressions (the order of actions must be followed, etc.).
1. The program must use math functions from the Java standard library.
1. The result of evaluating the expression must be output to the standard output stream in the specified format.

The execution of the program must be demonstrated on the  helios server.

### Individual specification: ###

1. Create a one-dimensional array d of type long. Fill it in with even numbers from 2 to 20 inclusive in descending order.
1. Create a one-dimensional array x of type double. Fill it with 16 random numbers ranging from -11.0 to 5.0.
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
1. Mathematical functions in the Java standard library. Class java.lang.Math.
1. Formatted numeric output.

## Laboratory work #2. ##

Based on the base Pokemon class, write your own classes for the specified types of Pokemon. Each type of Pokémon must have one or two types and standard basic characteristics:

1. health points (HP)
1. attack
1. defense
1. special attack
1. special defense
1. speed

Pokémon classes must be inherited according to the Pokémon evolutionary chain. Based on the base classes  PhysicalMove, SpecialMove and StatusMove, implement your own classes for the specified types of attacks.

The attack must be of standard type, power and accuracy. Standard attack effects must be implemented. Assign attacks to each type of Pokémon according to the option. The Pokémon level is chosen as the minimum required for all implemented attacks.

Using the Battle simulation class, create 2 Pokémon teams (each Pokémon must have a name) and start the battle.

The base classes and the battle simulator are in the jar-archive (updated on 10/9/2018, a bug with the addition of attacks and encoding was fixed). Documentation in Javadoc format is [here](https://se.ifmo.ru/~tony/doc/).

Information about Pokémon, evolution chains and attacks can be found at http://poke-universe.ru, http://pokemondb.net, http://veekun.com/dex/pokemon

### Comments. ###

Purpose of work: using a simple example to understand the basic concepts of OOP and learn how to use them in programs.

### What to do (short description): ###

1. Check out the documentation with a special focus on the Pokemon and Move classes. As you continue to complete the lab, read the documentation a few more times.
1. Download the Pokemon.jar file. It will need to be used both for compilation and for running the program. You don't need to unpack it! You need to learn how to connect external jar files to your program.
1. Write a minimal program and see how it works.

![run-pokemons](https://user-images.githubusercontent.com/71551187/140787203-5821e146-0353-42bf-96d1-d33574ea0654.png)
1. Create one of the Pokemon classes for your variant. The class must inherit from the base Pokemon class. In the constructor, you will need to set the types of the Pokemon and its basic characteristics. After that, try adding Pokemon to the battle.
1. Create one of the attack classes for your variant (it is best to start with a physical or special attack). The class must inherit from the PhysicalMove or SpecialMove class. In the constructor, you will need to set the type of attack, its strength and accuracy. After that, add an attack to the Pokémon and test its action in battle. Don't forget to override the describe method to display the desired message.
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
1. Final and static modifiers.
1. Packages, import statement.

## Laboratory work #3. ##

**Individual specification** (text) has been lost. It was part of “Neznayka on the Moon” fairy tale. The arrival of the expedition to the moon was described, a dialogue between a Neznayka with a local journalist about the differences between life on Earth and the Moon. Further, according to the plot, Neznayka handed over samples of terrestrial plants to lunar scientists who began to study them.

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
1. Final and static modifiers.
1. Packages, import statement.

## Laboratory work #4. ##

**Individual specification** for this laboratory work has been lost too. In was a large text. The plot of the previous laboratory work was described, as well as the further development of the business on the Moon according to the model of market relations. Various shops and hospitals were opened. A joint-stock company was opened, headed by travelers well-known according to the previous assignment. They started selling shares. The process of creating this organization and its interior were also described. Further in the plot, this company was robbed and the subsequent arrival of the police.

### The program must meet the following requirements: ###

1. The program must implement 2 of its own exception classes (checked and unchecked), as well as the exception handling of these classes.
1. The use of local, anonymous and nested classes (static and non-static) must be added to the program.

### Work order: ###

1. Refine the application object model.
1. Redraw the class diagram according to the changes made to the model.
1. Agree with the teacher about the changes made to the model.
1. Modify the program in accordance with the changes made to the model.

### The work report should contain: ###

1. The text of the task.
1. Object model class diagram.
1. The source code of the program.
1. The result of the program.
1. Conclusions on the work.

### Questions to defend laboratory work: ###

1. Exception handling, three types of exceptions.
1. Nested, local and anonymous classes.
1. The reflection mechanism in Java. Class Class.
