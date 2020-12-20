import summit.*;
import office.*;
import robbery.*;
import stonks.*;
import interview.*;

public class Main {

    public static void main(String[] args) {

        Journalist journalist = new Journalist();
        journalist.setName("Приставака");
        journalist.setJob("Скандальная журналистка");
        journalist.setClothes("Узкое зеленое платье и берет");
        journalist.getAd();
        journalist.photo();

        Traveler traveler = new Traveler();
        traveler.setName("Мига ");
        traveler.takeInfo();
        traveler.advertise();
        traveler.angry();

        Police police = new Police();
        police.cancelMeeting();

        Clinic clinic = new Clinic();
        clinic.setProfitLevel(100);

        SweetShop sweetShop = new SweetShop();
        sweetShop.setProfitLevel(100);

        Hostel hostel = new Hostel();
        hostel.setProfitLevel(100);

        hostel.sell();
        clinic.sell();
        sweetShop.sell();


        Journalist2 journalist2  = new Journalist2();
        journalist2.getInformationAboutTheMoon();

        Traveler2 traveler2 = new Traveler2();

        System.out.println("Ученые рассказали о космосе и физических процессах на луне.");

        Office office = new Office();
        office.sellActions(100);
        office.discuss();

        Robber robber = new Robber();
        robber.shot();

    }
}
