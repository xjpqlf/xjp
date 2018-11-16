package ui.zlz.bean;

public class BestHot {
    private  String lease;
    private String  repay;

    public BestHot() {
    }

    public BestHot(String lease, String repay) {
        this.lease = lease;
        this.repay = repay;
    }

    public String getLease() {
        return lease;
    }

    public void setLease(String lease) {
        this.lease = lease;
    }

    public String getRepay() {
        return repay;
    }

    public void setRepay(String repay) {
        this.repay = repay;
    }
}
