package stonks;

public abstract class Business {
    private int profitLevel;

    public void setProfitLevel(int level) {
        this.profitLevel += level;
    }

    public abstract void sell();

}
