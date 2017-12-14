package ru.test.elevator;

public class ElevatorSimulator {
    static int floors;
    static float height;
    static float velocity;
    static float doors;

    public static void main (String[] args) {
        sanityCheck(args);
        startElevator();
    }

    private static void sanityCheck(String[] args) {
        checkArgs(args);
        checkFloors(args);
        checkFloorHeight(args);
        checkVelocity(args);
        checkDoors(args);
    }

    private static void startElevator() {

        ElevatorControlCenter controller = new ElevatorControlCenter(floors, height, velocity, doors);
        controller.go();
    }

    //Проверяем количество параметров
    private static void checkArgs(String[] args) {
        if(args.length<4){
            throw new IllegalArgumentException("Too few parameters. Should be 4.");
        }
        if(args.length>4){
            throw new IllegalArgumentException("Too many parameters. Should be 4.");
        }
    }

    //Проверяем количество этажей
    private static void checkFloors(String[] args) {
        try {
            floors = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(args[0]+" <- that is not a valid number.");
        }
        if(floors<5){
            throw new IllegalArgumentException("Too few floors. Should be at lest 5.");
        }
        if(floors>20){
            throw new IllegalArgumentException("Too many floors. 20 tops.");
        }

    }
    //Проверяем высоту этажа
    private static void checkFloorHeight(String[] args) {
        try {
            height = Float.parseFloat(args[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(args[1]+" <- that is not a valid number.");
        }
        if(height<=2.54){
            throw new IllegalArgumentException("Floor height should be at least 2,54 m.");
        }
        if(height>13){
            throw new IllegalArgumentException("Floor height should be no more than 13 m.");
        }

    }
    //Проверяем скорость
    private static void checkVelocity(String[] args) {
        try {
            velocity = Float.parseFloat(args[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(args[2]+" <- that is not a valid number.");
        }
        if(velocity<=0){
            throw new IllegalArgumentException("Velocity should be positive");
        }
        if(velocity>299792458){
            throw new IllegalArgumentException("Relativistic model says 299792458 m/s is max possible speed.");
        }
    }

    //Проверяем скорость дверей
    private static void checkDoors(String[] args) {
        try {
            doors = Float.parseFloat(args[3]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(args[3]+" <- that is not a valid number.");
        }
        if(doors<=3){
            throw new IllegalArgumentException("Min 3 seconds.");
        }
        if(doors>15){
            throw new IllegalArgumentException("Max 15 seconds.");
        }

    }
}
