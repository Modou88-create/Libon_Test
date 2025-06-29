package org.libon.mylibon.service;

import org.libon.mylibon.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.libon.mylibon.model.Orientation.*;


@Service
public class MowerService {

    public List<MowerPositionResponse> getTheMowersPostions(LibonRequest libonRequest) {

        List<MowerPositionResponse> result = new ArrayList<>();

        for (MowerDto mower : libonRequest.getMowers()) {
            Position position = mower.getStart_position();
            Orientation orientation = Orientation.valueOf(mower.getOrientation());

            for (String instruction : mower.getInstructions()) {
                switch (instruction) {
                    case "G" -> orientation = orientation.turnLeft();
                    case "D" -> orientation = orientation.turnRight();
                    case "A" -> {

                        Position nextOne = move(position, orientation);
                        if (isValidPosition(nextOne, libonRequest.getField())) {
                            position = nextOne;
                        }
                    }
                }
            }

            result.add(new MowerPositionResponse(mower.getId(), position));
        }

        return result;


    }

    private Position move(Position pos, Orientation orientation) {
        return switch (orientation) {
            case N -> new Position(pos.getX(), pos.getY() + 1, N);
            case E -> new Position(pos.getX() + 1, pos.getY(), E);
            case S -> new Position(pos.getX(), pos.getY() - 1, S);
            case W -> new Position(pos.getX() - 1, pos.getY(), W);
        };
    }

    public static boolean isValidPosition(Position pos, Field field) {
        return pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() <= field.getMax_x() && pos.getY() <= field.getMax_y();
    }

}
