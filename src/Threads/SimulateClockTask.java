package Threads;

import Elevators.Elevator;
import Main.Settings;

import java.util.Collection;
import java.util.List;
import java.util.TimerTask;


public class SimulateClockTask extends TimerTask {

    List<Elevator> elevators;
    private final long sleepingTimeMS = 1000L;
    private int counter = 0;

    @Override
    public void run() {
        System.out.println("sec: " + counter);
        counter++;
        Elevator.getElevators().forEach(Elevator::doStep);
    }

    public int getCounter() {
        return counter;
    }
}