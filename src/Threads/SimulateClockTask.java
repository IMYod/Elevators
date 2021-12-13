package Threads;

import Elevators.Elevator;
import GUI.Building;
import Main.Settings;

import java.util.Collection;
import java.util.List;
import java.util.TimerTask;


public class SimulateClockTask extends TimerTask {

    //constructors
    public SimulateClockTask() {
        super();
    }
    public SimulateClockTask(Building gui) {
        this.gui = gui;
    }

    Building gui;
    private int counter = 0;

    //The series of operations in each unit of time
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
