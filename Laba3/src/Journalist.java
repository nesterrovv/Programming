public class Journalist {

    Info information = new Info(); // Instantiating a class
    Journalist journalist = new Journalist();
    private boolean hasSeed = false;

    private String knowledge = null;

    public String say(String name) {
        System.out.println("Здравствуйте " + name + "!");
        System.out.println("Нам было бы очень интересно услышать о вашей планете...");
        journalist.setInformationAboutTheEarth(aboutTheEarth); // Journalist receives the information
        if (knowledge != null) {
            System.out.println("Ого, а наша планета совсем другая!");
            journalist.getInformationAboutTheMoon(); // To say about the Moon
            this.hasSeed = true; // The traveler gives the seeds as a gift
            return "Интервью прошло успешно!";
        }
        else {
            return "Журналист не понял интерьвера!";
        }
    }

    private final String aboutTheEarth = information.getInfoAboutTheEarth(); // Information
    private final String aboutTheMoon = information.getInfoAboutTheMoon();

    public void setInformationAboutTheEarth(String inform) { // To receive the information
        this.knowledge = inform;
    }
    public String getInformationAboutTheMoon() { // To take info about herself
        return this.aboutTheMoon;
    }

    /*
    + say(String): String
    + setInformation(Info)
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
