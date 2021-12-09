package Elevators;

import Passengers.Passenger;
import Threads.Clock;

import java.util.Collection;

public class LeavingStatics {
    int countLeaving;
    long totalJourneyTime;
    Clock clock;

    public LeavingStatics(Clock clock) {
        this.clock = clock;
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
        ++countLeaving;
        totalJourneyTime += clock.currentTime() - passenger.getCreationTime();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Leaving: ");
        sb.append(countLeaving);
        sb.append("\nAverage time: ");
        sb.append((double) totalJourneyTime / countLeaving);
        return sb.toString();
    }
}
