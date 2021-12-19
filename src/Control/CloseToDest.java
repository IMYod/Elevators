package Control;

import Elevators.Elevator;
import Floors.Floor;
import java.util.LinkedList;
import java.util.ListIterator;

/*
Excluding the conditions of "Add By The Way" class,
Add the floor if the next stop floor in the list moves away from the new floor
 */
public class CloseToDest implements UpdatePath {
    @Override
    public void addToStopList(LinkedList<Floor> currentFloorPath, Elevator elevator, Floor floor) {
        ListIterator<Floor> iterator = currentFloorPath.listIterator();
        Floor current = elevator.getCurrentFloor();
        if (floor.equals(current)) {
            currentFloorPath.listIterator().add(current);
            elevator.goToFloor(floor);
            return;
        }
        while (iterator.hasNext()) {
            Floor next = iterator.next();
            if (floor.between(current, next) || closestToFirst(current, floor, next)) {
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

    private boolean closestToFirst(Floor floor, Floor a, Floor b) {
        return Math.abs(floor.getId() - a.getId()) <= Math.abs(floor.getId() - b.getId());
    }
}
