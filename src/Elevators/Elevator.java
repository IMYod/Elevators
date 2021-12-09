package Elevators;

import Control.CentralControl;
import Main.Settings;
import Passengers.Floor;
import Passengers.Passenger;

import java.util.*;

public class Elevator {

    private static HashMap<Integer, Elevator> elevatorsList;
    protected static CentralControl centralControl;
    protected static LeavingStatics leavingStatics;
    public static Collection<Elevator> getElevators() {
        return elevatorsList.values();
    }


    public static void init() {
        elevatorsList = new HashMap<>();
        for (int i=0; i< Settings.elevatorsAmount; ++i) {
            elevatorsList.put(i, new Elevator(i));
        }
    }

    public static void setCentralControl(CentralControl centralControl) {
        Elevator.centralControl = centralControl;
        leavingStatics = new LeavingStatics(centralControl.getClock());
    }

    public static Elevator getElevator(int floorID) {
        return elevatorsList.get(floorID);
    }

    private final int id;
    private Floor atFloor, destFloor;
    private HashMap<Integer, List<Passenger>> inside; //mapping passengers by destination floor
    int stoppingTime;

    private Elevator(int id) {
        this.id = id;
        inside = new HashMap<>();
        for (int i=0; i< Settings.floors; ++i) {
            inside.put(i, new LinkedList<>());
        }
        atFloor = Floor.getFloor(0);
    }

    public Floor getCurrentFloor() {
        return atFloor;
    }
    public Floor getDestFloor() { return destFloor; }

    public void doStep() {
        if (stoppingTime == 0) { //permitted go
            if (destFloor != null) {
                int direction = sign(destFloor.getId() - atFloor.getId()); // atFloor should change by {-1,0,1}
                atFloor = Floor.getFloor(atFloor.getId() + direction); // go
                if (atFloor == destFloor) { // Coming to destination
                    if (direction != 0) {
                        stoppingTime = Settings.stoppingTime; //stop for a while
                    }
                    else {
                        waitingEnter();
                    }
                    centralControl.elevatorStopped(this, atFloor); //find new destination
                }
            }
        }
        else {
            if (stoppingTime == Settings.stoppingTime) {
                passengersLeave();
            }
            waitingEnter();
            --stoppingTime;
        }
        System.out.println(this);
    }

    private static int sign(int i) {
        return Integer.compare(i, 0);
    }

    public void goToFloor(Floor destFloor) {
        this.destFloor = destFloor;
    }

    protected void waitingEnter() {
        Collection<Passenger> waiting = atFloor.passengersAt(this);
        waiting.forEach(this::enterPassenger);
        waiting.clear();
    }
    protected void enterPassenger(Passenger p) {
        inside.get(p.getDestFloor().getId()).add(p);
        centralControl.addToStopList(this, p.getDestFloor());
    }

    protected void passengersLeave() {
        List<Passenger> toLeave = inside.get(atFloor.getId());
        if (! toLeave.isEmpty()) {
            leavingStatics.Leave(toLeave);
            toLeave.clear();
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", atFloor=" + atFloor +
                ", destFloor=" + destFloor +
                ", stoppingTime=" + stoppingTime +
                '}';
    }
}
