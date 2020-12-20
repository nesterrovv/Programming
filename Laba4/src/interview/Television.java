package interview;

public class Television {

    private boolean streamStatus;

    public void getStream(boolean b) {
        this.streamStatus = b;
    }

    public String stream() {
        return "Все происходящее транслируется по телевидению.";
    }
}
