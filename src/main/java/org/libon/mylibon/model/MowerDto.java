package org.libon.mylibon.model;

import java.util.List;

public class MowerDto {
    String id;
    List<String> instructions;
    Position start_position;
    String orientation;

    public MowerDto(String id, List<String> instructions, Position start_position, String orientation) {
        this.id = id;
        this.instructions = instructions;
        this.start_position = start_position;
        this.orientation = orientation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public Position getStart_position() {
        return start_position;
    }

    public void setStart_position(Position start_position) {
        this.start_position = start_position;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
