public class Journalist extends HomoSapiens implements ForJournalist {

    Info information = new Info();
    //Traveler traveler = new Traveler();

    private boolean hasSeed;
    private String theme = "";
    private String opponent;
    private String name;
    private String homePlanet;
    private String knowledge;
    private String job;
    private final String aboutTheEarth = information.getInfoAboutTheEarth(); // Information
    private final String aboutTheMoon = information.getInfoAboutTheMoon();

    public void say(Traveler traveler) {
        System.out.println("Здравствуйте!");
        System.out.println("Я - " + getName() + ", " + getJob());
        //System.out.println("Вы же " + traveler.getJob() + "?");
        System.out.println("Насколько мы знаем, ваша родная планета - " + traveler.getHomePlanet());
        System.out.println("Нам было бы очень интересно услышать о вашей планете...");
        System.out.println("Тема нашего интервью - " + getTheme());
        setInformationAboutTheEarth(aboutTheEarth); // Journalist receives the information
        if (knowledge != null) {
            System.out.println("Ого, а наша планета совсем другая!");
            System.out.println(getInformationAboutTheMoon()); // To say about the Moon
        }
        else {
            System.out.println("Журналист не понял интерьвера!");
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

    public void setSeed(boolean seed) {
        this.hasSeed = seed;
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

    @Override
    public String toString(Traveler traveler) {
        return "Person{"
                + "Имя: '" + traveler.getName() + '\''
                + ", Работа: " + traveler.getJob()
                + ", Родная планета: " + traveler.getHomePlanet()
                + '}';
    }
}
