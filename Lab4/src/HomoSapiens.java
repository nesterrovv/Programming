/**
 * The ancestor class for all characters, for people.
 * Will expand for each specific purpose.
 */

abstract public class HomoSapiens {

    protected String name;
    protected String clothes;

    //public HomoSapiens() {}
    //Constructor for some people
    public HomoSapiens (String name, String clothes) {
        this.name = name;
        this.clothes = clothes;
    }
}
