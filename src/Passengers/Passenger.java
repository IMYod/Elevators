package Passengers;

import Floors.Floor;

public class Passenger {
    static int counter = 0;

    int id;
    int creationTime;
    // The floor on which it was created
    Floor sourceFloor;
    // The floor to be taken to
    Floor destFloor;

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
