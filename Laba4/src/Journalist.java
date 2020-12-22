/**
 * Class Journalist for interview to traveler.
 * It can push crowd members and force a traveler to
 * ad some business
 */

public class Journalist extends HomoSapiens {

    //final private String character;
    private boolean isKicked;
    //final private boolean isImpudent = true;

    //Constructor for journalist
    public Journalist(String name, String clothes) {
        super(name,clothes);
    }

    // Method for pushing somebody in a crowd. For impudent journalists.
    public void push(CrowdMember member) {
        member.fall(false); //
    }

    // Method for ad
    public void ad(Traveler traveler) {
        traveler.hasAd(true);
    }

    public void isKicked(boolean status) {
        this.isKicked = status;
    }


    public boolean getKicked() {
        return this.isKicked;
    }

}
