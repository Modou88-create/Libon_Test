package org.libon.mylibon.model;

import java.util.List;

public class LibonRequest {

    private Field field;
    private List<MowerDto> mowers;

    public LibonRequest(Field field, List<MowerDto> mowers) {
        this.field = field;
        this.mowers = mowers;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<MowerDto> getMowers() {
        return mowers;
    }

    public void setMowers(List<MowerDto> mowers) {
        this.mowers = mowers;
    }
}
