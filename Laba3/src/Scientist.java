public class Scientist extends HomoSapiens implements ForScientist {

    Scientist scientist = new Scientist();
    Info information = new Info();

    private String name = "Неизвестный";
    private String job = "Scientist";
    private boolean hasSeed = false;
    private String knowledge = null;
    private String data = null;
    private final boolean t = true;

    public String explorePlanet() {
        System.out.println("В НИИ поступила информация от журналиста");
        System.out.println("Данные скачиваются. Загрузка...");
        System.out.println("Данные загружены.");
        knowledge = this.data;
        if (knowledge != null) {
            System.out.println("Ученый " + name + "начал изучать информацию");
            return "Феноменально!";
        }
        else {
            return "Что-то пошло не так! Информацию невозможно понять...";
        }
    }

    public void getInformation() {
        this.data = information.getInfoAboutTheEarth();
    }

    public void explorePlant(Plant p) {
        System.out.println("В НИИ поступили образцы растений с Земли");
        Scientist.plant(Plant p);
        Scientist.water(Plant p);
        System.out.println("Семена посажены в горшочек.");
        System.out.println("Данные загружены.");
        knowledge = this.data;
        if (knowledge != null) {
            System.out.println("Ученый " + name + "начал изучать информацию");
            return "Феноменально!";
        }
        else {
            return "Что-то пошло не так! Информацию невозможно понять...";
        }
    }

    public void plant(Plant p) {
        p.getSoil();boolean this.t);
    }

    public void water(Plant p) {
        p.getWater();
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
}
