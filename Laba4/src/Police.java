/**
 * Class for canceling the meetings
 */

public class Police {

    private final String weapon = "Электрическая дубинка";

    public String getWeapon() {
        return weapon;
    }

    public void cancelMeeting(CrowdMember crowdMember) {
        crowdMember.crash();
    }


}
