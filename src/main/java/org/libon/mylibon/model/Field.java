package org.libon.mylibon.model;

public class Field {
    private int max_x;
    private int max_y;

    public Field(int max_x, int max_y) {
        this.max_x = max_x;
        this.max_y = max_y;
    }

    public int getMax_x() {
        return max_x;
    }

    public void setMax_x(int max_x) {
        this.max_x = max_x;
    }

    public int getMax_y() {
        return max_y;
    }

    public void setMax_y(int max_y) {
        this.max_y = max_y;
    }
}
