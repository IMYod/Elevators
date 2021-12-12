package Threads;

import Elevators.Elevator;
import GUI.Building;
import Main.Settings;

import java.util.Collection;
import java.util.List;
import java.util.TimerTask;


public class SimulateClockTask extends TimerTask {

    public SimulateClockTask() {
        super();
    }

    public SimulateClockTask(Building gui) {
        this.gui = gui;
    }

    List<Elevator> elevators;
    Building gui;
    private final long sleepingTimeMS = 1000L;
    private int counter = 0;

    @Override
    public void run() {
        System.out.println("sec: " + counter);
        counter++;
        Elevator.getElevators().forEach(Elevator::doStep);
        if (gui != null)
            gui.update();
    }

    public int getCounter() {
        return counter;
    }
}
