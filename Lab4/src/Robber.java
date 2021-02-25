public class Robber {
    private String arsenal = "Ружья, пулеметы, гранаты, винтовки";

    private int bankProfit = (int) (Math.random()*1000000);
    private int robberProfit = 0;

    public String getArsenal() {
        return this.arsenal;
    }

    public void stilling() {
        bankProfit = 0;
        System.out.println("В банке кончились деньги");
    }

    public void still() {
       System.out.println(getArsenal() + " - все это пошло на взлом сейфа с деньгами");
       robberProfit = bankProfit;
       System.out.println("Нажива бандитов: " + robberProfit + " фертингов");
    }

    public void kill(Police police) {
        System.out.println("Бандит стреляет. Его цель: полиция");
        police.death();
    }
}
