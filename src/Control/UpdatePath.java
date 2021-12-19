package Control;

import Elevators.Elevator;
import Floors.Floor;

import java.util.LinkedList;

/*
Given ordered list of stopping floors for an elevator,
add one floor to the list
 */

public interface UpdatePath {
    void addToStopList(LinkedList<Floor> currentFloorPath, Elevator elevator, Floor floor);
}
