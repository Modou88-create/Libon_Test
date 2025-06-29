package org.libon.mylibon.model;

public class Mower {
    private Position tondeusePosition;
    private int maxX;
    private int maxY;

    public Mower(Position tondeusePosition, int maxX, int maxY) {
        this.tondeusePosition = tondeusePosition;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Position getTondeusePosition() {
        return tondeusePosition;
    }

    public void setTondeusePosition(Position tondeusePosition) {
        this.tondeusePosition = tondeusePosition;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }
}
