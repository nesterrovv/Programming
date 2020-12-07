public class Journalist {
    Info information = new Info(); // Instantiating a class
    Journalist zvezdochka = new Journalist();
    String knowledge = null;
    public String say(String name) {
        Journalist zvezdochka = new Journalist();
        System.out.println("Здравствуйте " + name + "!");
        System.out.println("Нам было бы очень интересно услышать о вашей планете...");
        zvezdochka.setInformation(inform); // Journalist receives the information
        if (knowledge != null) {
            return "Информация получена!";
        }
        else {
            return "Журналист не понял интерьвера!";
        }
    }
    String inform = information.getInfoAboutTheEarth(); // Information

    public void setInformation(String information) { // To receive the information
        knowledge = information;
    }
    /*
    + say(String): String
    + setInformatin(Info)
    + getInformation(): Info
    + setTheme(String):
    + getTheme(): String
    + setOpponents(String):
    + getOpponents(): String
    + setName(String)
    + getName(): String
    + setHomePlanet(String)
    + getHomePlanet(): String
    + setJob(String)
    + getJob(): String
    + hasSeed(): boolean
     */
}
