package Main;

public class Settings {
    //Number of time units required to move between consecutive floors
    public static int movingTime = 1;

    //Number of time units required to move between consecutive floors
    public static int stoppingTime = 3;

    //One unit of time in milliseconds
    public static long oneUnitTime = 20L;

    //Elevators and floors amount
    public static int elevators = 8;
    public static int floors = 10;

    //The number of new people created per one unit time
    public static double passengersCreatedAtTime = 3;

    public static int capacity = 1000;
}
