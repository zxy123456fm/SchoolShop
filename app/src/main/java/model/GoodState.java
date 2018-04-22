package model;

/**
 */

public class GoodState {
    private boolean IsSelect;
    private int pos;

    public GoodState(boolean isSelect, int pos) {
        IsSelect = isSelect;
        this.pos = pos;
    }

    public boolean isSelect() {
        return IsSelect;
    }

    @Override
    public String toString() {
        return "GoodState{" +
                "IsSelect=" + IsSelect +
                ", pos=" + pos +
                '}';
    }

    public void setSelect(boolean select) {
        IsSelect = select;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
