package Control;

import Elevators.Elevator;
import Passengers.Floor;

import java.util.HashMap;
import java.util.LinkedList;

public interface UpdatePath {
    void addToStopList(HashMap<Elevator, LinkedList<Floor>> floorsPath, Elevator elevator, Floor floor);
}
