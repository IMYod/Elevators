package Passengers;

import Elevators.Elevator;
import Main.Settings;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Floor {
    public static HashMap<Integer, Floor> floorsList;

    public static void init() {
        floorsList = new HashMap<>();
        for (int i=0; i< Settings.floors; ++i) {
            Floor floor = new Floor(i);
            floorsList.put(i, floor);
        }
    }

    public static Collection<Floor> getFloors() { return floorsList.values(); }
    public static Floor getFloor(int floorID) {
        return floorsList.get(floorID);
    }

    int id;
    HashMap<Integer, List<Passenger>> waitingToElevator;

    private Floor(int id) {
        this.id = id;
        waitingToElevator = new HashMap<>();
        for (int i=0; i<Settings.elevatorsAmount; ++i) {
            waitingToElevator.put(i, new LinkedList<>());
        }
    }

    public Collection<Passenger> passengersAt(Elevator elevator) {
        return waitingToElevator.get(elevator.getId());
    }
    public int amountPassengers() {
        return waitingToElevator.values().stream().map(List::size).reduce(Integer::sum).orElse(0);
    }

    public void put(Elevator elevator, Passenger passenger) {
        waitingToElevator.get(elevator.getId()).add(passenger);
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return id == floor.id;
    }
    @Override
    public int hashCode() {
        return id;
    }

    public boolean lessThan(Floor other) {
        return id < other.id;
    }
    public boolean between(Floor a, Floor b) {
        return (a.lessThan(this) && this.lessThan(b)) || (this.lessThan(a) && b.lessThan(this));
    }

    @Override
    public String toString() {
        return "Floor{" + id +
                '}';
    }
}
