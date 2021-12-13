package Control;

import Elevators.Elevator;
import Main.Settings;
import Floors.Floor;
import Passengers.Passenger;
import Threads.Clock;

import java.util.*;
import java.util.concurrent.BlockingQueue;

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
