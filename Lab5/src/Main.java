import data.Country;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        /*
        CollectionManager collectionManager = new CollectionManager();
        Country c1 = collectionManager.receiveNationality();
        Country c2 = collectionManager.receiveNationality();
        Country c3 = collectionManager.receiveNationality();
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
         */
        Commander commander = new Commander(new CollectionManager());
        commander.interactiveMod();
    }
}