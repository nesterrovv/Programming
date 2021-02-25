/**
 * Class for canceling the meetings
 */

public class Police {

    private final String weapon = "Электрическая дубинка";
    private Location location = Location.STREET;

    public String getWeapon() {
        return weapon;
    }

    public void cancelMeeting(CrowdMember crowdMember) {
        crowdMember.crash();
    }

    public void death() {
        System.out.println("Полицейский убит.");
    }

    public void changeLocation(Location newPlace) {
        this.location = newPlace;
        System.out.println("Полиция приехала на вызов!");
    }

    public Location getLocation() {
        return location;
    }
}
