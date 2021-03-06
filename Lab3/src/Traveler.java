public class Traveler extends HomoSapiens implements ForTraveler {

    private boolean eatingStatus = false;
    private boolean drinkingStatus = false;
    private boolean healthStatus = false;
    private boolean possibility = false;

    //Traveler traveler = new Traveler();

    public void eat(Food f) {
        this.eatingStatus = true;
        System.out.println("Путешественник вкусно покушал. Съедено: " + f.getFood());
    }

    public void drink(Drink d) {
        this.drinkingStatus = true;
        System.out.println("Путешественник утолил жажду. Выпито: " + d.getDrink());
    }

    public boolean getHealth() {
        return this.healthStatus;
    }


    public String getHealthStatus() {
        if (drinkingStatus && eatingStatus) {
           this.healthStatus = true;
        }
        if (this.healthStatus) {
           return  "Путешественник прекрасно себя чувствует!";
        }
        else {
            return "Путешественнику нехорошо. Нужно срочно принять меры!";
        }
    }

    public void possibilityToGiveInformation() { // call the method after the interview ends
        System.out.println("Рассказ путешественника успешно передал знания.");
        this.possibility = true;
        //return this.possibility;
    }

    public boolean returnPossibility() {
        return this.possibility;
    }

    public void takeSeed() {
        this.hasSeed = true;
    }

    @Override
    public void setHomePlanet(String homePlanet) {
        this.homePlanet = homePlanet;
    }

    @Override
    public String getHomePlanet() {
        return this.homePlanet;
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

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String getJob() {
        return this.job;
    }

}
