package Control;

import Elevators.Elevator;
import Floors.Floor;

import java.util.HashMap;
import java.util.LinkedList;

public interface UpdatePath {
    void addToStopList(LinkedList<Floor> currentFloorPath, Elevator elevator, Floor floor);
}
