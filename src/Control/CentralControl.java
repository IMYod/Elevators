package Control;

import Elevators.Elevator;
import Floors.Floor;
import Floors.FloorsPath;
import Passengers.Passenger;
import Threads.Clock;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public abstract class CentralControl implements Runnable {

    protected CentralControl(BlockingQueue<Passenger> createdPersons, Clock clock) {
        this.createdPersons = createdPersons;
        this.clock = clock;

        floorsPath = new FloorsPath();
    }

    UpdatePath updatePath;
    FloorsPath floorsPath;
    protected BlockingQueue<Passenger> createdPersons;
    protected Clock clock;

    protected abstract Elevator chooseElevator(Passenger person);

    public void addToStopList(Elevator elevator, Floor floor) {
        synchronized (floorsPath.getLock(elevator)) {
            updatePath.addToStopList(floorsPath.getFloorsPath(elevator), elevator, floor);
        }
    }
    public void elevatorStopped(Elevator elevator, Floor floor) {
        synchronized (floorsPath.getLock(elevator)) {
            LinkedList<Floor> elevatorPath = floorsPath.getFloorsPath(elevator);
            elevatorPath.pollFirst();
            elevator.goToFloor(elevatorPath.peekFirst());
        }
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
