package Main;

import Control.*;
import Elevators.Elevator;
import GUI.Building;
import Passengers.Floor;
import Passengers.Passenger;
import Passengers.PassengersFactory;
import Threads.Clock;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {
    public static void main(String[] args){

        Building building = new Building();
        Clock clock = new Clock(building);
        SwingUtilities.invokeLater(() -> Building.createAndShowGui(building)); //gui

        Floor.init();
        Elevator.init();
        BlockingQueue<Passenger> personBlockingQueue = new ArrayBlockingQueue<>(1000);
        CentralControl control = new MinTimeEstimate(personBlockingQueue, clock);
        Elevator.setCentralControl(control);
        PassengersFactory passengersFactory = new PassengersFactory(personBlockingQueue, clock);

        clock.play();
        passengersFactory.run();
        control.run();
    }
}
