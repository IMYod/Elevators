package Main;

public class Settings {
    //Number of time units required to move between consecutive floors
    public static int movingTime = 1;

    //Number of time units required to move between consecutive floors
    public static int stoppingTime = 2;

    //One unit of time in milliseconds
    public static long oneUnitTime = 50L;

    //Elevators and floors amount
    public static int elevators = 5;
    public static int floors = 50;

    //The number of new people created per one unit time
    public static double passengersCreatedAtTime = 0.2;

    public static int capacity = 1000;
}
