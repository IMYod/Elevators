package Control;

import Elevators.Elevator;
import Main.Settings;
import Passengers.Floor;
import Passengers.Passenger;
import Threads.Clock;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class RandomAllocation extends CentralControl {
    public RandomAllocation(BlockingQueue<Passenger> createdPersons, Clock clock) {
        super(createdPersons, clock);
        updatePath = new AddByTheWay();
    }

    @Override
    protected Elevator chooseElevator(Passenger person) {
        return Elevator.getElevator(ThreadLocalRandom.current().nextInt(Settings.elevatorsAmount));
    }
}
