package model;

/**
 */

public class GoodPriceDetail {
    private int num;
    private boolean IsSelecet;
    private float AllPrice;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isSelecet() {
        return IsSelecet;
    }

    public void setSelecet(boolean selecet) {
        IsSelecet = selecet;
    }

    public float getAllPrice() {
        return AllPrice;
    }

    public void setAllPrice(float allPrice) {
        AllPrice = allPrice;
    }
}
