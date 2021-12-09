package Passengers;

import Elevators.Elevator;

public class Passenger {
    static int counter = 0;

    int id;
    int creationTime;
    Floor sourceFloor;
    Floor destFloor;

    Elevator atElevator;

    public Passenger(int creationTime, Floor sourceFloor, Floor goToFloor) {
        this.id = counter++;
        this.creationTime = creationTime;
        this.sourceFloor = sourceFloor;
        this.destFloor = goToFloor;
    }

    public int getId() {
        return id;
    }

    public Floor getDestFloor() {
        return destFloor;
    }

    public Floor getSourceFloor() {
        return sourceFloor;
    }

    public int getCreationTime() {
        return creationTime;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", creationTime=" + creationTime +
                ", sourceFloor=" + sourceFloor +
                ", destFloor=" + destFloor +
                '}';
    }
}
