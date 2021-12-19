package Control;

import Elevators.Elevator;
import Passengers.Passenger;
import Threads.Clock;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;

//Choose an elevator with the smallest number of stops in the path
public class ShortestListAllocation extends CentralControl {
    public ShortestListAllocation(BlockingQueue<Passenger> createdPersons, Clock clock) {
        super(createdPersons, clock);
        updatePath = new AddByTheWay();
    }

    @Override
    protected Elevator chooseElevator(Passenger person) {
        ElevatorWeight getListLength = (Elevator e) -> {
            synchronized (floorsPath.getLock(e)) {
                return floorsPath.getFloorsPath(e).size();
            }
        };
        return Elevator.getElevators().stream()
                .min(Comparator.comparingInt(getListLength::getWeight)).orElse(null);
    }
}
