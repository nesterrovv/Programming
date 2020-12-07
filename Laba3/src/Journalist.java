public class Journalist {

    Info information = new Info(); // Instantiating a class
    Journalist zvezdochka = new Journalist();
    String knowledge = null;

    public String say(String name) {
        Journalist zvezdochka = new Journalist();
        System.out.println("Здравствуйте " + name + "!");
        System.out.println("Нам было бы очень интересно услышать о вашей планете...");
        zvezdochka.setInformationAboutTheEarth(aboutTheEarth); // Journalist receives the information
        if (knowledge != null) {
            System.out.println("Ого, а наша планета совсем другая!");
            zvezdochka.getInformationAboutTheMoon(); // To say about the Moon
            return "Интервью прошло успешно!";
        }
        else {
            return "Журналист не понял интерьвера!";
        }
    }
    String aboutTheEarth = information.getInfoAboutTheEarth(); // Information
    String aboutTheMoon = information.getInfoAboutTheMoon();

    public void setInformationAboutTheEarth(String inform) { // To receive the information
        knowledge = inform;
    }
    public String getInformationAboutTheMoon() { // To take info about herself
        return aboutTheMoon;
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
