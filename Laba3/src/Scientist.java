public class Scientist extends HomoSapiens implements ForScientist {

    //Scientist scientist = new Scientist();
    Info information = new Info();
    Seed seed = new Seed();

    private String name = "Неизвестный";
    private String job = "Scientist";
    private boolean hasSeed = false;
    //private String knowledge = null;
    private String dataAboutThePlanet = null;
    //private String dataAboutThePlant = null;
    private final boolean t = true;

    public void explorePlanet() {
        System.out.println("В НИИ поступила информация от журналиста");
        System.out.println("Данные скачиваются. Загрузка...");
        System.out.println("Данные загружены.");
        dataAboutThePlanet = information.getInfoAboutTheEarth();
        if (dataAboutThePlanet != null) {
            System.out.println(getJob() + getName() + "начал изучать информацию");
            System.out.println("Феноменально!");
        }
        else {
            System.out.println("Что-то пошло не так! Информацию невозможно понять...");
        }
    }

    public void explorePlant(Plant p) {
        
        System.out.println("В НИИ поступили образцы растений с Земли");
        System.out.println("Их нужно посадить в землю и полить.");
        System.out.println("Данные загружены.");
        //dataAboutThePlanet = information.getInfoAboutTheEarth();
        //dataAboutThePlant = seed.getDescription();

        if (seed.getDescription() != null) {
            System.out.println(getJob() + getName() + "начал изучать информацию");
            plant(p);
            water(p);
            p.grow();
            System.out.println(seed.isPlant());
            System.out.println("Феноменально!");
            //return "Ура!";
        }
        else {
            //return "Что-то пошло не так! Семена не начинают прорастать.";
            System.out.println("Что-то пошло не так! Семена не начинают прорастать.");
        }
    }

    //public void getInformationAboutThePlanet(String dataAboutThePlanet) {
    //   dataAboutThePlanet = information.getInfoAboutTheEarth();
    //}.

    //public void getInformationAboutThePlant(String dataAboutThePlant) {
      //  dataAboutThePlant = information.getInfoAboutThePlant();
    //}

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

    @Override
    public void setHomePlanet(String homePlanet) {
        this.homePlanet = homePlanet;
    }

    @Override
    public String getHomePlanet() {
        return this.homePlanet;
    }

    public void setData(String data) {
        this.dataAboutThePlanet = data;
    }

    public void setSeed(boolean seed) {
        this.hasSeed = seed;
    }
}
