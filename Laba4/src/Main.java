import com.sun.media.jfxmediaimpl.HostUtils;

public class Main {

    public static void main(String[] args) {


        Traveler firstTraveler = new Traveler("Незнайка", "Скафандр", "Путешетвенник");
        Traveler secondTraveler = new Traveler("Мига", "Скафандр", "Путешественник");
        Journalist impudentJournalist = new Journalist("Приставака", "Узкое зеленое " +
                "платье");
        CrowdMember crowdMember = new CrowdMember();
        Police police = new Police();

        for (int i = 0; i < 11; i++) {
                crowdMember.ask();
        }

        impudentJournalist.push(crowdMember);
        System.out.println("Он стал жертвой наглого журналиста, пробирающегося к своей цели");

        secondTraveler.angry();
        impudentJournalist.ad(firstTraveler);
        if (firstTraveler.adPossibility()) {
            firstTraveler.doAd();
        }

        if (firstTraveler.adPossibility() && secondTraveler.checkAngry()) {
            secondTraveler.kick(impudentJournalist);
            System.out.println(secondTraveler.getJob() + " отвесил крепкого пинка журналисту");
        }

        if (impudentJournalist.getKicked()) {
            System.out.println("А, ой, простите-извините");
        }

        police.cancelMeeting(crowdMember);
        if (crowdMember.isParalyze()) {
            System.out.println(police.getWeapon() + " - эффективный способ успокоить человека.");
        }

        SweetShop sweetShop = new SweetShop(100);
        //SweetShop sweetShop = new SweetShop((int)(Math.random()*1000));
        Clinic clinic = new Clinic((int)(Math.random()*1000));
        Hostel hostel = new Hostel((int)(Math.random()*1000));
        Show show = new Show((int)(Math.random()*1000));

        sweetShop.profit();
        sweetShop.story();
        clinic.profit();
        clinic.story();
        hostel.profit();
        hostel.story();
        show.profit();
        show.story();

        Human human = new Human();
        House house = new House();
        House.Office office = new House.Office();
        office.sell(human);
        

    }
}
