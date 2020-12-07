# Programming
Laboratory work at ITMO University, programming. first year
______________________________________________________________________________________________________________________________________________________________________
Laboratory work #1, variant 312621

Write a Java program that performs the appropriate action options. The program must meet the following requirements:

1. It should be packaged into an executable jar archive.
2. The expression must be evaluated in accordance with the calculation of mathematical expressions (the order of actions must be observed, etc.).
3. The program must use math functions from the Java standard library.
4. The result of evaluating the expression to be output to standard output must be output in the specified format.

The program must be executed on the helios server.

The laboratory report should contain:
1. Task text.
2. Source code of the program.
3. The result of the program.
4. Conclusions on the work.


The task text has been uploaded to the repository. (Task.jpg)
The report has been uploaded to the repository. (Laba1_Nesterov_R3137.docx)

______________________________________________________________________________________________________________________________________________________________________
Laboratory work #2, variant 389512
Based on the base Pokemon class, write your own classes for the specified types of Pokemon. Each type of Pokémon must have one or two types and standard basic characteristics:
1. Health points (HP)
2. Attack (attack)
3. Protection (protection)
4. Special attack (special attack)
5. Special protection (special protection)
6. Speed (speed)
Pokémon classes must be inherited according to the Pokémon evolutionary chain. Based on the base classes PhysicalMove, SpecialMove and StatusMove, implement your own classes for the specified types of attacks.

The attack must be of standard type, power and accuracy. Standard attack effects must be implemented. Assign attacks to each type of Pokémon according to the option. The Pokémon level is chosen as the minimum necessary for all implemented attacks.

Using the Battle simulation class, create 2 Pokémon teams (each Pokémon must have a name) and start the battle.

The base classes and the battle simulator are in the jar archive (archieve Pokemon.jae has been uploaded)

Information about Pokémon, evolution chains and attacks can be found at http://poke-universe.ru, http://pokemondb.net, http://veekun.com/dex/pokemon

Comments
Purpose of work: by a simple example, to understand the basic concepts of OOP and learn how to use them in programs.

What to do (short description)
1. Check out the documentation, with a focus on the Pokemon and Move classes. As you continue to complete the lab, read the documentation a few more times.
2. Download the Pokemon.jar file. It will need to be used both for compilation and for running the program. You don't need to unpack it! You need to learn how to connect external jar files to your program.
3. Write a minimal program and see how it works.

Battle b = new Battle();

Pokemon p1 = new Pokemon("Чужой", 1);

Pokemon p2 = new Pokemon("Хищник", 1);

b.addAlly(p1);

b.addFoe(p2);

b.go();


4. Create one of the Pokemon classes for your variant. The class must inherit from the base Pokemon class. In the constructor, you will need to set the types of the Pokemon and its basic characteristics. After that, try adding the Pokémon to the battle.
5. Create one of the attack classes for your variant (it is best to start with a physical or special attack). The class must inherit from the PhysicalMove or SpecialMove class. In the constructor, you will need to specify the type of attack, its strength and accuracy. After that, add an attack to the Pokemon and test its action in battle. Don't forget to override the describe method to display the desired message.
6. If the attack action differs from the standard one, for example, the Pokemon does not miss, or the attacking Pokemon also receives damage, then the corresponding methods must be additionally redefined in the attack class (see the documentation). When implementing attacks that change the status of a Pokemon (inherited from StatusMove), you will most likely have to deal with the Effect class. It allows you to change the state of the Pokemon or the modifier of its basic characteristics for one or several turns.
7. Finish all the necessary attacks and all the Pokémon, distribute the Pokémon by teams, start the battle.

ATTENTION

Information on how to connect an external jar file to your program using InelliJ IDEA can be found here: https://javadevblog.com/kak-dobavit-biblioteku-jar-fajl-v-proekt-intellij-idea.html

However, you may need to include the jar file without using a development environment. In this case, I recommend examining the classpath. The fact is that when connecting an external jar file, you need to add information about this connection to the manifest of your file, which is what the development environment does. However, you cannot unzip the jar file yourself, add the required line to the manifest and zip everything back into the archive. You can solve this problem by using the build jar file command in the manifest. Instead of cfe parameters, you need to pass cfm parameters, and then specify the full path to the file that contains the information that needs to be entered into the manifest. I added this information to the adding.txt file and uploaded it to the repository. In addition, I quote this command in full (to build a jar file from the java class files I need): 

jar cfm Final.jar "Path\to\adding.txt" AcidSpray.class Bite.class BodySlam.class Charjabug.class Crunch.class Grubbin.class Gulpin.class Main.class Mimikyu.class MudSlap.class PinMissile.class Pound.class Present.class SludgeWave.class SuperBite.class Swalot.class Twineedle.class Vikavolt.class ZingZap.class

I hope these instructions will help you solve your problem. Good luck!

