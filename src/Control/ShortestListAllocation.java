package Control;

import Elevators.Elevator;
import Main.Settings;
import Passengers.Floor;
import Passengers.Passenger;
import Threads.Clock;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class ShortestListAllocation extends CentralControl {
    public ShortestListAllocation(BlockingQueue<Passenger> createdPersons, Clock clock) {
        super(createdPersons, clock);
        updatePath = new AddByTheWay();
    }

    @Override
    protected Elevator chooseElevator(Passenger person) {
        ElevatorWeight getListLength = (Elevator e) -> floorsPath.get(e).size();
        return Elevator.getElevators().stream()
                .min(Comparator.comparingInt(getListLength::getWeight)).orElse(null);
    }
}
