package Elevators;

import Main.Settings;
import Floors.Floor;
import Passengers.Passenger;
import Threads.Clock;
import java.util.Collection;
import java.util.HashMap;

/*
Information on passengers, by the leaving floor.
This class can be extended for more complex analysis.
 */
public class LeavingStatics {
    int countLeaving;
    long totalJourneyTime;
    Clock clock;
    HashMap<Floor, Integer> leavingPerFloor;
    private Object[] lock;

    public int getLeavingStatics(Floor floor) {
        return leavingPerFloor.get(floor);
    }

    public LeavingStatics(Clock clock) {
        this.clock = clock;
        leavingPerFloor = new HashMap<>();
        lock = new Object[Settings.floors];
        for (int i = 0; i< Settings.floors; ++i) {
            leavingPerFloor.put(Floor.getFloor(i), 0);
            lock[i] = new Object();
        }
    }

    public void Leave(Collection<Passenger> passengers) {
        passengers.forEach(this::Leave);
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[31m");
        sb.append("Leaving: ");
        for (Passenger passenger : passengers)
            sb.append(passenger.toString());
        sb.append(this);
        sb.append("\u001B[0m");
        System.out.println(sb);
    }

    protected void Leave(Passenger passenger) {
        totalJourneyTime += clock.currentTime() - passenger.getCreationTime();
        ++countLeaving;
        increment(passenger.getDestFloor());
    }

    private void increment(Floor floor) {
        synchronized (lock[floor.getId()]) {
            leavingPerFloor.put(floor, leavingPerFloor.get(floor) + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Leaving: ");
        sb.append(countLeaving);
        sb.append("\nAverage time: ");
        sb.append((double) totalJourneyTime / countLeaving);
        return sb.toString();
    }
}
