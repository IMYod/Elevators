package Passengers;

import Main.Settings;
import Threads.Clock;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class PassengersFactory implements Runnable {

    private BlockingQueue<Passenger> createdPersons;
    private Clock clock;

    public PassengersFactory(BlockingQueue<Passenger> createdPersons, Clock clock)
    {
        this.createdPersons = createdPersons;
        this.clock = clock;
    }

    protected Passenger createOnePassenger() {
        Floor source = Floor.getFloor(ThreadLocalRandom.current().nextInt(Settings.floors));
        int destNum = ThreadLocalRandom.current().nextInt(1, Settings.floors);
        Floor dest = Floor.getFloor((source.getId() + destNum) % Settings.floors);
        Passenger p = new Passenger(clock.currentTime(), source, dest);
        System.out.println("\u001B[34m" + "Passenger created " + p + "\u001B[0m");
        return p;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                createdPersons.add(createOnePassenger());
            }
        },0 , (long) (Settings.oneUnitTime / Settings.passengersCreatedAtTime));
    }
}
