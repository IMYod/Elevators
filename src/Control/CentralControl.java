package Control;

import Elevators.Elevator;
import Passengers.Floor;
import Passengers.Passenger;
import Threads.Clock;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public abstract class CentralControl implements Runnable {

    protected CentralControl(BlockingQueue<Passenger> createdPersons, Clock clock) {
        this.createdPersons = createdPersons;
        this.clock = clock;

        floorsPath = new HashMap<>();
        for (Elevator elevator : Elevator.getElevators()) {
            floorsPath.put(elevator, new LinkedList<>());
        }
    }

    UpdatePath updatePath;
    HashMap<Elevator, LinkedList<Floor>> floorsPath;
    protected BlockingQueue<Passenger> createdPersons;
    protected Clock clock;

    protected abstract Elevator chooseElevator(Passenger person);
    public void addToStopList(Elevator elevator, Floor floor) {
        updatePath.addToStopList(floorsPath, elevator, floor);
    }
    public void elevatorStopped(Elevator elevator, Floor floor) {
        floorsPath.get(elevator).pollFirst();
        elevator.goToFloor(floorsPath.get(elevator).peekFirst());
    }

    @Override
    public void run() {
        try {
            while (true) {
                Passenger passenger = createdPersons.take();
                Elevator elevator = chooseElevator(passenger);
                System.out.println("Go to " + elevator.getId());
                Floor floor = passenger.getSourceFloor();
                floor.put(elevator, passenger);
                addToStopList(elevator, floor);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Clock getClock() {
        return clock;
    }
}
