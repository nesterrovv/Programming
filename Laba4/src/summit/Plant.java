package summit;

public class Plant {
   // private String type;
    //private String homePlanet;
    //private boolean growingStatus;
    private boolean inSoil;
    private boolean isWatered;

    public void grow() {
        if (inSoil && isWatered) {
            System.out.print("Сложившиеся благоприятные условия помогают ");
            System.out.println("растению расти. ");
            System.out.println("Оно растет! Нужно подождать.");

        }
        else {
            System.out.println("Оно не растет!");
        }
    }

    //public boolean inSoil() {
    //    return this.inSoil;
    //}

    //public boolean isWatered() {
    //    return this.isWatered;
    //}

    public void getSoil(boolean b) {
        this.inSoil = b;
    }

    public void getWater(boolean b) {
        this.isWatered = b;
    }
}
