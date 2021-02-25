/**
 * A class for creating crowd members.
 * They will shout random questions to astronauts
 * and interfere with journalists. Can be pushed apart.
 */

public class CrowdMember {

    private boolean paralyze;

    // Questions for randomly asking
    String[] questions = new String[] {
            "Как вы добрались?", "В космосе холодно?",
            "Вы надолго?", "Что происходит?", "На Земле много людей?",
            "Как вам наш город?", "Где вы будете жить?", "Сколько у вас денег?",
            "Скафандр тяжелый?", "Тесно ли в ракете?", "У вас есть деньги?"
    };

    private boolean stable; // Crowd Member may be falled

    // To ask some random question
    public void ask() {
        int randomIndex = (int) Math.round(Math.random() * 10);
        System.out.println(questions[randomIndex]);
    }

    // To fall, when crowd member was pushed
    public void fall(boolean status) {
        this.stable = status;
        if (!this.stable) {
            System.out.println("Человек падает");
        }
    }

    public void crash() {
        this.paralyze = true;
        System.out.println("Парализованный током человек падает без чувств.");
    }

    public boolean isParalyze() {
        return this.paralyze;
    }
}


