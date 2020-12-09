public class Scientist extends HomoSapiens implements ForScientist {

    Scientist scientist = new Scientist();
    Info information = new Info();

    private String name = "Неизвестный";
    private String job = "Scientist";
    private boolean hasSeed = false;
    //private String knowledge = null;
    private String dataAboutThePlanet = null;
    private String dataAboutThePlant = null;
    private final boolean t = true;

    public String explorePlanet() {
        System.out.println("В НИИ поступила информация от журналиста");
        System.out.println("Данные скачиваются. Загрузка...");
        System.out.println("Данные загружены.");
        getInformationAboutThePlanet(dataAboutThePlanet);
        if (dataAboutThePlanet != null) {
            System.out.println("Ученый " + name + "начал изучать информацию");
            return "Феноменально!";
        }
        else {
            return "Что-то пошло не так! Информацию невозможно понять...";
        }
    }

    public String explorePlant(Plant p) {
        System.out.println("В НИИ поступили образцы растений с Земли");
        System.out.println("Их нужно посадить в землю и полить.");
        //plant(p);
        //water(p);
        System.out.println("Данные загружены.");
        getInformationAboutThePlant(dataAboutThePlant);
        if (dataAboutThePlant != null) {
            System.out.println("Ученый " + name + "начал изучать информацию");
            plant(p);
            water(p);
            p.grow();
            return "Феноменально!";
        }
        else {
            return "Что-то пошло не так! Семена не начинают прорастать.";
        }
    }

    public void getInformationAboutThePlanet(String dataAboutThePlanet) {
        dataAboutThePlanet = information.getInfoAboutTheEarth();
    }

    public void getInformationAboutThePlant(String dataAboutThePlant) {
        dataAboutThePlant = information.getInfoAboutThePlant();
    }

    public void plant(Plant p) {
        p.getSoil(t);
    }

    public void water(Plant p) {
        p.getWater(t);
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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean hasSeed() {
        return hasSeed;
    }
}
