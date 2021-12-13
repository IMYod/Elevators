package Threads;

import GUI.Building;
import Main.Settings;

import java.util.Timer;

public class Clock {
    private Timer timer;
    private SimulateClockTask clockTask;
    public Building gui;

    //constructors
    public Clock() {
        timer = new Timer();
        clockTask = new SimulateClockTask(gui);
    }
    public Clock(Building gui) {
        timer = new Timer();
        clockTask = new SimulateClockTask(gui);
    }

    //Perform the sequence of actions in each unit of time
    public void play() {
        timer.schedule(clockTask, 0L, Settings.oneUnitTime);
    }

    public int currentTime() {
        return clockTask.getCounter();
    }
}
