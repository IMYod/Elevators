package Main;

import Control.ClosestAllocation;
import Control.RandomAllocation;
import Control.ShortestListAllocation;
import Elevators.Elevator;
import Passengers.Floor;
import Passengers.Passenger;
import Control.CentralControl;
import Passengers.PassengersFactory;
import Threads.Clock;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {
    public static void main(String[] args){

        Clock clock = new Clock();

        Floor.init();
        Elevator.init();
        BlockingQueue<Passenger> personBlockingQueue = new ArrayBlockingQueue<>(1000);
        CentralControl control = new ClosestAllocation(personBlockingQueue, clock);
        Elevator.setCentralControl(control);
        PassengersFactory passengersFactory = new PassengersFactory(personBlockingQueue, clock);

        clock.play();
        passengersFactory.run();
        control.run();
    }
}
