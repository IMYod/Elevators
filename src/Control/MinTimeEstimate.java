package Control;

import Elevators.Elevator;
import Main.Settings;
import Floors.Floor;
import Passengers.Passenger;
import Threads.Clock;

import java.util.*;
import java.util.concurrent.BlockingQueue;

/*
An estimate of the route time of the elevator, if this passenger will be added to it.
Choose the minimum between them.
 */
public class MinTimeEstimate extends CentralControl {

    public MinTimeEstimate(BlockingQueue<Passenger> createdPersons, Clock clock) {
        super(createdPersons, clock);
        updatePath = new AddByTheWay();
    }

    @Override
    protected Elevator chooseElevator(Passenger person) {
        return Elevator.getElevators().stream().min(Comparator.comparingInt(
                (Elevator e) -> estimateElevator(e, person)))
                .orElse(null);
    }

/*
The estimate takes into account the number of floors in the path, and the number of stops.
The estimated time required for both.
For simplicity, there is no consideration of the order of the floors within the path.
 */
    private int estimateElevator(Elevator e, Passenger person) {
        Set<Floor> possibleFloors;
        synchronized (floorsPath.getLock(e)) {
            possibleFloors = new HashSet<>(floorsPath.getFloorsPath(e));
            possibleFloors.add(e.getCurrentFloor());
            possibleFloors.add(person.getSourceFloor());
            possibleFloors.add(person.getDestFloor());
            int minRange = possibleFloors.stream().map(Floor::getId).min(Integer::compare).get();
            int maxRange = possibleFloors.stream().map(Floor::getId).max(Integer::compare).get();
            return possibleFloors.size() * Settings.stoppingTime +
                    (maxRange - minRange) * Settings.movingTime;
        }
    }
}
