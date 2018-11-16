package ui.zlz.bean;

public class NewComer {
   private String rate;
   private  String time;
   private  boolean isInvest;

    public NewComer() {
    }

    public NewComer(String rate, String time, boolean isInvest) {
        this.rate = rate;
        this.time = time;
        this.isInvest = isInvest;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isInvest() {
        return isInvest;
    }

    public void setInvest(boolean invest) {
        isInvest = invest;
    }
}
