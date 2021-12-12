package Threads;

import Elevators.Elevator;
import GUI.Building;
import Main.Settings;

import java.util.Collection;
import java.util.List;
import java.util.Timer;

public class Clock {
    private Timer timer;
    private SimulateClockTask clockTask;
    public Building gui;

    public Clock() {
        timer = new Timer();
        clockTask = new SimulateClockTask(gui);
    }

    public Clock(Building gui) {
        timer = new Timer();
        clockTask = new SimulateClockTask(gui);
    }

    public void play() {
        timer.schedule(clockTask, 0L, Settings.oneUnitTime);
    }

    public int currentTime() {
        return clockTask.getCounter();
    }
}
