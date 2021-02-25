/**
 * Class for main character of this plot.
 * Functional is being work out.
 */

public class Traveler extends HomoSapiens {

    final private String job;
    private boolean isAngry;
    private boolean hasAd;

    public Traveler(String name, String clothes, String job) {
        super(name, clothes);
        this.job = job;
    }

    public void angry() {
        this.isAngry = true;
    }

    public String getName() {
        return this.name;
    }

    public void kick(Journalist journalist) {
        if (isAngry) {
            journalist.isKicked(true);
        }
        else {
            System.out.println("Вдох-выдох... " + getName() + " стал спокойнее");
        }
    }

    public void hasAd(boolean status) {
        this.hasAd = status;
    }

    public boolean adPossibility() {
        return hasAd;
    }

    public String getJob() {
        return job;
    }

    public void doAd() {
        System.out.println("Висящий на шее плакат делает неплохую рекламу.");
    }

    public boolean checkAngry() {
        return isAngry;
    }
}
