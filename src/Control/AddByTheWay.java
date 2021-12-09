package Control;

import Elevators.Elevator;
import Passengers.Floor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class AddByTheWay implements UpdatePath {
    @Override
    public void addToStopList(HashMap<Elevator, LinkedList<Floor>> floorsPath, Elevator elevator, Floor floor) {
        List<Floor> currentFloorPath = floorsPath.get(elevator);

        synchronized (currentFloorPath) {
            ListIterator<Floor> iterator = currentFloorPath.listIterator();
            Floor current = elevator.getCurrentFloor();
            if (floor.equals(current)) {
                currentFloorPath.listIterator().add(current);
                elevator.goToFloor(floor);
                return;
            }
            while (iterator.hasNext()) {
                Floor next = iterator.next();
                if (floor.between(current, next)) {
                    iterator.previous();
                    if (!iterator.hasPrevious()) {
                        elevator.goToFloor(floor);
                    }
                    iterator.add(floor);
                    return;
                } else if (floor.equals(next)) {
                    return;
                }
                current = next;
            }
            if (currentFloorPath.isEmpty()) {
                elevator.goToFloor(floor);
            }
            currentFloorPath.add(floor);
        }
    }
}
