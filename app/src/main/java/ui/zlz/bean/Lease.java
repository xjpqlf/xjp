package ui.zlz.bean;

public class Lease {
    private String rate;
    private String sum;
    private String num;
    private String receivable;
    private boolean islease;

    public Lease(String rate, String sum, String num, String receivable, boolean islease) {
        this.rate = rate;
        this.sum = sum;
        this.num = num;
        this.receivable = receivable;
        this.islease = islease;
    }

    public Lease() {
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getReceivable() {
        return receivable;
    }

    public void setReceivable(String receivable) {
        this.receivable = receivable;
    }

    public boolean isIslease() {
        return islease;
    }

    public void setIslease(boolean islease) {
        this.islease = islease;
    }
}
