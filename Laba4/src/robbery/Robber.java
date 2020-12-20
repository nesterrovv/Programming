package robbery;

public class Robber {
    private final String arsenal = "Винтовки, ружья, пистолеты, ручные гранаты, пулеметы";

    public String shot() {
        return "Бабах! Полицейские укокошены.";
    }

    // num - деньги в банке
    public int num = 100000000;
    public int still(int num) throws Exception{
        if(num == 0) {
            throw new Exception("Нечего красть!");
        }
        int result = num;
        return num;
    }

}
