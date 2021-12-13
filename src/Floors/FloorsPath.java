package Floors;

import Elevators.Elevator;

import java.util.*;

public class FloorsPath {
    private HashMap<Elevator, LinkedList<Floor>> floorsPath;
    private HashMap<Elevator, Object> locks;

    public FloorsPath() {
        floorsPath = new HashMap<>();
        locks = new HashMap<>();
        for (Elevator elevator : Elevator.getElevators()) {
            floorsPath.put(elevator, new LinkedList<>());
            locks.put(elevator, new Object());
        }
    }

    public Object getLock(Elevator elevator) {
        return locks.get(elevator);
    }
    public LinkedList<Floor> getFloorsPath(Elevator elevator) {
        return floorsPath.get(elevator);
    }


}
