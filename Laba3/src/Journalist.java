public class Journalist extends HomoSapiens implements ForJournalist {

    Info information = new Info(); // Instantiating a class
    Journalist journalist = new Journalist();

    private boolean hasSeed = false;
    private String theme = "";
    private String opponent = "неизвестный";
    private String name = "Неизвестный";
    private String homePlanet = "Неизвестная планета";
    private String knowledge = null;
    private String job = "";
    private final String aboutTheEarth = information.getInfoAboutTheEarth(); // Information
    private final String aboutTheMoon = information.getInfoAboutTheMoon();

    public String say(String name) {
        System.out.println("Здравствуйте " + getOpponent() + name + "!");
        System.out.println("Вы же " + getJob() + "?");
        System.out.println("Насколько мы знаем, ваша родная планета - " + getHomePlanet());
        System.out.println("Нам было бы очень интересно услышать о вашей планете...");
        System.out.println("Тема нашего интервью - " + getTheme());
        journalist.setInformationAboutTheEarth(aboutTheEarth); // Journalist receives the information
        if (knowledge != null) {
            System.out.println("Ого, а наша планета совсем другая!");
            System.out.println(getInformationAboutTheMoon()); // To say about the Moon
            this.hasSeed = true; // The traveler gives the seeds as a gift
            return "Интервью прошло успешно!";
        }
        else {
            return "Журналист не понял интерьвера!";
        }
    }

    public void setInformationAboutTheEarth(String inform) { // To receive the information
        this.knowledge = inform;
    }

    public String getInformationAboutTheMoon() { // To take info about herself
        return this.aboutTheMoon;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setOpponents(String opponent) {
        this.opponent = opponent;
    }

    public String getOpponent() {
        return this.opponent;
    }

    @Override
    public void setHomePlanet(String homePlanet) {
        this.homePlanet = homePlanet;
    }

    @Override
    public String getHomePlanet() {
        return homePlanet;
    }


    public boolean hasSeed() {
        return hasSeed;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String getJob() {
        return this.job;
    }
}
