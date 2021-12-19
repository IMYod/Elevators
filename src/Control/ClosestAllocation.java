package Control;

import Elevators.Elevator;
import Floors.Floor;
import Passengers.Passenger;
import Threads.Clock;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class ClosestAllocation extends CentralControl {
    public ClosestAllocation(BlockingQueue<Passenger> createdPersons, Clock clock) {
        super(createdPersons, clock);
        updatePath = new CloseToDest();
    }

    //Add a passenger to at floor f,
    //to the elevator with next stopping floor, closest to fS
    @Override
    protected Elevator chooseElevator(Passenger person) {
        ElevatorWeight elevatorDistance = (Elevator e) -> {
            Floor eFloor = e.getDestFloor();
            if (eFloor == null)
                eFloor = e.getCurrentFloor();
            return Math.abs(eFloor.getId() - person.getSourceFloor().getId());
        };
        Collection<Elevator> elevatorsCollection = Elevator.getElevators();
        Collection<Elevator> elevatorsNullDest = elevatorsCollection.stream().filter(
                elevator -> (elevator.getDestFloor() == null)
        ).collect(Collectors.toSet());

        if (elevatorsNullDest.size() > 0) {
            elevatorsCollection = elevatorsNullDest;
        }

        return elevatorsCollection.stream()
                .min(Comparator.comparingInt(elevatorDistance::getWeight)).orElse(null);
    }

}
